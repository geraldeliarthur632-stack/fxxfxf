package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.GoogleAuthHelper
import com.example.model.AppLanguage
import com.example.model.AppStrings
import com.example.ui.components.MascotBubble
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    language: AppLanguage,
    onLoginGoogle: (email: String, name: String) -> Unit,
    onLoginEmail: () -> Unit,
    onCreateAccount: () -> Unit,
    onContinueGuest: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showGoogleDialog by remember { mutableStateOf(false) }
    var customGoogleEmail by remember { mutableStateOf("geraldeliarthur632@gmail.com") }
    var customGoogleName by remember { mutableStateOf("Arthur Geraldeli") }
    var isLoadingGoogle by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero Banner with official Brand Logo Badge
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcome_hero),
                    contentDescription = "Mundo da Música",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop
                )

                // Official Brand Logo Badge
                Box(
                    modifier = Modifier
                        .size(108.dp)
                        .offset(y = 65.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(Color(0xFF1A0438))
                        .border(3.dp, Color(0xFF8B5CF6), RoundedCornerShape(26.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_brand_logo),
                        contentDescription = "Logo Mundo da Música",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(24.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(55.dp))

            Text(
                text = AppStrings.get("app_title", language),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                    letterSpacing = 0.5.sp
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = AppStrings.get("welcome_subtitle", language),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(14.dp))

            MascotBubble(
                message = "Olá! Eu sou o Maestro Pipo. Escolha como quer entrar para começarmos sua aventura musical no estúdio!",
                mascotName = "Maestro Pipo"
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 1. Google Sign-In with Official Studio Brand Card Button
            Surface(
                onClick = {
                    isLoadingGoogle = true
                    scope.launch {
                        GoogleAuthHelper.signInWithGoogle(
                            context = context,
                            onSuccess = { email, name ->
                                isLoadingGoogle = false
                                onLoginGoogle(email, name)
                            },
                            onError = {
                                isLoadingGoogle = false
                                showGoogleDialog = true
                            },
                            onFallbackPrompt = {
                                isLoadingGoogle = false
                                showGoogleDialog = true
                            }
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("welcome_login_google"),
                shape = RoundedCornerShape(14.dp),
                color = MaterialTheme.colorScheme.surface,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.outlineVariant),
                shadowElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isLoadingGoogle) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
                    } else {
                        // Google "G" Badge Icon
                        Surface(
                            shape = CircleShape,
                            color = Color.White,
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5E7EB)),
                            modifier = Modifier.size(26.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "G",
                                    fontWeight = FontWeight.Black,
                                    fontSize = 16.sp,
                                    color = Color(0xFF4285F4)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Continuar com o Google",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 2. Email Login
            FilledTonalButton(
                onClick = onLoginEmail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("welcome_login_email"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Email, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = AppStrings.get("login_email", language),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 3. Create Account
            OutlinedButton(
                onClick = onCreateAccount,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("welcome_create_account"),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Default.Person, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = AppStrings.get("create_account", language),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Guest Mode
            Card(
                onClick = onContinueGuest,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("welcome_continue_guest"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = AppStrings.get("continue_guest", language),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                        Text(
                            text = "Estude imediatamente sem cadastro! Seu progresso é salvo no celular e pode ser vinculado ao Google depois.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    }

    // Google Sign-In Confirmation Dialog
    if (showGoogleDialog) {
        AlertDialog(
            onDismissRequest = { showGoogleDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE5E7EB)),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("G", fontWeight = FontWeight.Black, fontSize = 18.sp, color = Color(0xFF4285F4))
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Login com o Google", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text(
                        "Confirme sua conta Google para sincronizar suas 720 aulas, moedas e conquistas no Firebase & Firestore:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = customGoogleEmail,
                        onValueChange = { customGoogleEmail = it },
                        label = { Text("Seu E-mail Google") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("google_email_dialog_input"),
                        shape = RoundedCornerShape(10.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = customGoogleName,
                        onValueChange = { customGoogleName = it },
                        label = { Text("Nome do Músico") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("google_name_dialog_input"),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        showGoogleDialog = false
                        val email = customGoogleEmail.trim().ifBlank { "geraldeliarthur632@gmail.com" }
                        val name = customGoogleName.trim().ifBlank { "Arthur Geraldeli" }
                        onLoginGoogle(email, name)
                    },
                    modifier = Modifier.testTag("google_confirm_button")
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Entrar e Sincronizar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showGoogleDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
