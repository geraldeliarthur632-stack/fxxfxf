package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.AppLanguage
import com.example.model.AppStrings
import com.example.model.UserProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    userProfile: UserProfile,
    onBack: () -> Unit,
    onLanguageChange: (AppLanguage) -> Unit,
    onDarkModeToggle: (Boolean) -> Unit,
    onSoundEffectsToggle: (Boolean) -> Unit,
    onHapticsToggle: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    var showLogoutDialog by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }

    val deleteAccountUrl = "https://ais-pre-j252vjkxcnwonriwys64o4-66499204937.us-east1.run.app/excluir-conta.html"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppStrings.get("settings", userProfile.language), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("settings_back_btn")) {
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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Language Selection Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Language, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Idioma do Aplicativo", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        AppLanguage.entries.forEach { lang ->
                            FilterChip(
                                selected = userProfile.language == lang,
                                onClick = { onLanguageChange(lang) },
                                label = { Text("${lang.flag} ${lang.displayName}") },
                                modifier = Modifier.testTag("settings_lang_${lang.code}")
                            )
                        }
                    }
                }
            }

            // Visual & Sound Preferences Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Aparência & Sons", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.DarkMode, contentDescription = null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(AppStrings.get("dark_mode", userProfile.language))
                        }
                        Switch(
                            checked = userProfile.isDarkMode,
                            onCheckedChange = onDarkModeToggle,
                            modifier = Modifier.testTag("switch_dark_mode")
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.VolumeUp, contentDescription = null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(AppStrings.get("sound_effects", userProfile.language))
                        }
                        Switch(
                            checked = userProfile.soundEffectsEnabled,
                            onCheckedChange = onSoundEffectsToggle,
                            modifier = Modifier.testTag("switch_sound_effects")
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Vibration, contentDescription = null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(AppStrings.get("haptic_feedback", userProfile.language))
                        }
                        Switch(
                            checked = userProfile.hapticFeedbackEnabled,
                            onCheckedChange = onHapticsToggle,
                            modifier = Modifier.testTag("switch_haptics")
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                    // FCM Daily Practice Reminders
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.NotificationsActive, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text("Lembretes Diários de Prática", fontWeight = FontWeight.SemiBold)
                                    Text(
                                        "FCM ativo para ${userProfile.selectedInstrument.getName(userProfile.language)}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        FilledTonalButton(
                            onClick = {
                                com.example.service.FcmNotificationHelper.sendLocalPracticeNotification(
                                    context = context,
                                    instrument = userProfile.selectedInstrument,
                                    language = userProfile.language
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_test_fcm_notification"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.Default.NotificationImportant, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Testar Notificação Diária Agora")
                        }
                    }
                }
            }

            // 12-Hour Automatic Coin Timer Information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Schedule, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Regra das Moedas e Recarga de 12h", style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "• Cada aluno começa com 3 moedas gratuitas.\n• Cada aula iniciada consome 1 moeda uma única vez (revisitar é sempre grátis).\n• A cada 12 horas, suas moedas são recarregadas automaticamente até o limite de 3.\n• Você pode assistir a anúncios ou adquirir moedas por R$ 0,10 a qualquer momento.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Privacy & Account Deletion Section (Obrigatório Google Play)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("settings_account_deletion_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.25f)
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.4f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Security,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Privacidade & Exclusão de Conta",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.error
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Conforme as diretrizes obrigatórias da Google Play Store, você pode solicitar a exclusão permanente de sua conta e de todos os seus dados a qualquer momento via link web ou diretamente no aplicativo:",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Link Web Obrigatório de Exclusão (Google Play Requirement)
                    Surface(
                        onClick = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deleteAccountUrl))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Erro ao abrir navegador: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                        },
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("link_excluir_conta_web")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        Icons.Default.Link,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "Link Obrigatório: Excluir Conta (Web)",
                                        style = MaterialTheme.typography.labelLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = deleteAccountUrl,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )
                                Text(
                                    text = "Toque para abrir a página web externa de solicitação de exclusão",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                            Icon(
                                Icons.AutoMirrored.Filled.OpenInNew,
                                contentDescription = "Abrir Link Externo",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = {
                                clipboardManager.setText(AnnotatedString(deleteAccountUrl))
                                Toast.makeText(context, "Link copiado! Cole no Google Play Console.", Toast.LENGTH_LONG).show()
                            },
                            modifier = Modifier.testTag("btn_copy_delete_link")
                        ) {
                            Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Copiar Link Web")
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

                    // Botão Excluir Conta no App
                    Button(
                        onClick = { showDeleteAccountDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("btn_excluir_conta_app"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Default.DeleteForever, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Excluir Minha Conta e Dados Agora", fontWeight = FontWeight.Bold)
                    }
                }
            }

            // Logout / Reset
            OutlinedButton(
                onClick = { showLogoutDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("settings_logout_btn"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(AppStrings.get("logout", userProfile.language))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF1A0438))
                        .border(1.5.dp, Color(0xFF8B5CF6), RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_brand_logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(13.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Mundo da Música",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Versão 5.0 (Build 5)",
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.outline)
                )
            }
        }
    }

    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = { Text("Confirmar Saída") },
            text = { Text("Deseja realmente sair da conta atual? No modo convidado, seu progresso continuará salvo no aparelho.") },
            confirmButton = {
                Button(
                    onClick = {
                        showLogoutDialog = false
                        onLogout()
                    }
                ) {
                    Text("Sair")
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteAccountDialog = false },
            icon = {
                Icon(
                    Icons.Default.DeleteForever,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(32.dp)
                )
            },
            title = { Text("Excluir Conta Permanentemente?", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("Esta ação é definitiva e irreversível.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Ao confirmar, todos os seus dados serão apagados imediatamente:")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("• Identificador e e-mail cadastrado", style = MaterialTheme.typography.bodySmall)
                    Text("• Histórico de aulas concluídas e progresso", style = MaterialTheme.typography.bodySmall)
                    Text("• Saldo de moedas e histórico de transações", style = MaterialTheme.typography.bodySmall)
                    Text("• Dias de ofensiva (streak) e conquistas", style = MaterialTheme.typography.bodySmall)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteAccountDialog = false
                        onDeleteAccount()
                        Toast.makeText(context, "Conta e dados excluídos com sucesso.", Toast.LENGTH_LONG).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                    modifier = Modifier.testTag("dialog_confirm_delete_btn")
                ) {
                    Text("Excluir Definitivamente", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteAccountDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
