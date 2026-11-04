package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.sin

object AudioSynthesizer {
    private const val SAMPLE_RATE = 44100

    // Standard musical pitch frequencies
    val NOTE_FREQUENCIES = mapOf(
        "C3" to 130.81, "C#3" to 138.59, "D3" to 146.83, "D#3" to 155.56, "E3" to 164.81,
        "F3" to 174.61, "F#3" to 185.00, "G3" to 196.00, "G#3" to 207.65, "A3" to 220.00,
        "A#3" to 233.08, "B3" to 246.94,
        "C4" to 261.63, "C#4" to 277.18, "D4" to 293.66, "D#4" to 311.13, "E4" to 329.63,
        "F4" to 349.23, "F#4" to 369.99, "G4" to 392.00, "G#4" to 415.30, "A4" to 440.00,
        "A#4" to 466.16, "B4" to 493.88,
        "C5" to 523.25, "D5" to 587.33, "E5" to 659.25, "G5" to 783.99, "A5" to 880.00
    )

    suspend fun playTone(frequencyHz: Double, durationMs: Int = 400, harmonicRichness: Float = 0.35f) = withContext(Dispatchers.Default) {
        try {
            val numSamples = (SAMPLE_RATE * (durationMs / 1000.0)).toInt()
            val sample = ShortArray(numSamples)

            for (i in 0 until numSamples) {
                val t = i.toDouble() / SAMPLE_RATE
                // Fundamental wave + 2nd and 3rd harmonics for musical instrument timbre warmth
                val fundamental = sin(2.0 * Math.PI * frequencyHz * t)
                val secondHarmonic = sin(4.0 * Math.PI * frequencyHz * t) * harmonicRichness
                val thirdHarmonic = sin(6.0 * Math.PI * frequencyHz * t) * (harmonicRichness * 0.5)
                var rawValue = (fundamental + secondHarmonic + thirdHarmonic) / (1.0 + harmonicRichness * 1.5)

                // Smooth ADSR envelope: 10% attack, 90% exponential decay
                val envelope = when {
                    i < numSamples * 0.08 -> i / (numSamples * 0.08)
                    else -> Math.exp(-3.0 * (i - numSamples * 0.08) / (numSamples * 0.92))
                }

                sample[i] = (rawValue * envelope * Short.MAX_VALUE * 0.85).toInt().toShort()
            }

            val bufferSize = AudioTrack.getMinBufferSize(
                SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            )

            val audioTrack = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(SAMPLE_RATE)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(Math.max(bufferSize, sample.size * 2))
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            audioTrack.write(sample, 0, sample.size)
            audioTrack.setNotificationMarkerPosition(numSamples)
            audioTrack.setPlaybackPositionUpdateListener(object : AudioTrack.OnPlaybackPositionUpdateListener {
                override fun onMarkerReached(track: AudioTrack?) {
                    track?.release()
                }
                override fun onPeriodicNotification(track: AudioTrack?) {}
            })
            audioTrack.play()
        } catch (_: Exception) {
            // Graceful fallback if audio device unavailable
        }
    }

    suspend fun playArpeggio(notes: List<Double>, delayBetweenNotesMs: Long = 180L) = withContext(Dispatchers.Default) {
        for (freq in notes) {
            playTone(freq, 350)
            kotlinx.coroutines.delay(delayBetweenNotesMs)
        }
    }

    suspend fun playSuccessChime() = withContext(Dispatchers.Default) {
        playTone(523.25, 140) // C5
        kotlinx.coroutines.delay(100)
        playTone(659.25, 250) // E5
        kotlinx.coroutines.delay(120)
        playTone(783.99, 450) // G5
    }

    suspend fun playErrorTone() = withContext(Dispatchers.Default) {
        playTone(185.00, 200, 0.1f) // F#3 low gentle boop
        kotlinx.coroutines.delay(80)
        playTone(164.81, 300, 0.1f) // E3
    }
}
