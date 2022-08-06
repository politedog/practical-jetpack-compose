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

import academy.compose.graphs.ui.Legend
import academy.compose.graphs.Tags.TAG_LEGEND
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LegendTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Chart_Data_Labels_Displayed() {
        val chartData = GraphsDataFactory.makeChartData()
        composeTestRule.setContent {
            Legend(chartData = chartData)
        }

        chartData.forEachIndexed { index, data ->
            composeTestRule.onNodeWithTag(
                TAG_LEGEND
            ).onChildAt(index)
                .assert(hasText(data.label))
        }
    }
}