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

        setContent {
            val userProfile by userRepository.userProfile.collectAsState()
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
