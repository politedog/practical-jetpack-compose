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

import academy.compose.graphs.model.GraphType
import academy.compose.graphs.model.GraphsState
import academy.compose.graphs.ui.GraphsDashboard
import academy.compose.graphs.Tags.TAG_CHART_AREA
import academy.compose.graphs.Tags.TAG_CHART_BAR
import academy.compose.graphs.Tags.TAG_CHART_COLUMN
import academy.compose.graphs.Tags.TAG_CHART_DOUGHNUT
import academy.compose.graphs.Tags.TAG_CHART_LINE
import academy.compose.graphs.Tags.TAG_CHART_PIE
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GraphsDashboardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Chart_Picker_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(Tags.TAG_CHART_TYPE_TOGGLE)
            .assertIsDisplayed()
    }

    @Test
    fun Bar_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.BAR
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Column_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.COLUMN
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_COLUMN)
            .assertIsDisplayed()
    }

    @Test
    fun Pie_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.PIE
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_PIE)
            .assertIsDisplayed()
    }

    @Test
    fun Doughnut_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.DOUGHNUT
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_DOUGHNUT)
            .assertIsDisplayed()
    }

    @Test
    fun Area_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.AREA
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_AREA)
            .assertIsDisplayed()
    }

    @Test
    fun Line_Chart_Displayed() {
        composeTestRule.setContent {
            GraphsDashboard(
                state = GraphsState(
                    selectedChart = GraphType.LINE
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHART_LINE)
            .assertIsDisplayed()
    }
}