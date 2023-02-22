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
package academy.compose.graphs.ui.graph

import academy.compose.graphs.GraphsDataFactory
import academy.compose.graphs.Tags.TAG_CHART_BAR
import academy.compose.graphs.model.GraphData
import academy.compose.graphs.ui.*
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    val context = LocalContext.current
    val highestValue = chartData.maxOf { it.value }
    val transitionAnimation = configureAnimation(chartData)
    val longestLabel = chartData.maxByOrNull { it.label.length }?.label

    val labelTextPaint = labelTextPaint(context.resources)
    val labelWidth = labelTextPaint.measureText(longestLabel)

    Canvas(
        modifier = modifier
            .height(280.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 36.dp, bottom = 36.dp)
            .testTag(TAG_CHART_BAR)
    ) {
        val columnSpacing = 6f
        val numberOfValues = chartData.count()
        val heightSegment = (size.height - strokeThickness - (padding * 2) -
            (columnSpacing * (numberOfValues + 1))) / numberOfValues
        val widthSegment = (size.width - (padding * 2) - labelWidth) / highestValue

        chartData.forEachIndexed { index, chartData ->
            val drawAtY =
                padding + columnSpacing + ((columnSpacing + heightSegment) * index)
            val width =
                ((widthSegment * chartData.value) - strokeThickness) * transitionAnimation.value
            drawIntoCanvas {
                val bounds = Rect().also { rect ->
                    labelTextPaint.getTextBounds(chartData.label, 0, chartData.label.length, rect)
                }
                val centerY = drawAtY + (heightSegment / 2) + (bounds.height() / 2)
                it.nativeCanvas.drawText(chartData.label, 0f, centerY, labelTextPaint)
            }
            drawRect(
                chartData.color,
                topLeft = Offset(
                    x = labelWidth + padding + textPadding + strokeThickness,
                    y = drawAtY
                ),
                size = Size(width = width, height = heightSegment)
            )
        }

        drawAxis(labelWidth)

        repeat(numberOfValues + 1) { value ->
            drawIntoCanvas {
                val points = (highestValue / numberOfValues) * value
                val width = size.width - (padding * 2) - labelWidth

                val xPosition =
                    ((width / numberOfValues) * value) + labelWidth + padding

                it.nativeCanvas.drawText(
                    points.toString(),
                    xPosition,
                    size.height,
                    labelTextPaint
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_BarChart() {
    MaterialTheme {
        BarChart(
            modifier = Modifier.fillMaxSize(),
            GraphsDataFactory.makeChartData()
        )
    }
}