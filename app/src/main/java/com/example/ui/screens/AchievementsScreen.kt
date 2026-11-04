package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.Achievement
import com.example.model.AppLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AchievementsScreen(
    achievements: List<Achievement>,
    language: AppLanguage,
    onBack: () -> Unit
) {
    val unlockedCount = achievements.count { it.isUnlocked }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Conquistas & Medalhas", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("achievements_back_btn")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(36.dp))
                        Spacer(modifier = Modifier.width(14.dp))
                        Column {
                            Text(
                                text = "$unlockedCount de ${achievements.size} Desbloqueadas",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer)
                            )
                            LinearProgressIndicator(
                                progress = { if (achievements.isNotEmpty()) unlockedCount.toFloat() / achievements.size.toFloat() else 0f },
                                modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
                            )
                        }
                    }
                }
            }

            items(achievements) { ach ->
                Card(
                    modifier = Modifier.fillMaxWidth().testTag("achievement_${ach.id}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (ach.isUnlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (ach.isUnlocked) 2.dp else 0.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(
                                    if (ach.isUnlocked) Color(0xFFFEF3C7) else Color(0xFFE2E8F0),
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (ach.isUnlocked) Icons.Default.EmojiEvents else Icons.Default.Lock,
                                contentDescription = null,
                                tint = if (ach.isUnlocked) Color(0xFFD97706) else Color(0xFF94A3B8)
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = ach.getTitle(language),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (ach.isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = ach.getDescription(language),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = if (ach.isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
                                )
                            )
                        }

                        if (ach.isUnlocked) {
                            SuggestionChip(
                                onClick = {},
                                label = { Text("Conquistado", style = MaterialTheme.typography.labelSmall) }
                            )
                        }
                    }
                }
            }
        }
    }
}
