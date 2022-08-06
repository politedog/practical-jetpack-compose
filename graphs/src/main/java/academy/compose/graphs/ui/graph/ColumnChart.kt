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
import academy.compose.graphs.Tags.TAG_CHART_COLUMN
import academy.compose.graphs.model.GraphData
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColumnChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    val maximumValue = chartData.maxOf { it.value }
    val transitionAnimation = configureAnimation(chartData)
    val columnSpacing = with(LocalDensity.current) { 4.dp.toPx() }
    val context = LocalContext.current
    val longestLabel = chartData.maxByOrNull { it.label.length }!!.label
    val labelTextPaint = labelTextPaint(context.resources)
    val labelWidth = labelTextPaint.measureText(longestLabel)

    Canvas(
        modifier = modifier
            .height(320.dp)
            .fillMaxSize()
            .padding(36.dp)
            .testTag(TAG_CHART_COLUMN)
    ) {
        val numberOfValues = chartData.count()
        val occupiedSeparatorSpacing = columnSpacing * (numberOfValues + 1)
        val widthSegment =
            (size.width - (padding * 2) - occupiedSeparatorSpacing - labelWidth) / numberOfValues

        val heightSegment = (size.height - (padding * 2)) / maximumValue

        drawAxis(labelWidth)

        chartData.forEachIndexed { index, chartData ->
            val drawAtX =
                padding + labelWidth + columnSpacing + ((columnSpacing + widthSegment) * index)
            val height =
                (heightSegment * chartData.value)  * transitionAnimation.value

            drawRect(
                chartData.color,
                topLeft = Offset(
                    x = drawAtX,
                    y = size.height - padding - strokeThickness - height
                ),
                size = Size(width = widthSegment, height = height)
            )

            drawDataLabelsOnXAxis(
                chartData.label,
                widthSegment + columnSpacing,
                index,
                context.resources,
                labelWidth + padding
            )
        }

        drawValueLabelsOnYAxis(maximumValue, chartData.count(), context.resources)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ColumnChart() {
    MaterialTheme {
        ColumnChart(
            modifier = Modifier.fillMaxSize(),
            GraphsDataFactory.makeChartData()
        )
    }
}