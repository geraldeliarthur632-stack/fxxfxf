package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.audio.AudioSynthesizer
import com.example.model.Lesson
import com.example.model.UserProfile
import com.example.ui.components.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    lesson: Lesson,
    userProfile: UserProfile,
    onBack: () -> Unit,
    onStartExercise: () -> Unit,
    onNeedCoinsForLesson: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isPlayingAudio by remember { mutableStateOf(false) }
    var showInsufficientCoinsDialog by remember { mutableStateOf(false) }

    val isAlreadyUnlocked = userProfile.unlockedLessonIds.contains(lesson.id) || userProfile.completedLessonIds.contains(lesson.id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Aula ${lesson.lessonNumber}: ${lesson.title}",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1
                        )
                        Text(
                            text = lesson.moduleTitle,
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("lesson_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    CoinBadge(
                        userProfile = userProfile,
                        onStoreClick = onNeedCoinsForLesson,
                        onWatchAdClick = onNeedCoinsForLesson
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(18.dp)
        ) {
            // Objective Header Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.6f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Objetivo da Aula:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        )
                        Text(
                            text = lesson.objective,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Didactic Content
            Text(
                text = "Conteúdo Teórico & Prático",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = lesson.didacticContent,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Visual Diagrams (Chord for string instruments, Piano diagram for keys)
            if (lesson.chordDiagram != null) {
                Text(
                    text = "Diagrama de Acordes:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                FretboardDiagram(
                    chordData = lesson.chordDiagram,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (lesson.pianoDiagram != null) {
                Text(
                    text = "Posição das Teclas:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                PianoKeyboardDiagram(
                    diagramData = lesson.pianoDiagram,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Real Synthesizer Audio Demonstration
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Demonstração Sonora Real",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Ouça as notas sintetizadas desta aula afinadas em tempo real.",
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }

                    FilledTonalButton(
                        onClick = {
                            if (!isPlayingAudio) {
                                isPlayingAudio = true
                                scope.launch {
                                    val freqs = lesson.audioNotes.map { it.frequencyHz }
                                    if (freqs.isNotEmpty()) {
                                        AudioSynthesizer.playArpeggio(freqs)
                                    } else {
                                        AudioSynthesizer.playTone(440.0, 500)
                                    }
                                    isPlayingAudio = false
                                }
                            }
                        },
                        modifier = Modifier.testTag("lesson_play_audio_btn")
                    ) {
                        Icon(
                            imageVector = if (isPlayingAudio) Icons.Default.GraphicEq else Icons.Default.VolumeUp,
                            contentDescription = "Tocar Som"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (isPlayingAudio) "Tocando..." else "Ouvir")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Practical Exercise
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Exercício Prático:",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = lesson.practicalExercise,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            MascotBubble(
                message = "Treine o exercício prático no seu instrumento! Quando estiver pronto, faça as perguntas interativas para concluir a aula!",
                mascotName = "Maestro Pipo"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Start Exercise / Next Button
            Button(
                onClick = {
                    if (isAlreadyUnlocked || userProfile.coins >= 1) {
                        onStartExercise()
                    } else {
                        showInsufficientCoinsDialog = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("lesson_start_exercise_button"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = if (isAlreadyUnlocked)
                        "Praticar Exercício Interativo (Livre)"
                    else
                        "Iniciar Exercício (1 Moeda)",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    if (showInsufficientCoinsDialog) {
        AlertDialog(
            onDismissRequest = { showInsufficientCoinsDialog = false },
            icon = { Icon(Icons.Default.MusicOff, contentDescription = null, tint = MaterialTheme.colorScheme.error) },
            title = { Text("Suas moedas acabaram!") },
            text = {
                Text("Para iniciar uma nova aula, você precisa de 1 moeda (Nota Musical). Você pode assistir a um anúncio gratuito para ganhar 1 moeda ou comprar mais na loja!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showInsufficientCoinsDialog = false
                        onNeedCoinsForLesson()
                    }
                ) {
                    Text("Obter Moedas")
                }
            },
            dismissButton = {
                TextButton(onClick = { showInsufficientCoinsDialog = false }) {
                    Text("Voltar")
                }
            }
        )
    }
}
