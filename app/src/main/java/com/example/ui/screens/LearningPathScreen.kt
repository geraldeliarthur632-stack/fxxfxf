package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.CourseRepository
import com.example.model.*
import com.example.ui.components.CoinBadge
import com.example.ui.components.StreakBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearningPathScreen(
    userProfile: UserProfile,
    onBack: () -> Unit,
    onLessonClick: (String) -> Unit,
    onStoreClick: () -> Unit,
    onWatchAdClick: () -> Unit
) {
    val instrument = userProfile.selectedInstrument
    val modules = CourseRepository.getModules()
    val allLessons = CourseRepository.getLessonsForInstrument(instrument)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = instrument.getName(userProfile.language),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Caminho de Aprendizagem (90 Aulas)",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("path_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    StreakBadge(streakDays = userProfile.currentStreak)
                    Spacer(modifier = Modifier.width(6.dp))
                    CoinBadge(
                        userProfile = userProfile,
                        onStoreClick = onStoreClick,
                        onWatchAdClick = onWatchAdClick
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            modules.forEach { moduleInfo ->
                item {
                    // Module Banner
                    ModuleHeaderBanner(
                        moduleInfo = moduleInfo,
                        language = userProfile.language
                    )
                }

                val moduleLessons = allLessons.filter { it.moduleNumber == moduleInfo.moduleNumber }
                itemsIndexed(moduleLessons) { index, lesson ->
                    val isCompleted = userProfile.completedLessonIds.contains(lesson.id)
                    val isUnlocked = userProfile.unlockedLessonIds.contains(lesson.id)

                    // Find if this is the current active lesson (first uncompleted)
                    val isCurrent = !isCompleted && allLessons.firstOrNull { !userProfile.completedLessonIds.contains(it.id) }?.id == lesson.id

                    // A lesson is unlocked if it's the first one, or previous lesson was completed, or explicitly unlocked with coin
                    val isAccessible = isCompleted || isUnlocked || isCurrent || lesson.lessonNumber == 1

                    // Zig-zag offset for winding Duolingo path: 0 -> center, 1 -> right, 2 -> center, 3 -> left
                    val offsetAlignment = when (index % 4) {
                        0 -> Alignment.Center
                        1 -> Alignment.CenterEnd
                        2 -> Alignment.Center
                        else -> Alignment.CenterStart
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 8.dp),
                        contentAlignment = offsetAlignment
                    ) {
                        PathLessonNode(
                            lesson = lesson,
                            isCompleted = isCompleted,
                            isCurrent = isCurrent,
                            isAccessible = isAccessible,
                            onClick = {
                                onLessonClick(lesson.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ModuleHeaderBanner(
    moduleInfo: ModuleInfo,
    language: AppLanguage
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
            .testTag("module_banner_${moduleInfo.moduleNumber}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.primary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${moduleInfo.moduleNumber}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = moduleInfo.getTitle(language),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                    Text(
                        text = "Aulas ${moduleInfo.startLesson} a ${moduleInfo.endLesson} (${moduleInfo.totalLessonsInModule} aulas)",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = when (language) {
                    AppLanguage.PT -> moduleInfo.descPt
                    AppLanguage.EN -> moduleInfo.descEn
                    AppLanguage.ES -> moduleInfo.descEs
                },
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                )
            )
        }
    }
}

@Composable
fun PathLessonNode(
    lesson: Lesson,
    isCompleted: Boolean,
    isCurrent: Boolean,
    isAccessible: Boolean,
    onClick: () -> Unit
) {
    val nodeBg = when {
        isCompleted -> Color(0xFF10B981) // Emerald check
        isCurrent -> MaterialTheme.colorScheme.primary // Active
        isAccessible -> MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
        else -> Color(0xFF9CA3AF) // Locked gray
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(enabled = isAccessible) { onClick() }
            .testTag("path_node_${lesson.lessonNumber}")
    ) {
        if (isCurrent) {
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF59E0B),
                modifier = Modifier.padding(bottom = 4.dp)
            ) {
                Text(
                    text = "Começar!",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .size(if (isCurrent) 70.dp else 64.dp)
                .clip(CircleShape)
                .background(nodeBg)
                .border(
                    width = if (isCurrent) 3.5.dp else 2.dp,
                    color = if (isCurrent) Color(0xFFFBBF24) else Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            when {
                isCompleted -> {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Concluída",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                isCurrent -> {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Aula Atual",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }
                isAccessible -> {
                    Text(
                        text = "${lesson.lessonNumber}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                else -> {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Bloqueada",
                        tint = Color(0xFFD1D5DB),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Aula ${lesson.lessonNumber}",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Normal,
                color = if (isAccessible) MaterialTheme.colorScheme.onBackground else Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }
}
