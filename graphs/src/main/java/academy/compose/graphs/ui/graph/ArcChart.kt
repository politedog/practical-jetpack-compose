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
import academy.compose.graphs.R
import academy.compose.graphs.Tags.TAG_CHART_DOUGHNUT
import academy.compose.graphs.Tags.TAG_CHART_PIE
import academy.compose.graphs.model.GraphData
import android.graphics.Paint
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun DoughnutChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    ArcChart(
        modifier = modifier.testTag(TAG_CHART_DOUGHNUT),
        chartData = chartData,
        useCenter = false,
        style = Stroke(150f, cap = StrokeCap.Butt),
        outerMargin = 70f
    )
}

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    ArcChart(
        modifier = modifier.testTag(TAG_CHART_PIE),
        chartData = chartData,
        useCenter = true,
        style = Fill
    )
}

@Composable
fun ArcChart(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>,
    useCenter: Boolean,
    style: DrawStyle,
    outerMargin: Float = 0f
) {
    val transitionAnimation = configureAnimation(chartData)
    val radiusBorder = with(LocalDensity.current) { 24.dp.toPx() }
    val resources = LocalContext.current.resources
    val labelTextPaint = remember {
        labelTextPaint(resources).apply {
            textAlign = Paint.Align.CENTER
        }
    }.apply {
        alpha = ((255 / 100) *
            (transitionAnimation.value * 100)).toInt()
    }

    val sumOfDataSet = remember(chartData) { chartData.sumOf { it.value } }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Canvas(
            modifier = Modifier.size(240.dp)
        ) {
            var currentSegment = 0f
            var currentStartAngle = 0f
            val radius = size.width / 2
            val totalAngle = 360

            chartData.forEach { chartData ->
                val segmentAngle = totalAngle * chartData.value / sumOfDataSet
                val angleToDraw =
                    totalAngle * (chartData.value * transitionAnimation.value) / sumOfDataSet
                drawArc(
                    color = chartData.color,
                    startAngle = currentStartAngle,
                    sweepAngle = angleToDraw,
                    useCenter = useCenter,
                    style = style
                )
                currentSegment += segmentAngle
                currentStartAngle += angleToDraw

                drawIntoCanvas {
                    val medianAngle = (currentSegment - (segmentAngle / 2)) * Math.PI / 180f
                    val radiusWithBorder = radius + radiusBorder
                    val drawAtX =
                        ((radiusWithBorder + outerMargin) * cos(medianAngle)).toFloat() + radius
                    val drawAtY =
                        ((radiusWithBorder + outerMargin) * sin(medianAngle)).toFloat() + radius
                    it.nativeCanvas.drawText(
                        chartData.value.toString(),
                        drawAtX,
                        drawAtY,
                        labelTextPaint
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(36.dp))
        Legend(chartData = chartData)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DoughnutChart() {
    MaterialTheme {
        DoughnutChart(
            modifier = Modifier.fillMaxWidth().padding(60.dp),
            GraphsDataFactory.makeChartData()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PieChart() {
    MaterialTheme {
        PieChart(
            modifier = Modifier.fillMaxWidth().padding(60.dp),
            GraphsDataFactory.makeChartData()
        )
    }
}