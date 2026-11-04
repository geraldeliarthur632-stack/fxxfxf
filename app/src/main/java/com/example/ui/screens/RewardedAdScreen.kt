package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.audio.AudioSynthesizer
import com.example.data.AdMobManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardedAdScreen(
    onBack: () -> Unit,
    onRewardEarned: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isPlayingAd by remember { mutableStateOf(false) }
    var adProgress by remember { mutableFloatStateOf(0f) }
    var secondsRemaining by remember { mutableIntStateOf(5) }
    var isCompleted by remember { mutableStateOf(false) }
    var rewardGranted by remember { mutableStateOf(false) }
    var adClosedEarly by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Anúncio Recompensado", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (isPlayingAd && !isCompleted) {
                                adClosedEarly = true
                            } else {
                                onBack()
                            }
                        },
                        modifier = Modifier.testTag("ad_back_btn")
                    ) {
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Header Badge
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color(0xFFDCFCE7),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF16A34A))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Stars, contentDescription = null, tint = Color(0xFF16A34A), modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Recompensa: +1 Nota Musical",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = Color(0xFF166534))
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                if (!isPlayingAd && !isCompleted) {
                    Text(
                        text = "Assista a um vídeo curto para ganhar 1 moeda grátis!",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "O vídeo tem duração de apenas 5 segundos no modo de testes. Ao assistir até o final, exatamente 1 moeda será creditada em sua conta.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Configuração Google AdMob:", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "App ID de Teste: ${AdMobManager.TEST_APP_ID}\nAd Unit ID: ${AdMobManager.getActiveRewardedAdUnitId()}",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.outline)
                            )
                        }
                    }
                } else if (isPlayingAd && !isCompleted) {
                    // Video player simulation container
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(
                                progress = { adProgress },
                                modifier = Modifier.size(64.dp),
                                color = Color(0xFFF59E0B),
                                strokeWidth = 5.dp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Exibindo Anúncio Patrocinado...",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Aguarde $secondsRemaining segundos para receber a moeda",
                                color = Color(0xFF94A3B8),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                } else if (isCompleted) {
                    // Reward Granted Success State
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDCFCE7))
                            .border(3.dp, Color(0xFF16A34A), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Sucesso",
                            tint = Color(0xFF16A34A),
                            modifier = Modifier.size(56.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Parabéns! +1 Moeda Creditada!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF166534)
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Você concluiu o anúncio com sucesso e sua recompensa já está disponível para desbloquear sua próxima aula.",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                        textAlign = TextAlign.Center
                    )
                }

                if (adClosedEarly) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFEF2F2)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEF4444))
                    ) {
                        Text(
                            text = "Atenção: O anúncio foi fechado antes do término. Nenhuma moeda foi creditada.",
                            color = Color(0xFF991B1B),
                            modifier = Modifier.padding(14.dp),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }

            // Bottom Buttons
            if (!isPlayingAd && !isCompleted) {
                Button(
                    onClick = {
                        isPlayingAd = true
                        adClosedEarly = false
                        scope.launch {
                            val totalSeconds = 5
                            for (s in totalSeconds downTo 1) {
                                secondsRemaining = s
                                adProgress = (totalSeconds - s + 1).toFloat() / totalSeconds.toFloat()
                                delay(1000L)
                            }
                            isCompleted = true
                            isPlayingAd = false
                            if (!rewardGranted) {
                                rewardGranted = true
                                onRewardEarned()
                                AudioSynthesizer.playSuccessChime()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("start_ad_button"),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF16A34A))
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Assistir Anúncio Agora", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                }
            } else if (isCompleted) {
                Button(
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("ad_finish_button"),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text("Voltar aos Estudos", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                }
            }
        }
    }
}
