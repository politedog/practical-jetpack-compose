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
import academy.compose.graphs.model.GraphsEvent
import academy.compose.graphs.model.GraphsState
import academy.compose.graphs.Tags.TAG_CHART_TYPE_TOGGLE
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GraphsDashboard(state: GraphsState, handleEvent: (event: GraphsEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ChartPicker(
            modifier = Modifier.testTag(TAG_CHART_TYPE_TOGGLE),
            chart = state.selectedChart,
            expanded = state.showChartPicker,
            dismiss = {
                handleEvent(GraphsEvent.DismissPicker)
            },
            onItemSelected = {
                handleEvent(GraphsEvent.SetChartType(it))
            },
            onPickerRequested = {
                handleEvent(GraphsEvent.ShowPicker)
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Chart(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            chart = state.selectedChart,
            chartData = state.chartData
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Graphs() {
    MaterialTheme {
        GraphsDashboard(
            state = GraphsState(
                chartData = GraphsDataFactory.makeChartData()
            ),
            handleEvent = { }
        )
    }
}
