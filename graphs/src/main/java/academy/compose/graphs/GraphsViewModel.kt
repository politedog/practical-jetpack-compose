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
package academy.compose.graphs

import academy.compose.graphs.model.GraphsEvent
import academy.compose.graphs.model.GraphsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class GraphsViewModel: ViewModel() {

    val uiState = MutableStateFlow(GraphsState())

    fun handleStocksEvent(event: GraphsEvent) {
        when (event) {
            GraphsEvent.DismissPicker -> {
                uiState.value = uiState.value.copy(
                    showChartPicker = false
                )
            }
            is GraphsEvent.SetChartType -> {
                uiState.value = uiState.value.copy(
                    selectedChart = event.chart,
                    showChartPicker = false
                )
            }
            GraphsEvent.ShowPicker -> {
                uiState.value = uiState.value.copy(
                    showChartPicker = true
                )
            }
        }
    }
}