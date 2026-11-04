package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.AppStrings
import com.example.model.UserProfile

@Composable
fun CoinBadge(
    userProfile: UserProfile,
    onStoreClick: () -> Unit,
    onWatchAdClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFFEF3C7),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFF59E0B)),
        modifier = modifier
            .clickable { showDialog = true }
            .testTag("coin_badge")
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(22.dp)
                    .background(Color(0xFFF59E0B), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = "Moedas",
                    tint = Color.White,
                    modifier = Modifier.size(15.dp)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "${userProfile.coins}",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF92400E)
                )
            )

            if (userProfile.coins < UserProfile.MAX_FREE_COINS) {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Recarga em andamento",
                    tint = Color(0xFFB45309),
                    modifier = Modifier.size(13.dp)
                )
            }
        }
    }

    if (showDialog) {
        val remainingMillis = userProfile.calculateRemainingRefillMillis()
        val hours = (remainingMillis / (1000 * 60 * 60)).toInt()
        val minutes = ((remainingMillis / (1000 * 60)) % 60).toInt()
        val timeFormatted = String.format("%02dh %02dm", hours, minutes)

        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFFF59E0B), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.MusicNote, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${userProfile.coins} ${AppStrings.get("notes_coins", userProfile.language)}",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            },
            text = {
                Column {
                    Text(
                        text = "Cada nova aula iniciada consome 1 moeda. As aulas que você já desbloqueou podem ser revistas gratuitamente para sempre!",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Schedule, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = AppStrings.get("refill_timer_label", userProfile.language),
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            if (userProfile.coins >= UserProfile.MAX_FREE_COINS) {
                                Text(
                                    text = "Suas moedas gratuitas estão cheias (3/3)!",
                                    style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF059669), fontWeight = FontWeight.Bold)
                                )
                            } else {
                                Text(
                                    text = "Faltam aprox. $timeFormatted",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Text(
                                    text = AppStrings.get("coins_refill_info", userProfile.language),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onStoreClick()
                    },
                    modifier = Modifier.testTag("dialog_store_button")
                ) {
                    Text(AppStrings.get("buy_coins", userProfile.language))
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = {
                        showDialog = false
                        onWatchAdClick()
                    },
                    modifier = Modifier.testTag("dialog_watch_ad_button")
                ) {
                    Text(AppStrings.get("watch_ad", userProfile.language))
                }
            }
        )
    }
}
