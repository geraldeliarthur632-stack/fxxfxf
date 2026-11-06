package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.AudioSynthesizer
import com.example.model.Lesson
import com.example.model.QuizQuestion
import com.example.model.UserProfile
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    lesson: Lesson,
    userProfile: UserProfile,
    onBack: () -> Unit,
    onFinishLesson: () -> Unit
) {
    val questions = lesson.questions.ifEmpty {
        listOf(
            QuizQuestion(
                id = "default_${lesson.id}",
                prompt = "Você praticou o exercício prático desta aula no seu instrumento?",
                options = listOf(
                    "Sim, pratiquei e entendi a técnica com clareza!",
                    "Ainda estou treinando, mas compreendi o conceito.",
                    "Vou treinar mais vezes depois."
                ),
                correctIndex = 0,
                explanation = "A repetição diária constrói a memória muscular indispensável para a fluência no instrumento!"
            )
        )
    }

    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerChecked by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val currentQuestion = questions[currentIndex]

    // Animated progress calculation
    val animatedProgress by animateFloatAsState(
        targetValue = (currentIndex + 1).toFloat() / questions.size.toFloat(),
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "exerciseProgress"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Exercício • Aula ${lesson.lessonNumber}", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("exercise_back_button")) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Progress Bar with animated fill
                LinearProgressIndicator(
                    progress = { animatedProgress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pergunta ${currentIndex + 1} de ${questions.size}",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "${(animatedProgress * 100).toInt()}% concluído",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Question Prompt Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.MusicNote,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Desafio Musical",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = currentQuestion.prompt,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                lineHeight = 28.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Animated Option Cards
                currentQuestion.options.forEachIndexed { optIndex, optionText ->
                    val isSelected = selectedOptionIndex == optIndex
                    val optionBg by animateColorAsState(
                        targetValue = when {
                            isAnswerChecked && optIndex == currentQuestion.correctIndex -> Color(0xFFD1FAE5) // light green
                            isAnswerChecked && isSelected && !isCorrect -> Color(0xFFFEE2E2) // light red
                            isSelected -> MaterialTheme.colorScheme.primaryContainer
                            else -> MaterialTheme.colorScheme.surface
                        },
                        animationSpec = tween(durationMillis = 250),
                        label = "optionBg_$optIndex"
                    )

                    val borderColor by animateColorAsState(
                        targetValue = when {
                            isAnswerChecked && optIndex == currentQuestion.correctIndex -> Color(0xFF10B981)
                            isAnswerChecked && isSelected && !isCorrect -> Color(0xFFEF4444)
                            isSelected -> MaterialTheme.colorScheme.primary
                            else -> MaterialTheme.colorScheme.outlineVariant
                        },
                        animationSpec = tween(durationMillis = 250),
                        label = "borderColor_$optIndex"
                    )

                    val cardScale by animateFloatAsState(
                        targetValue = if (isSelected) 1.02f else 1.0f,
                        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                        label = "cardScale_$optIndex"
                    )

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .scale(cardScale)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable(enabled = !isAnswerChecked) {
                                selectedOptionIndex = optIndex
                            }
                            .testTag("exercise_option_$optIndex"),
                        shape = RoundedCornerShape(14.dp),
                        color = optionBg,
                        border = BorderStroke(1.5.dp, borderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(34.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${('A' + optIndex)}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Answer Feedback Banner with Slide and Fade Animation
                AnimatedVisibility(
                    visible = isAnswerChecked,
                    enter = fadeIn(animationSpec = tween(200)) + expandVertically(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isCorrect) Color(0xFFECFDF5) else Color(0xFFFEF2F2)
                        ),
                        border = BorderStroke(1.5.dp, if (isCorrect) Color(0xFF10B981) else Color(0xFFEF4444))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Error,
                                    contentDescription = null,
                                    tint = if (isCorrect) Color(0xFF059669) else Color(0xFFDC2626)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (isCorrect) "Excelente! Resposta correta!" else "Quase lá! Veja a explicação:",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isCorrect) Color(0xFF065F46) else Color(0xFF991B1B)
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentQuestion.explanation,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (isCorrect) Color(0xFF064E3B) else Color(0xFF7F1D1D)
                                )
                            )
                            if (isCorrect) {
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Stars, contentDescription = null, tint = Color(0xFFD97706), modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "+25 XP conquistados",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = Color(0xFFB45309),
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action Button with Pulsing Animation when Ready
            Button(
                onClick = {
                    if (!isAnswerChecked) {
                        if (selectedOptionIndex == null) return@Button
                        isAnswerChecked = true
                        isCorrect = selectedOptionIndex == currentQuestion.correctIndex
                        scope.launch {
                            if (isCorrect) {
                                AudioSynthesizer.playSuccessChime()
                            } else {
                                AudioSynthesizer.playErrorTone()
                            }
                        }
                    } else {
                        // Advance to next question or complete
                        if (currentIndex + 1 < questions.size) {
                            currentIndex++
                            selectedOptionIndex = null
                            isAnswerChecked = false
                            isCorrect = false
                        } else {
                            onFinishLesson()
                        }
                    }
                },
                enabled = selectedOptionIndex != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("exercise_check_continue_btn"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isAnswerChecked && isCorrect) Color(0xFF10B981) else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = when {
                        !isAnswerChecked -> "Verificar Resposta"
                        currentIndex + 1 < questions.size -> "Próxima Pergunta"
                        else -> "Concluir Aula!"
                    },
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
