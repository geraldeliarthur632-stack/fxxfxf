package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.CourseRepository
import com.example.model.*
import com.example.ui.components.CoinBadge
import com.example.ui.components.MascotBubble
import com.example.ui.components.StreakBadge
import com.example.ui.components.StreakCounterCard

@Composable
fun HomeScreen(
    userProfile: UserProfile,
    onNavigatePath: () -> Unit,
    onStartLesson: (String) -> Unit,
    onSwitchInstrument: () -> Unit,
    onNavigateStore: () -> Unit,
    onNavigateAd: () -> Unit,
    onNavigateProfile: () -> Unit,
    onNavigateAchievements: () -> Unit,
    onNavigateHistory: () -> Unit,
    onNavigateSettings: () -> Unit,
    onNavigateSupport: () -> Unit,
    onNavigateTuner: () -> Unit
) {
    val instrument = userProfile.selectedInstrument
    val lessons = CourseRepository.getLessonsForInstrument(instrument)
    val completedCount = lessons.count { userProfile.completedLessonIds.contains(it.id) }
    val nextLesson = lessons.firstOrNull { !userProfile.completedLessonIds.contains(it.id) } ?: lessons.first()
    val progressFraction = if (lessons.isNotEmpty()) completedCount.toFloat() / lessons.size.toFloat() else 0f

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onNavigateProfile() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.mascot_pipo),
                            contentDescription = "Perfil",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = userProfile.nickname,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Nível ${userProfile.userLevel} • ${userProfile.totalXp} XP",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    StreakBadge(streakDays = userProfile.currentStreak)
                    Spacer(modifier = Modifier.width(8.dp))
                    CoinBadge(
                        userProfile = userProfile,
                        onStoreClick = onNavigateStore,
                        onWatchAdClick = onNavigateAd
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Current Instrument Banner
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_instrument_banner"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = instrument.drawableRes),
                        contentDescription = instrument.getName(userProfile.language),
                        modifier = Modifier
                            .size(76.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Instrumento Atual:",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f))
                        )
                        Text(
                            text = instrument.getName(userProfile.language),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            LinearProgressIndicator(
                                progress = { progressFraction },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "$completedCount/90",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                    IconButton(
                        onClick = onSwitchInstrument,
                        modifier = Modifier.testTag("home_switch_instrument_button")
                    ) {
                        Icon(Icons.Default.SwapHoriz, contentDescription = "Trocar Instrumento")
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Next Lesson CTA Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_next_lesson_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SuggestionChip(
                            onClick = {},
                            label = { Text("Aula ${nextLesson.lessonNumber} de 90") }
                        )
                        Text(
                            text = nextLesson.moduleTitle,
                            style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = nextLesson.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = nextLesson.objective,
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = { onStartLesson(nextLesson.id) },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("home_start_lesson_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (userProfile.unlockedLessonIds.contains(nextLesson.id) || userProfile.completedLessonIds.contains(nextLesson.id))
                                    "Continuar Aula"
                                else
                                    "Iniciar Aula (1 Moeda)",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        OutlinedButton(
                            onClick = onNavigatePath,
                            modifier = Modifier
                                .height(48.dp)
                                .testTag("home_view_path_button"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Route, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Ver Caminho")
                        }
                    }
                }
            }

            // Streak Counter Consistency Component
            StreakCounterCard(
                streakDays = userProfile.currentStreak,
                language = userProfile.language,
                onLearnMoreClick = onNavigateAchievements
            )

            Spacer(modifier = Modifier.height(14.dp))

            MascotBubble(
                message = if (userProfile.currentStreak > 0)
                    "Incrível! Você tem ${userProfile.currentStreak} dias consecutivos de prática musical. Continue assim hoje para não apagar a chama!"
                else
                    "Estude sua aula de hoje para iniciar sua sequência de prática e ganhar bônus de XP!",
                mascotName = "Maestro Pipo"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Studio Tuner Hero Action Card
            Card(
                onClick = onNavigateTuner,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("home_open_tuner_button"),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.GraphicEq,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Afinador Studio",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        )
                        Text(
                            text = "Afine ${instrument.getName(userProfile.language)} com microfone ou ouça as notas de referência",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.85f)
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Abrir Afinador",
                        tint = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Quick Access Grid
            Text(
                text = "Recursos & Atividades:",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    onClick = onNavigateStore,
                    modifier = Modifier.weight(1f).testTag("quick_store"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF3C7))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.Storefront, contentDescription = null, tint = Color(0xFFD97706))
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Loja de Moedas", fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                        Text("R$ 0,10 por moeda", style = MaterialTheme.typography.labelSmall, color = Color(0xFFB45309))
                    }
                }

                Card(
                    onClick = onNavigateAd,
                    modifier = Modifier.weight(1f).testTag("quick_ad"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCFCE7))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.PlayCircle, contentDescription = null, tint = Color(0xFF16A34A))
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Ganhar Moeda", fontWeight = FontWeight.Bold, color = Color(0xFF166534))
                        Text("Assistir anúncio", style = MaterialTheme.typography.labelSmall, color = Color(0xFF15803D))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    onClick = onNavigateAchievements,
                    modifier = Modifier.weight(1f).testTag("quick_achievements"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.EmojiEvents, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Conquistas", fontWeight = FontWeight.Bold)
                        Text("Medalhas & Troféus", style = MaterialTheme.typography.labelSmall)
                    }
                }

                Card(
                    onClick = onNavigateHistory,
                    modifier = Modifier.weight(1f).testTag("quick_history"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.History, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Histórico", fontWeight = FontWeight.Bold)
                        Text("Revisar aulas grátis", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Card(
                    onClick = onNavigateSupport,
                    modifier = Modifier.weight(1f).testTag("quick_support"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.Tune, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Afinador & Dicas", fontWeight = FontWeight.Bold)
                        Text("Guias e afinações", style = MaterialTheme.typography.labelSmall)
                    }
                }

                Card(
                    onClick = onNavigateSettings,
                    modifier = Modifier.weight(1f).testTag("quick_settings"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Icon(Icons.Default.Settings, contentDescription = null, tint = MaterialTheme.colorScheme.outline)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text("Configurações", fontWeight = FontWeight.Bold)
                        Text("Tema, som, idioma", style = MaterialTheme.typography.labelSmall)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
