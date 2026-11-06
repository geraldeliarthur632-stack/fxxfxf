package com.example.ui.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.audio.AudioSynthesizer
import com.example.audio.PitchDetector
import com.example.model.AppLanguage
import com.example.model.InstrumentTunerData
import com.example.model.InstrumentType
import com.example.model.TunerString
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TunerScreen(
    currentInstrument: InstrumentType,
    language: AppLanguage = AppLanguage.PT,
    onBack: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val strings = remember(currentInstrument) {
        InstrumentTunerData.getStringsForInstrument(currentInstrument)
    }

    var selectedStringIndex by remember { mutableIntStateOf(0) }
    var isAutoMode by remember { mutableStateOf(true) }
    val reading by PitchDetector.reading.collectAsState()

    var hasAudioPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasAudioPermission = granted
        if (granted) {
            PitchDetector.startListening(scope)
        }
    }

    LaunchedEffect(hasAudioPermission) {
        if (hasAudioPermission) {
            PitchDetector.startListening(scope)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            PitchDetector.stopListening()
        }
    }

    // Auto-detect closest string if auto mode is enabled and frequency is received
    LaunchedEffect(reading.frequencyHz, isAutoMode) {
        if (isAutoMode && reading.frequencyHz > 30f) {
            var closestIdx = 0
            var minDiff = Float.MAX_VALUE
            strings.forEachIndexed { index, s ->
                val diff = abs(reading.frequencyHz - s.frequencyHz)
                if (diff < minDiff) {
                    minDiff = diff
                    closestIdx = index
                }
            }
            selectedStringIndex = closestIdx
        }
    }

    val currentString = strings.getOrElse(selectedStringIndex) { strings.first() }

    // Haptic feedback when perfectly in tune
    LaunchedEffect(reading.isInTune) {
        if (reading.isInTune && reading.isSignalDetected) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator?.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator?.vibrate(50)
            }
        }
    }

    // Dynamic strings header label according to language and instrument type
    val stringsHeaderLabel = remember(currentInstrument, language) {
        when (currentInstrument) {
            InstrumentType.PIANO, InstrumentType.TECLADO -> when (language) {
                AppLanguage.PT -> "Teclas de Referência do Instrumento"
                AppLanguage.EN -> "Instrument Reference Keys"
                AppLanguage.ES -> "Teclas de Referencia del Instrumento"
            }
            InstrumentType.BATERIA -> when (language) {
                AppLanguage.PT -> "Peças e Peles da Bateria"
                AppLanguage.EN -> "Drum Heads & Shells"
                AppLanguage.ES -> "Parches y Cuerpos de Batería"
            }
            InstrumentType.VIOLA_CAIPIRA -> when (language) {
                AppLanguage.PT -> "Pares de Cordas da Viola (Toque para ouvir)"
                AppLanguage.EN -> "10-String Viola Courses (Tap to listen)"
                AppLanguage.ES -> "Pares de Cuerdas de la Viola (Toca para escuchar)"
            }
            else -> when (language) {
                AppLanguage.PT -> "Cordas do Instrumento (Toque para ouvir)"
                AppLanguage.EN -> "Instrument Strings (Tap to listen)"
                AppLanguage.ES -> "Cuerdas del Instrumento (Toca para escuchar)"
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.GraphicEq,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                AppLanguage.PT -> "Afinador Studio"
                                AppLanguage.EN -> "Studio Tuner"
                                AppLanguage.ES -> "Afinador Studio"
                            },
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                },
                navigationIcon = {
                    if (onBack != null) {
                        IconButton(onClick = onBack, modifier = Modifier.testTag("tuner_back_button")) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                        }
                    }
                },
                actions = {
                    AssistChip(
                        onClick = { isAutoMode = !isAutoMode },
                        label = {
                            Text(
                                text = if (isAutoMode) {
                                    when (language) {
                                        AppLanguage.PT -> "Modo Auto"
                                        AppLanguage.EN -> "Auto Mode"
                                        AppLanguage.ES -> "Modo Auto"
                                    }
                                } else {
                                    when (language) {
                                        AppLanguage.PT -> "Modo Manual"
                                        AppLanguage.EN -> "Manual Mode"
                                        AppLanguage.ES -> "Modo Manual"
                                    }
                                },
                                fontWeight = FontWeight.Bold
                            )
                        },
                        leadingIcon = {
                            Icon(
                                if (isAutoMode) Icons.Default.AutoMode else Icons.Default.Tune,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    )
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: Selected instrument card with high-resolution photo & tuning info
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.8f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = currentInstrument.drawableRes),
                        contentDescription = currentInstrument.getName(language),
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .border(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = when (language) {
                                AppLanguage.PT -> "Instrumento Ativo"
                                AppLanguage.EN -> "Active Instrument"
                                AppLanguage.ES -> "Instrumento Activo"
                            },
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                        Text(
                            text = currentInstrument.getName(language),
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = currentInstrument.tuningInfo,
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // TOP SECTION: As Cordas e Nomes das Cordas do Instrumento Selecionado
            Text(
                text = stringsHeaderLabel,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Horizontal strings bar with real names according to selected language
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                strings.forEachIndexed { index, tunerString ->
                    val isSelected = selectedStringIndex == index

                    Card(
                        onClick = {
                            selectedStringIndex = index
                            isAutoMode = false
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("tuner_string_${tunerString.noteLetter}_$index"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        ),
                        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = tunerString.getStringNumber(language),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Medium
                                ),
                                maxLines = 1
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = tunerString.noteLetter,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${tunerString.frequencyHz.toInt()} Hz",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        AudioSynthesizer.playTone(tunerString.frequencyHz.toDouble(), 1000)
                                    }
                                },
                                modifier = Modifier.size(28.dp)
                            ) {
                                Icon(
                                    Icons.Default.VolumeUp,
                                    contentDescription = "Ouvir nota",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // BOTTOM SECTION: O Afinador e as Letras do Afinador
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!hasAudioPermission) {
                        Column(
                            modifier = Modifier.padding(vertical = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Mic,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = when (language) {
                                    AppLanguage.PT -> "Permissão de Microfone Necessária"
                                    AppLanguage.EN -> "Microphone Permission Required"
                                    AppLanguage.ES -> "Permiso de Micrófono Requerido"
                                },
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = when (language) {
                                    AppLanguage.PT -> "Para afinar em tempo real ouvindo as cordas do seu ${currentInstrument.getName(language)}, conceda acesso ao microfone:"
                                    AppLanguage.EN -> "To tune in real time listening to your ${currentInstrument.getName(language)}, grant microphone access:"
                                    AppLanguage.ES -> "Para afinar en tiempo real escuchando tu ${currentInstrument.getName(language)}, concede acceso al micrófono:"
                                },
                                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { permissionLauncher.launch(Manifest.permission.RECORD_AUDIO) },
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.testTag("btn_request_microphone_permission")
                            ) {
                                Icon(Icons.Default.Mic, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    when (language) {
                                        AppLanguage.PT -> "Ativar Microfone para Afinar"
                                        AppLanguage.EN -> "Enable Microphone to Tune"
                                        AppLanguage.ES -> "Activar Micrófono para Afinar"
                                    }
                                )
                            }
                        }
                    } else {
                        // Real-Time Tuner Display
                        val displayedNote = if (reading.isSignalDetected) reading.noteName else currentString.noteLetter
                        val detectedFreq = if (reading.isSignalDetected) reading.frequencyHz else 0f
                        val targetFreq = currentString.frequencyHz
                        val centsDiff = if (reading.isSignalDetected) {
                            PitchDetector.calculateCentsToTarget(reading.frequencyHz, targetFreq)
                        } else 0f

                        val inTune = reading.isSignalDetected && abs(centsDiff) <= 5f

                        val statusColor by animateColorAsState(
                            targetValue = when {
                                !reading.isSignalDetected -> MaterialTheme.colorScheme.onSurfaceVariant
                                inTune -> Color(0xFF10B981) // Green
                                centsDiff < -5f -> Color(0xFFF59E0B) // Amber flat
                                else -> Color(0xFFEF4444) // Red sharp
                            },
                            animationSpec = tween(durationMillis = 300),
                            label = "statusColor"
                        )

                        val statusText = when {
                            !reading.isSignalDetected -> when (language) {
                                AppLanguage.PT -> "Toque uma corda para afinar..."
                                AppLanguage.EN -> "Play a string to tune..."
                                AppLanguage.ES -> "Toca una cuerda para afinar..."
                            }
                            inTune -> when (language) {
                                AppLanguage.PT -> "AFINADO! PERFEITO!"
                                AppLanguage.EN -> "IN TUNE! PERFECT!"
                                AppLanguage.ES -> "¡AFINADO! ¡PERFECTO!"
                            }
                            centsDiff < -5f -> when (language) {
                                AppLanguage.PT -> "MUITO BAIXO: Aperte a tarraxa (↑)"
                                AppLanguage.EN -> "TOO FLAT: Tighten peg (↑)"
                                AppLanguage.ES -> "MUY GRAVE: Aprieta la clavija (↑)"
                            }
                            else -> when (language) {
                                AppLanguage.PT -> "MUITO ALTO: Afrouxe a tarraxa (↓)"
                                AppLanguage.EN -> "TOO SHARP: Loosen peg (↓)"
                                AppLanguage.ES -> "MUY AGUDO: Afloja la clavija (↓)"
                            }
                        }

                        // Central Meter Needle Display
                        Box(
                            modifier = Modifier
                                .size(210.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.radialGradient(
                                        listOf(
                                            statusColor.copy(alpha = 0.18f),
                                            MaterialTheme.colorScheme.surface
                                        )
                                    )
                                )
                                .border(3.dp, statusColor.copy(alpha = 0.5f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            // Canvas for needle arc with animated needle swing
                            val needleAngle by animateFloatAsState(
                                targetValue = (centsDiff / 50f).coerceIn(-1f, 1f) * 45f,
                                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
                                label = "needleAngle"
                            )

                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val center = Offset(size.width / 2, size.height / 2)
                                val radius = size.width / 2 - 20

                                // Center tick mark (0 cents)
                                drawLine(
                                    color = Color(0xFF10B981),
                                    start = Offset(center.x, center.y - radius),
                                    end = Offset(center.x, center.y - radius + 16),
                                    strokeWidth = 3.5.dp.toPx(),
                                    cap = StrokeCap.Round
                                )

                                // Left tick mark (-25 cents)
                                drawLine(
                                    color = Color(0xFFF59E0B),
                                    start = Offset(center.x - radius * 0.38f, center.y - radius * 0.92f),
                                    end = Offset(center.x - radius * 0.34f, center.y - radius * 0.83f),
                                    strokeWidth = 2.dp.toPx()
                                )

                                // Right tick mark (+25 cents)
                                drawLine(
                                    color = Color(0xFFEF4444),
                                    start = Offset(center.x + radius * 0.38f, center.y - radius * 0.92f),
                                    end = Offset(center.x + radius * 0.34f, center.y - radius * 0.83f),
                                    strokeWidth = 2.dp.toPx()
                                )
                            }

                            // Large Note Letter and Frequency inside dial with animated pulse
                            val noteScale by animateFloatAsState(
                                targetValue = if (inTune) 1.08f else 1.0f,
                                animationSpec = spring(stiffness = Spring.StiffnessMedium),
                                label = "noteScale"
                            )

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.scale(noteScale)
                            ) {
                                Text(
                                    text = displayedNote,
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontWeight = FontWeight.Black,
                                        color = statusColor,
                                        fontSize = 68.sp,
                                        fontFamily = FontFamily.Monospace
                                    )
                                )
                                Text(
                                    text = if (reading.isSignalDetected) "${detectedFreq} Hz" else "Alvo: ${targetFreq} Hz",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                if (reading.isSignalDetected) {
                                    Text(
                                        text = "${if (centsDiff > 0) "+" else ""}${centsDiff.toInt()} cents",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = statusColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Status Badge with Animated Visibility
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = statusColor.copy(alpha = 0.15f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, statusColor.copy(alpha = 0.5f))
                        ) {
                            Text(
                                text = statusText,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = statusColor
                                ),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Target String Info card translated
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = when (language) {
                                        AppLanguage.PT -> "Corda Alvo"
                                        AppLanguage.EN -> "Target String"
                                        AppLanguage.ES -> "Cuerda Objetivo"
                                    },
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Text(
                                    text = currentString.getStringNumber(language),
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = when (language) {
                                        AppLanguage.PT -> "Nota Padrão"
                                        AppLanguage.EN -> "Standard Note"
                                        AppLanguage.ES -> "Nota Estándar"
                                    },
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Text(
                                    text = currentString.getNoteFullName(language),
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = when (language) {
                                        AppLanguage.PT -> "Freq. Padrão"
                                        AppLanguage.EN -> "Std Frequency"
                                        AppLanguage.ES -> "Frec. Estándar"
                                    },
                                    style = MaterialTheme.typography.labelSmall
                                )
                                Text(
                                    text = "${currentString.frequencyHz} Hz",
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Play Reference Pitch Button
                        FilledTonalButton(
                            onClick = {
                                scope.launch {
                                    AudioSynthesizer.playTone(currentString.frequencyHz.toDouble(), 1500)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("btn_play_reference_tone"),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.VolumeUp, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                when (language) {
                                    AppLanguage.PT -> "Tocar Som de Referência: ${currentString.getNoteFullName(language)}"
                                    AppLanguage.EN -> "Play Reference Pitch: ${currentString.getNoteFullName(language)}"
                                    AppLanguage.ES -> "Tocar Tono de Referencia: ${currentString.getNoteFullName(language)}"
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Studio Tuning Advice Tips
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = when (language) {
                                AppLanguage.PT -> "Dicas de Estúdio para Afinação Perfeita"
                                AppLanguage.EN -> "Studio Tips for Perfect Tuning"
                                AppLanguage.ES -> "Consejos de Estudio para una Afinación Perfecta"
                            },
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when (language) {
                            AppLanguage.PT -> "1. Toque a corda suavemente com a ponta do dedo ou palheta.\n" +
                                    "2. Afine sempre subindo: se a corda estiver alta demais, afrouxe abaixo da nota e suba devagar até o centro verde.\n" +
                                    "3. Mantenha as outras cordas abafadas com a palma para evitar harmônicos cruzados no microfone."
                            AppLanguage.EN -> "1. Pluck the string gently with your fingertip or pick.\n" +
                                    "2. Always tune upwards: if it is sharp, loosen slightly below pitch and tune up slowly into the green.\n" +
                                    "3. Mute adjacent strings with your palm to avoid acoustic microphone bleed."
                            AppLanguage.ES -> "1. Pulsa la cuerda suavemente con la yema del dedo o púa.\n" +
                                    "2. Afina siempre subiendo: si está muy aguda, afloja por debajo y sube despacio hacia el centro verde.\n" +
                                    "3. Apaga las otras cuerdas con la palma para evitar armónicos cruzados en el micrófono."
                        },
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }
        }
    }
}
