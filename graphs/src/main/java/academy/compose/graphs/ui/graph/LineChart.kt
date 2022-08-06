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

import academy.compose.graphs.GraphsDataFactory
import academy.compose.graphs.Tags.TAG_CHART_LINE
import academy.compose.graphs.model.GraphData
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LineChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    val transitionAnimation = configureAnimation(chartData)
    val pointRadius = with(LocalDensity.current) { 10.dp.toPx() }
    val context = LocalContext.current

    val longestLabel = chartData.maxByOrNull { it.label.length }!!.label

    val labelTextPaint = labelTextPaint(context.resources)
    val labelWidth = labelTextPaint.measureText(longestLabel)

    Canvas(
        modifier = modifier
            .height(320.dp)
            .fillMaxWidth()
            .padding(36.dp)
            .testTag(TAG_CHART_LINE)
    ) {
        val highestData = chartData.maxOf { it.value }

        val widthSegment = (size.width - (padding * 2)) / chartData.count()
        val heightSegment = (size.height - (padding * 2)) / highestData

        drawAxis(labelWidth)

        val pointsToDraw = chartData.mapIndexed { index, chartData ->
            Offset(
                 padding + labelWidth + strokeThickness + (widthSegment * index) + (widthSegment / 2),
                heightSegment * chartData.value
            )
        }

        chartData.forEachIndexed { index, chartData ->
            drawCircle(
                chartData.color,
                radius = pointRadius * transitionAnimation.value,
                center = pointsToDraw[index]
            )

            drawDataLabelsOnXAxis(
                chartData.label,
                widthSegment,
                index,
                context.resources,
                labelWidth + padding
            )
        }

        pointsToDraw.forEachIndexed { index, startOffset ->
            if (index < pointsToDraw.count() - 1) {
                val nextPoint = pointsToDraw[index + 1]
                val endOffset = nextPoint.copy(
                    x = startOffset.x + ((nextPoint.x - startOffset.x) * transitionAnimation.value),
                    y = startOffset.y + ((nextPoint.y - startOffset.y) * transitionAnimation.value)
                )
                drawLine(
                    color = Color.Black,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = 8f
                )
            }
        }

        drawValueLabelsOnYAxis(highestData, chartData.count(), context.resources)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LineChart() {
    MaterialTheme {
        LineChart(
            modifier = Modifier.fillMaxSize(),
            GraphsDataFactory.makeChartData()
        )
    }
}