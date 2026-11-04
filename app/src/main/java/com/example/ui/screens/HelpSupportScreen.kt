package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.audio.AudioSynthesizer
import com.example.model.InstrumentType
import com.example.model.UserProfile
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpSupportScreen(
    userProfile: UserProfile,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajuda & Afinador Didático", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("help_back_btn")) {
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
            // Interactive Tuner Reference Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Tune, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Guia de Afinação Sonora", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimaryContainer))
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Toque nos botões para ouvir a afinação de referência gerada pelo sintetizador do app:",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    when (userProfile.selectedInstrument) {
                        InstrumentType.VIOLAO -> {
                            val strings = listOf(
                                Pair("6ª Mi (E2)", 82.41),
                                Pair("5ª Lá (A2)", 110.00),
                                Pair("4ª Ré (D3)", 146.83),
                                Pair("3ª Sol (G3)", 196.00),
                                Pair("2ª Si (B3)", 246.94),
                                Pair("1ª Mi (E4)", 329.63)
                            )
                            strings.forEach { (name, freq) ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(name, fontWeight = FontWeight.SemiBold)
                                    FilledTonalButton(
                                        onClick = { scope.launch { AudioSynthesizer.playTone(freq, 800) } }
                                    ) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Ouvir")
                                    }
                                }
                            }
                        }
                        InstrumentType.VIOLA_CAIPIRA -> {
                            val pairs = listOf(
                                Pair("1º Par Mi (E4)", 329.63),
                                Pair("2º Par Si (B3)", 246.94),
                                Pair("3º Par Sol# (G#3)", 207.65),
                                Pair("4º Par Si (B2)", 123.47),
                                Pair("5º Par Mi (E2)", 82.41)
                            )
                            pairs.forEach { (name, freq) ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(name, fontWeight = FontWeight.SemiBold)
                                    FilledTonalButton(
                                        onClick = { scope.launch { AudioSynthesizer.playTone(freq, 800) } }
                                    ) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Ouvir")
                                    }
                                }
                            }
                        }
                        InstrumentType.PIANO, InstrumentType.TECLADO -> {
                            val notes = listOf(
                                Pair("Dó 4 (C4)", 261.63),
                                Pair("Ré 4 (D4)", 293.66),
                                Pair("Mi 4 (E4)", 329.63),
                                Pair("Sol 4 (G4)", 392.00),
                                Pair("Lá 4 - 440 Hz (A4)", 440.00)
                            )
                            notes.forEach { (name, freq) ->
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(name, fontWeight = FontWeight.SemiBold)
                                    FilledTonalButton(
                                        onClick = { scope.launch { AudioSynthesizer.playTone(freq, 800) } }
                                    ) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = null, modifier = Modifier.size(16.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Ouvir")
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // FAQ Section
            Text("Perguntas Frequentes (FAQ)", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))

            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Como funcionam as moedas (Notas Musicais)?", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Cada aluno recebe 3 moedas iniciais. Cada nova aula consome 1 moeda. As aulas desbloqueadas ficam salvas para sempre. A cada 12 horas, suas moedas são recarregadas automaticamente até 3. Você também pode assistir anúncios ou comprar mais moedas.",
                        style = MaterialTheme.typography.bodySmall
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    Text("Posso aprender mais de um instrumento?", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Sim! Você pode alternar livremente entre Violão, Viola Caipira, Piano e Teclado a qualquer momento. O progresso de cada um é mantido individualmente.",
                        style = MaterialTheme.typography.bodySmall
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    Text("O modo convidado perde meus dados?", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Não! Todo o progresso do convidado é salvo no armazenamento local do aplicativo. Quando você decidir vincular uma conta Google ou e-mail, todo seu progresso e moedas são transferidos sem perda.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Contact Card
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Email, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Canal de Suporte", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Dúvidas pedagógicas ou suporte técnico:\nsuporte@mundodamusica.app",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
