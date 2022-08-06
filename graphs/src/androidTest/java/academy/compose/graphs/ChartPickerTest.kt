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
import academy.compose.graphs.ui.ChartPicker
import academy.compose.graphs.Tags.TAG_CHART_TYPES
import academy.compose.graphs.Tags.TAG_SELECTED_CHART
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(AndroidJUnit4::class)
class ChartPickerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Selected_Chart_Displayed() {
        composeTestRule.setContent {
            ChartPicker(
                chart = GraphType.AREA,
                expanded = false,
                dismiss = { },
                onItemSelected = { },
                onPickerRequested = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_SELECTED_CHART)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.getString(GraphType.AREA.label)
            )
    }

    @Test
    fun Chart_Types_Displayed() {
        composeTestRule.setContent {
            ChartPicker(
                chart = GraphType.AREA,
                expanded = true,
                dismiss = { },
                onItemSelected = { },
                onPickerRequested = { }
            )
        }

        GraphType.values().forEachIndexed { index, chartType ->
            composeTestRule.onNodeWithTag(TAG_CHART_TYPES)
                .onChildAt(index)
                .assert(
                    hasText(
                        InstrumentationRegistry.getInstrumentation()
                            .targetContext.getString(chartType.label)
                    )
                )
        }
    }

    @Test
    fun Picker_Requested_Triggered() {
        val onPickerRequested: () -> Unit = mock()
        composeTestRule.setContent {
            ChartPicker(
                chart = GraphType.AREA,
                expanded = false,
                dismiss = { },
                onItemSelected = { },
                onPickerRequested = onPickerRequested
            )
        }

        composeTestRule.onNodeWithTag(TAG_SELECTED_CHART)
            .performClick()

        verify(onPickerRequested).invoke()
    }

    @Test
    fun Item_Selected_Triggered() {
        val chartToSelect = GraphType.DOUGHNUT
        val onItemSelected: (type: GraphType) -> Unit = mock()
        composeTestRule.setContent {
            ChartPicker(
                chart = GraphType.AREA,
                expanded = true,
                dismiss = { },
                onItemSelected = onItemSelected,
                onPickerRequested = { }
            )
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(chartToSelect.label)
        ).performClick()

        verify(onItemSelected).invoke(chartToSelect)
    }
}