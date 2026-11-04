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
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.AppLanguage
import com.example.model.AppStrings
import com.example.ui.components.MascotBubble

@Composable
fun WelcomeScreen(
    language: AppLanguage,
    onLoginGoogle: (email: String, name: String) -> Unit,
    onLoginEmail: () -> Unit,
    onCreateAccount: () -> Unit,
    onContinueGuest: () -> Unit
) {
    var showGoogleDialog by remember { mutableStateOf(false) }
    var customGoogleEmail by remember { mutableStateOf("aluno.musical@gmail.com") }
    var customGoogleName by remember { mutableStateOf("Músico Google") }

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
                        .size(92.dp)
                        .offset(y = 65.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1E1B4B))
                        .border(3.dp, Color(0xFFF59E0B), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_brand_logo),
                        contentDescription = "Logo Mundo da Música",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(55.dp))

            Text(
                text = AppStrings.get("app_title", language),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
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
                message = "Olá! Eu sou o Maestro Pipo. Escolha como quer entrar para começarmos sua aventura musical!",
                mascotName = "Maestro Pipo"
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 1. Google Sign-In
            Button(
                onClick = { showGoogleDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("welcome_login_google"),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Default.LockOpen, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = AppStrings.get("login_google", language),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
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
                            text = "Estude imediatamente sem cadastro! Seu progresso é salvo no celular e pode ser vinculado depois.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    }

    // Google Sign-In Selection Dialog
    if (showGoogleDialog) {
        AlertDialog(
            onDismissRequest = { showGoogleDialog = false },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Entrar com o Google", fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column {
                    Text(
                        "Selecione ou confirme sua conta Google para sincronizar seu progresso, moedas e certificados musicais:",
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
                        label = { Text("Nome do Aluno") },
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
                        val email = customGoogleEmail.trim().ifBlank { "aluno.musical@gmail.com" }
                        val name = customGoogleName.trim().ifBlank { "Músico Google" }
                        onLoginGoogle(email, name)
                    },
                    modifier = Modifier.testTag("google_confirm_button")
                ) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Continuar com Google")
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
