package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.AppLanguage
import com.example.model.AppStrings
import com.example.ui.components.MascotBubble

@Composable
fun NicknameScreen(
    language: AppLanguage,
    initialNickname: String,
    onConfirm: (nickname: String, avatarId: String) -> Unit
) {
    var nickname by remember { mutableStateOf(initialNickname) }
    var selectedAvatar by remember { mutableStateOf("avatar_pipo") }

    val avatars = listOf(
        Pair("avatar_pipo", R.drawable.mascot_pipo),
        Pair("avatar_guitar", R.drawable.instrument_guitar),
        Pair("avatar_viola", R.drawable.instrument_viola),
        Pair("avatar_piano", R.drawable.instrument_piano),
        Pair("avatar_keyboard", R.drawable.instrument_keyboard)
    )

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = AppStrings.get("nickname_title", language),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = AppStrings.get("nickname_subtitle", language),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                MascotBubble(
                    message = "Escolha um apelido musical e o avatar que vai te acompanhar em cada lição!",
                    mascotName = "Maestro Pipo"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Avatar Chooser Row
                Text(
                    text = "Escolha seu Avatar Musical:",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    avatars.forEach { (avatarId, resId) ->
                        val isSelected = selectedAvatar == avatarId
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant)
                                .border(
                                    width = if (isSelected) 3.dp else 1.dp,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                                    shape = CircleShape
                                )
                                .clickable { selectedAvatar = avatarId }
                                .testTag("avatar_option_$avatarId"),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = resId),
                                contentDescription = avatarId,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            if (isSelected) {
                                Box(
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.BottomEnd)
                                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text(AppStrings.get("nickname_label", language)) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("nickname_input_field"),
                    shape = RoundedCornerShape(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val finalNickname = if (nickname.isBlank()) "Músico Curioso" else nickname.trim()
                    onConfirm(finalNickname, selectedAvatar)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("nickname_confirm_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = AppStrings.get("start_studying", language),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
