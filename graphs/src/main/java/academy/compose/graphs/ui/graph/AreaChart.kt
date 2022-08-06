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
import academy.compose.graphs.Tags.TAG_CHART_AREA
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AreaChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    val transitionAnimation = configureAnimation(chartData)
    val context = LocalContext.current
    val longestLabel = chartData.maxByOrNull { it.label.length }!!.label
    val labelTextPaint = labelTextPaint(context.resources)
    val labelWidth = labelTextPaint.measureText(longestLabel)

    Canvas(
        modifier = modifier
            .height(320.dp)
            .fillMaxWidth()
            .padding(36.dp)
            .testTag(TAG_CHART_AREA)
    ) {
        val maximumValue = chartData.maxOf { it.value }
        val widthSegment = (size.width - (padding * 2)) / chartData.count()
        val heightSegment = (size.height - (padding * 2)) / maximumValue
        val availableHeightFromBottom = size.height - padding - strokeThickness

        drawAxis(labelWidth)

        val pathToDraw = Path()

        chartData.forEachIndexed { index, current ->
            drawDataLabelsOnXAxis(
                current.label,
                widthSegment,
                index,
                context.resources,
                labelWidth + padding
            )

            if (index == 0) {
                pathToDraw.moveTo(
                    labelWidth + strokeThickness + padding,
                    size.height - padding - strokeThickness
                )
            } else {
                val height =
                    (heightSegment * current.value)  * transitionAnimation.value
                val offsetToDraw = Offset(
                    labelWidth + padding + strokeThickness + (widthSegment * index) + (widthSegment / 2),
                    heightSegment * current.value
                )
                val y =
                    availableHeightFromBottom - height
                pathToDraw.lineTo(offsetToDraw.x, y)
                if (index == chartData.count() - 1) {
                    pathToDraw.lineTo(
                        offsetToDraw.x,
                        size.height - padding - strokeThickness
                    )
                }
            }
        }

        drawPath(
            pathToDraw,
            Color.Black
        )

        drawValueLabelsOnYAxis(maximumValue, chartData.count(), context.resources)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AreaChart() {
    MaterialTheme {
        AreaChart(
            modifier = Modifier.fillMaxSize(),
            GraphsDataFactory.makeChartData()
        )
    }
}