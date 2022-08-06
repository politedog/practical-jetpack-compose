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

import academy.compose.graphs.model.GraphData
import androidx.compose.ui.graphics.Color

object GraphsDataFactory {

    fun makeChartData(): List<GraphData> {
        return listOf(
            GraphData("2016", 20, Color.Red),
            GraphData("2017", 10, Color.Blue),
            GraphData("2018", 2, Color.Yellow),
            GraphData("2019", 42, Color.Green),
            GraphData("2020", 7, Color.Magenta),
            GraphData("2021", 5, Color.Cyan),
        )
    }
}