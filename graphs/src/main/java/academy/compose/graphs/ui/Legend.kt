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
import academy.compose.graphs.Tags.TAG_LEGEND
import academy.compose.graphs.model.GraphData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Legend(
    modifier: Modifier = Modifier,
    chartData: List<GraphData>
) {
    Column(modifier = modifier.testTag(TAG_LEGEND)) {
        chartData.chunked(2).forEach { row ->
            Row {
                LegendItem(row[0])
                LegendItem(row[1])
            }
        }
    }
}

@Composable
fun LegendItem(data: GraphData) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .semantics(mergeDescendants = true) { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(data.color)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = data.label)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Legend() {
    MaterialTheme {
        Legend(
            chartData = GraphsDataFactory.makeChartData()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_LegendItem() {
    MaterialTheme {
        LegendItem(
            data = GraphsDataFactory.makeChartData()[0]
        )
    }
}