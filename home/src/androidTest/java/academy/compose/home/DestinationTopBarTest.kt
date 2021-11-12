/*
 * Copyright 2021 Compose Academy
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

package academy.compose.home

import academy.compose.home.Tags.TAG_CHILD_TOP_BAR
import academy.compose.home.Tags.TAG_ROOT_TOP_BAR
import academy.compose.home.model.Destination
import academy.compose.home.ui.DestinationTopBar
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class DestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Root_Destination_Top_Bar_Displayed() {
        composeTestRule.setContent {
            DestinationTopBar(
                currentDestination = Destination.Home,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_ROOT_TOP_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Root_Destination_Top_Bar_Never_Displayed() {
        composeTestRule.setContent {
            DestinationTopBar(
                currentDestination = Destination.Add,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_ROOT_TOP_BAR)
            .assertDoesNotExist()
    }

    @Test
    fun Child_Top_Bar_Displayed() {
        composeTestRule.setContent {
            DestinationTopBar(
                currentDestination = Destination.Add,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHILD_TOP_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Child_Top_Bar_Never_Displayed() {
        composeTestRule.setContent {
            DestinationTopBar(
                currentDestination = Destination.Feed,
                openDrawer = { },
                onNavigateUp = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_CHILD_TOP_BAR)
            .assertDoesNotExist()
    }
}