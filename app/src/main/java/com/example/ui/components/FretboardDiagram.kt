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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ChordDiagramData

@Composable
fun FretboardDiagram(
    chordData: ChordDiagramData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
            .padding(16.dp)
            .testTag("chord_diagram_${chordData.chordName}"),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = chordData.chordName,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        val numStrings = chordData.frets.size
        val numFrets = 4

        Canvas(
            modifier = Modifier
                .width(180.dp)
                .height(190.dp)
        ) {
            val canvasW = size.width
            val canvasH = size.height

            val topMargin = 28f
            val bottomMargin = 15f
            val leftMargin = 24f
            val rightMargin = 24f

            val fretboardW = canvasW - leftMargin - rightMargin
            val fretboardH = canvasH - topMargin - bottomMargin

            val stringSpacing = fretboardW / (numStrings - 1)
            val fretSpacing = fretboardH / numFrets

            // Draw nut (thick top line)
            drawLine(
                color = Color(0xFF6B7280),
                start = Offset(leftMargin, topMargin),
                end = Offset(canvasW - rightMargin, topMargin),
                strokeWidth = 6f
            )

            // Draw horizontal frets
            for (f in 1..numFrets) {
                val y = topMargin + f * fretSpacing
                drawLine(
                    color = Color(0xFF9CA3AF),
                    start = Offset(leftMargin, y),
                    end = Offset(canvasW - rightMargin, y),
                    strokeWidth = 2.5f
                )
            }

            // Draw vertical strings
            for (s in 0 until numStrings) {
                val x = leftMargin + s * stringSpacing
                // thicker bass strings on left, thinner treble on right
                val stringStroke = 3.5f - (s * (2.0f / (numStrings - 1)))
                drawLine(
                    color = Color(0xFF4B5563),
                    start = Offset(x, topMargin),
                    end = Offset(x, canvasH - bottomMargin),
                    strokeWidth = stringStroke.coerceAtLeast(1.2f)
                )

                // Draw X or O on top
                val fretVal = chordData.frets.getOrElse(s) { 0 }
                val symbolY = topMargin - 14f
                if (fretVal == -1) {
                    // Draw X
                    drawLine(
                        color = Color(0xFFEF4444),
                        start = Offset(x - 6f, symbolY - 6f),
                        end = Offset(x + 6f, symbolY + 6f),
                        strokeWidth = 2.5f
                    )
                    drawLine(
                        color = Color(0xFFEF4444),
                        start = Offset(x - 6f, symbolY + 6f),
                        end = Offset(x + 6f, symbolY - 6f),
                        strokeWidth = 2.5f
                    )
                } else if (fretVal == 0) {
                    // Draw O (open circle)
                    drawCircle(
                        color = Color(0xFF10B981),
                        radius = 6f,
                        center = Offset(x, symbolY),
                        style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.5f)
                    )
                } else {
                    // Finger dot inside fret
                    val dotY = topMargin + (fretVal - 0.5f) * fretSpacing
                    drawCircle(
                        color = Color(0xFF4F46E5),
                        radius = 11f,
                        center = Offset(x, dotY)
                    )
                }
            }
        }
    }
}
