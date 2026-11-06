package com.example.audio

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.abs
import kotlin.math.log2
import kotlin.math.roundToInt

data class TunerReading(
    val frequencyHz: Float = 0f,
    val noteName: String = "--",
    val octave: Int = 0,
    val targetFreqHz: Float = 0f,
    val centsDeviation: Float = 0f,
    val isInTune: Boolean = false,
    val isSignalDetected: Boolean = false
)

object PitchDetector {
    private const val TAG = "PitchDetector"
    private const val SAMPLE_RATE = 22050
    private const val BUFFER_SIZE = 4096

    private val _reading = MutableStateFlow(TunerReading())
    val reading: StateFlow<TunerReading> = _reading.asStateFlow()

    private var audioRecord: AudioRecord? = null
    private var listeningJob: Job? = null
    private var isRunning = false

    private val NOTE_NAMES = listOf("Dó (C)", "Dó# (C#)", "Ré (D)", "Ré# (D#)", "Mi (E)", "Fá (F)", "Fá# (F#)", "Sol (G)", "Sol# (G#)", "Lá (A)", "Lá# (A#)", "Si (B)")
    private val NOTE_LETTERS = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")

    @SuppressLint("MissingPermission")
    fun startListening(scope: CoroutineScope) {
        if (isRunning) return

        listeningJob?.cancel()
        listeningJob = scope.launch(Dispatchers.IO) {
            try {
                val minBufSize = AudioRecord.getMinBufferSize(
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )
                val bufSize = maxOf(minBufSize, BUFFER_SIZE)

                audioRecord = AudioRecord(
                    MediaRecorder.AudioSource.MIC,
                    SAMPLE_RATE,
                    AudioFormat.CHANNEL_IN_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    bufSize
                )

                if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
                    Log.w(TAG, "AudioRecord failed to initialize")
                    return@launch
                }

                audioRecord?.startRecording()
                isRunning = true

                val audioBuffer = ShortArray(BUFFER_SIZE)

                while (isActive && isRunning) {
                    val readSamples = audioRecord?.read(audioBuffer, 0, BUFFER_SIZE) ?: 0
                    if (readSamples > 0) {
                        // Calculate RMS (volume level) to ignore background silence
                        var sumSq = 0.0
                        for (i in 0 until readSamples) {
                            sumSq += audioBuffer[i] * audioBuffer[i]
                        }
                        val rms = kotlin.math.sqrt(sumSq / readSamples)

                        if (rms > 200.0) { // Signal threshold
                            val pitch = detectPitchAutocorrelation(audioBuffer, readSamples, SAMPLE_RATE)
                            if (pitch in 35.0f..1100.0f) {
                                val result = analyzePitch(pitch)
                                _reading.value = result
                            }
                        } else {
                            // Silence / low volume
                            if (_reading.value.isSignalDetected) {
                                _reading.value = _reading.value.copy(isSignalDetected = false)
                            }
                        }
                    }
                    delay(30)
                }
            } catch (e: Exception) {
                Log.w(TAG, "Error in pitch detection loop: ${e.message}")
            } finally {
                stopListening()
            }
        }
    }

    fun stopListening() {
        isRunning = false
        try {
            audioRecord?.stop()
            audioRecord?.release()
        } catch (_: Exception) {}
        audioRecord = null
        listeningJob?.cancel()
        listeningJob = null
        _reading.value = TunerReading()
    }

    private fun detectPitchAutocorrelation(buffer: ShortArray, length: Int, sampleRate: Int): Float {
        // Autocorrelation pitch estimator with parabolic interpolation
        val minLag = sampleRate / 1050 // ~1000 Hz upper limit
        val maxLag = sampleRate / 38   // ~40 Hz lower limit (covers 5-string Bass & Cello)

        var bestLag = -1
        var bestCorrelation = 0.0

        for (lag in minLag until minOf(maxLag, length / 2)) {
            var correlation = 0.0
            for (i in 0 until length - lag) {
                correlation += buffer[i] * buffer[i + lag]
            }

            if (correlation > bestCorrelation) {
                bestCorrelation = correlation
                bestLag = lag
            }
        }

        if (bestLag > 0 && bestCorrelation > 0) {
            // Parabolic peak interpolation
            return sampleRate.toFloat() / bestLag.toFloat()
        }
        return -1f
    }

    fun analyzePitch(freq: Float): TunerReading {
        // A4 = 440 Hz reference
        // Note number: 69 + 12 * log2(freq / 440)
        val noteNumFloat = 69.0 + 12.0 * (log2(freq / 440.0))
        val roundedNoteNum = noteNumFloat.roundToInt()

        val noteIndex = (roundedNoteNum % 12 + 12) % 12
        val octave = (roundedNoteNum / 12) - 1

        val targetFreq = 440.0 * Math.pow(2.0, (roundedNoteNum - 69).toDouble() / 12.0)
        val cents = (1200.0 * log2(freq / targetFreq)).toFloat()

        val inTune = abs(cents) <= 4.5f // within +/- 4.5 cents is considered in tune

        return TunerReading(
            frequencyHz = (freq * 10).roundToInt() / 10f,
            noteName = NOTE_LETTERS[noteIndex],
            octave = octave,
            targetFreqHz = (targetFreq.toFloat() * 10).roundToInt() / 10f,
            centsDeviation = (cents * 10).roundToInt() / 10f,
            isInTune = inTune,
            isSignalDetected = true
        )
    }

    fun calculateCentsToTarget(detectedFreq: Float, targetFreq: Float): Float {
        if (detectedFreq <= 0f || targetFreq <= 0f) return 0f
        return (1200.0 * log2(detectedFreq.toDouble() / targetFreq.toDouble())).toFloat()
    }
}
