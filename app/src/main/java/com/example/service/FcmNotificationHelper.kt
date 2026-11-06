package com.example.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.MainActivity
import com.example.R
import com.example.model.AppLanguage
import com.example.model.InstrumentType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

object FcmNotificationHelper {

    private const val TAG = "FcmNotificationHelper"

    /**
     * Initializes FCM, retrieves token and subscribes user to general and instrument-specific practice topics.
     * Also saves the token and selected instrument in Firestore under /users/{userId}.
     */
    fun setupPracticeReminders(
        context: Context,
        instrument: InstrumentType,
        language: AppLanguage
    ) {
        try {
            val messaging = FirebaseMessaging.getInstance()

            // Subscribe to all-users daily reminder topic
            messaging.subscribeToTopic("daily_practice_all")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Subscribed to topic: daily_practice_all")
                    }
                }

            // Unsubscribe from previous other instruments and subscribe to currently selected instrument
            InstrumentType.values().forEach { inst ->
                val topicName = "practice_${inst.id}"
                if (inst == instrument) {
                    messaging.subscribeToTopic(topicName)
                        .addOnSuccessListener {
                            Log.d(TAG, "Subscribed to instrument practice topic: $topicName")
                        }
                } else {
                    messaging.unsubscribeFromTopic(topicName)
                }
            }

            // Subscribe to language topic
            messaging.subscribeToTopic("lang_${language.code}")

            // Retrieve registration token
            messaging.token.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Log.d(TAG, "FCM Token: $token")
                    saveTokenToFirestore(context, token, instrument, language)
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error configuring FCM reminders", e)
        }
    }

    private fun saveTokenToFirestore(
        context: Context,
        token: String,
        instrument: InstrumentType,
        language: AppLanguage
    ) {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        try {
            val dbId = context.getString(R.string.firestore_database_id)
            val db = FirebaseFirestore.getInstance(dbId)
            val tokenData = mapOf(
                "fcmToken" to token,
                "fcmInstrument" to instrument.id,
                "fcmLanguage" to language.code,
                "fcmUpdatedAt" to com.google.firebase.Timestamp.now()
            )
            db.collection("users").document(user.uid)
                .set(tokenData, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d(TAG, "FCM token synced to Firestore for user: ${user.uid}")
                }
        } catch (e: Exception) {
            Log.w(TAG, "Failed to sync FCM token to Firestore", e)
        }
    }

    /**
     * Shows a local test reminder in the notification bar so the user can immediately experience
     * the 'time to practice' reminder for their instrument.
     */
    fun sendLocalPracticeNotification(
        context: Context,
        instrument: InstrumentType,
        language: AppLanguage
    ) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = MusicAppFirebaseMessagingService.CHANNEL_PRACTICE_REMINDER

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Lembretes de Prática Diária",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Lembrete diário de prática musical do Mundo da Música"
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val instrumentName = instrument.getName(language)
        val title = when (language) {
            AppLanguage.PT -> "Hora de Praticar $instrumentName!"
            AppLanguage.EN -> "Time to practice your $instrumentName!"
            AppLanguage.ES -> "¡Hora de practicar tu $instrumentName!"
        }

        val body = when (language) {
            AppLanguage.PT -> "Mantenha sua ofensiva em chamas! Dedique 10 minutinhos no estúdio hoje para dominar sua próxima aula."
            AppLanguage.EN -> "Keep your streak on fire! Spend 10 minutes in the studio today to master your next lesson."
            AppLanguage.ES -> "¡Mantén tu racha encendida! Dedica 10 minutos en el estudio hoy para dominar tu próxima lección."
        }

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.app_logo_modern)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(MusicAppFirebaseMessagingService.NOTIFICATION_ID_PRACTICE, notification)
    }
}
