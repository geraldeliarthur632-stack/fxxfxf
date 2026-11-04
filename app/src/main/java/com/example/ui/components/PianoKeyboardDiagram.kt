package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.PianoDiagramData

@Composable
fun PianoKeyboardDiagram(
    diagramData: PianoDiagramData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
            .padding(16.dp)
            .testTag("piano_diagram"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = diagramData.chordOrScaleName,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // White keys list for 10 keys: C4, D4, E4, F4, G4, A4, B4, C5, D5, E5
        val whiteKeys = listOf("C4", "D4", "E4", "F4", "G4", "A4", "B4", "C5", "D5", "E5")
        // Black keys map with their relative position index between white keys:
        // C#4 between 0 and 1, D#4 between 1 and 2, F#4 between 3 and 4, G#4 between 4 and 5, A#4 between 5 and 6, C#5 between 7 and 8, D#5 between 8 and 9
        val blackKeys = listOf(
            Pair("C#4", 0.65f),
            Pair("D#4", 1.75f),
            Pair("F#4", 3.65f),
            Pair("G#4", 4.70f),
            Pair("A#4", 5.75f),
            Pair("C#5", 7.65f),
            Pair("D#5", 8.75f)
        )

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            val totalWhite = whiteKeys.size
            val keyWidth = size.width / totalWhite
            val whiteKeyHeight = size.height
            val blackKeyHeight = whiteKeyHeight * 0.62f
            val blackKeyWidth = keyWidth * 0.62f

            // 1. Draw white keys
            whiteKeys.forEachIndexed { i, keyName ->
                val isActive = diagramData.activeKeys.any { it.equals(keyName, ignoreCase = true) }
                val fillColor = if (isActive) Color(0xFFFBBF24) else Color.White
                val left = i * keyWidth

                drawRoundRect(
                    color = fillColor,
                    topLeft = Offset(left, 0f),
                    size = Size(keyWidth - 2f, whiteKeyHeight),
                    cornerRadius = CornerRadius(4f, 4f)
                )

                drawRoundRect(
                    color = Color(0xFF9CA3AF),
                    topLeft = Offset(left, 0f),
                    size = Size(keyWidth - 2f, whiteKeyHeight),
                    cornerRadius = CornerRadius(4f, 4f),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
                )
            }

            // 2. Draw black keys
            blackKeys.forEach { (keyName, pos) ->
                val isActive = diagramData.activeKeys.any { it.equals(keyName, ignoreCase = true) }
                val fillColor = if (isActive) Color(0xFFF59E0B) else Color(0xFF1F2937)
                val left = (pos * keyWidth) - (blackKeyWidth / 2f)

                drawRoundRect(
                    color = fillColor,
                    topLeft = Offset(left, 0f),
                    size = Size(blackKeyWidth, blackKeyHeight),
                    cornerRadius = CornerRadius(3f, 3f)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Teclas Ativas: " + diagramData.activeKeys.joinToString(", "),
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )
    }
}
