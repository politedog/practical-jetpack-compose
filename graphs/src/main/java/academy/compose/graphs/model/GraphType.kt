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
package academy.compose.graphs.model

import academy.compose.graphs.R
import androidx.annotation.StringRes

enum class GraphType(@StringRes val label: Int) {
    PIE(R.string.graph_pie),
    LINE(R.string.graph_line),
    BAR(R.string.graph_bar),
    COLUMN(R.string.graph_column),
    DOUGHNUT(R.string.graph_doughnut),
    AREA(R.string.graph_area)
}