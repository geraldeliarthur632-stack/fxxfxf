package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.data.UserPreferencesRepository
import com.example.ui.navigation.AppNavigation
import com.example.ui.theme.MundoDaMusicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userRepository = UserPreferencesRepository(applicationContext)

        // Request POST_NOTIFICATIONS on Android 13+
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            val permissionCheck = androidx.core.content.ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            )
            if (permissionCheck != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                androidx.core.app.ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }

        setContent {
            val userProfile by userRepository.userProfile.collectAsState()

            // Automatically subscribe to practice reminders whenever user updates selected instrument or language
            androidx.compose.runtime.LaunchedEffect(userProfile.selectedInstrument, userProfile.language) {
                com.example.service.FcmNotificationHelper.setupPracticeReminders(
                    context = applicationContext,
                    instrument = userProfile.selectedInstrument,
                    language = userProfile.language
                )
            }

            val systemDark = isSystemInDarkTheme()
            val isDark = userProfile.isDarkMode || systemDark

            MundoDaMusicaTheme(darkTheme = userProfile.isDarkMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(userRepository = userRepository)
                }
            }
        }
    }
}
