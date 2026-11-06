package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.AppLanguage

@Composable
fun StreakBadge(
    streakDays: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val infiniteTransition = rememberInfiniteTransition(label = "streakPulse")
    val fireScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = if (streakDays > 0) 1.22f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 650, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "fireScale"
    )

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (streakDays > 0) Color(0xFFFFEDD5) else MaterialTheme.colorScheme.surfaceVariant,
        border = BorderStroke(1.5.dp, if (streakDays > 0) Color(0xFFF97316) else Color(0xFF9CA3AF)),
        modifier = modifier
            .testTag("streak_badge")
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocalFireDepartment,
                contentDescription = "Ofensiva",
                tint = if (streakDays > 0) Color(0xFFEA580C) else Color(0xFF6B7280),
                modifier = Modifier
                    .size(20.dp)
                    .scale(fireScale)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$streakDays",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = if (streakDays > 0) Color(0xFF9A3412) else Color(0xFF374151)
                )
            )
        }
    }
}

/**
 * Full Streak Counter Card that highlights consistency,
 * shows a 7-day progress tracker, consecutive milestones, and motivational rewards.
 */
@Composable
fun StreakCounterCard(
    streakDays: Int,
    language: AppLanguage,
    onLearnMoreClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "cardFlame")
    val flameScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "cardFlameScale"
    )

    // Milestone calculation
    val nextMilestone = when {
        streakDays < 3 -> 3
        streakDays < 7 -> 7
        streakDays < 14 -> 14
        streakDays < 30 -> 30
        streakDays < 60 -> 60
        else -> ((streakDays / 30) + 1) * 30
    }
    val daysRemaining = (nextMilestone - streakDays).coerceAtLeast(1)

    val weekDaysLabels = remember(language) {
        when (language) {
            AppLanguage.PT -> listOf("Seg", "Ter", "Qua", "Qui", "Sex", "Sáb", "Dom")
            AppLanguage.EN -> listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            AppLanguage.ES -> listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")
        }
    }

    // Determine current day of week (0 to 6)
    val currentDayIndex = remember {
        val calendar = java.util.Calendar.getInstance()
        // Sunday is 1, Monday is 2, Saturday is 7
        val dow = calendar.get(java.util.Calendar.DAY_OF_WEEK)
        // Convert to Monday=0 .. Sunday=6
        if (dow == java.util.Calendar.SUNDAY) 6 else dow - 2
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("streak_counter_card"),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        border = BorderStroke(1.5.dp, Color(0xFFF97316).copy(alpha = 0.6f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFF7ED),
                            Color(0xFFFFEDD5)
                        )
                    )
                )
                .padding(18.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .scale(flameScale)
                                .clip(CircleShape)
                                .background(Color(0xFFF97316)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = when (language) {
                                    AppLanguage.PT -> "$streakDays ${if (streakDays == 1) "Dia de Ofensiva" else "Dias de Ofensiva"}!"
                                    AppLanguage.EN -> "$streakDays-Day Streak!"
                                    AppLanguage.ES -> "¡Racha de $streakDays ${if (streakDays == 1) "Día" else "Días"}!"
                                },
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFF9A3412)
                                )
                            )
                            Text(
                                text = when (language) {
                                    AppLanguage.PT -> if (streakDays > 0) "Sua constância musical está em chamas!" else "Pratique hoje para acender sua chama!"
                                    AppLanguage.EN -> if (streakDays > 0) "Your musical consistency is on fire!" else "Practice today to light your flame!"
                                    AppLanguage.ES -> if (streakDays > 0) "¡Tu constancia musical está que arde!" else "¡Practica hoy para encender tu llama!"
                                },
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = Color(0xFFC2410C),
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }

                    // Reward badge
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFEA580C),
                        contentColor = Color.White
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Stars, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "+${(streakDays * 5).coerceAtLeast(10)} XP",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 7-Day Visual Track Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    weekDaysLabels.forEachIndexed { index, dayName ->
                        val isDayDone = index <= currentDayIndex && streakDays > (currentDayIndex - index)
                        val isToday = index == currentDayIndex

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = dayName,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isToday) FontWeight.ExtraBold else FontWeight.Normal,
                                    color = if (isToday) Color(0xFFC2410C) else Color(0xFF9A3412).copy(alpha = 0.7f),
                                    fontSize = 10.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when {
                                            isDayDone -> Color(0xFFEA580C)
                                            isToday -> Color(0xFFFED7AA)
                                            else -> Color.White.copy(alpha = 0.8f)
                                        }
                                    )
                                    .border(
                                        width = if (isToday) 2.dp else 1.dp,
                                        color = if (isToday) Color(0xFFEA580C) else Color(0xFFFDBA74),
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isDayDone) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                } else if (isToday) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = null,
                                        tint = Color(0xFFEA580C),
                                        modifier = Modifier.size(18.dp)
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFFFDBA74))
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Consistency Milestone Footer
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.65f),
                    border = BorderStroke(1.dp, Color(0xFFFED7AA))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color(0xFFEA580C),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = when (language) {
                                    AppLanguage.PT -> "Próxima medalha em $daysRemaining ${if (daysRemaining == 1) "dia" else "dias"} ($nextMilestone dias consecutivos)!"
                                    AppLanguage.EN -> "Next badge in $daysRemaining ${if (daysRemaining == 1) "day" else "days"} ($nextMilestone days goal)!"
                                    AppLanguage.ES -> "¡Próxima medalla en $daysRemaining ${if (daysRemaining == 1) "día" else "días"} (meta de $nextMilestone días)!"
                                },
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF9A3412),
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}
