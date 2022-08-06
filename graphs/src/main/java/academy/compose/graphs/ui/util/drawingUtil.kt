/*
 * Copyright 2022 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package academy.compose.graphs.ui

import academy.compose.graphs.DisplayMetricsUtil.dpToPx
import academy.compose.graphs.R
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas

val padding = dpToPx(16f)
val textPadding = dpToPx(4f)
val strokeThickness = dpToPx(2f)

fun labelTextPaint(resources: Resources) = Paint().apply {
    textSize = resources.getDimensionPixelSize(R.dimen.label_size).toFloat()
    typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
}

fun DrawScope.drawAxis(startPadding: Float = 0f) {
    drawLine(
        strokeWidth = strokeThickness,
        color = Color.Black,
        start = Offset(
            x = startPadding + padding,
            y = size.height - padding
        ),
        end = Offset(
            x = size.width,
            y = size.height - padding
        )
    )
    drawLine(
        strokeWidth = strokeThickness,
        color = Color.Black,
        start = Offset(
            x = startPadding + padding,
            y = size.height - padding
        ),
        end = Offset(
            x = startPadding + padding,
            y = padding
        )
    )
}

fun DrawScope.drawDataLabelsOnXAxis(
    label: String,
    widthSegment: Float,
    index: Int,
    resources: Resources,
    padding: Float
) {
    val drawAtX = padding + strokeThickness + (widthSegment * index) + (widthSegment / 2)
    drawIntoCanvas {
        val textPaint = labelTextPaint(resources)
        val textWidth = textPaint.measureText(label)
        val centerX = drawAtX - (textWidth / 2)
        it.nativeCanvas.drawText(label, centerX, size.height, textPaint)
    }
}

fun DrawScope.drawValueLabelsOnYAxis(
    maximumValueToDisplay: Int,
    labelCount: Int,
    resources: Resources
) {
    val textPaint = labelTextPaint(resources)
    for (value in labelCount downTo 0) {
        drawIntoCanvas {
            val valueToDraw =
                ((maximumValueToDisplay / labelCount) * (labelCount - value)).toString()
            val availableHeight = size.height - (padding * 2)
            val bounds = Rect().also { rect ->
                textPaint.getTextBounds(valueToDraw, 0, valueToDraw.length, rect)
            }
            val drawAtY = ((availableHeight / labelCount) * value) + padding + (bounds.height() / 2)

            it.nativeCanvas.drawText(
                valueToDraw,
                0f,
                drawAtY,
                textPaint
            )
        }
    }
}