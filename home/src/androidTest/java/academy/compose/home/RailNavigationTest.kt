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

import academy.compose.home.Tags.TAG_RAIL_CREATE
import academy.compose.home.Tags.TAG_RAIL_NAVIGATION
import academy.compose.home.model.Destination
import academy.compose.home.ui.RailNavigationBar
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RailNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Navigation_Rail_Displayed() {
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_NAVIGATION)
            .assertIsDisplayed()
    }

    @Test
    fun Header_Displayed() {
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithTag(TAG_RAIL_CREATE)
            .assertIsDisplayed()
    }

    @Test
    fun Navigation_Rail_Items_Displayed() {
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        listOf(
            Destination.Feed,
            Destination.Contacts,
            Destination.Calendar
        ).forEach {
            composeTestRule.onNodeWithText(
                it.title
            ).assertIsDisplayed()
        }
    }

    @Test
    fun Current_Destination_Selected() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = destination,
                onNavigate = { },
                onCreateItem = { }
            )
        }
        composeTestRule.onNodeWithText(destination.title)
            .assertIsSelected()
        composeTestRule.onNodeWithText(Destination.Feed.title)
            .assertIsNotSelected()
        composeTestRule.onNodeWithText(Destination.Calendar.title)
            .assertIsNotSelected()
    }

    @Test
    fun Create_Item_Callback_Triggered() {
        val onCreateItem: () -> Unit = mock()
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = { },
                onCreateItem = onCreateItem
            )
        }

        composeTestRule.onNodeWithTag(TAG_RAIL_CREATE)
            .performClick()

        verify(onCreateItem).invoke()
    }

    @Test
    fun Navigation_Callback_Triggered() {
        val destination = Destination.Contacts
        val onNavigate: (destination: Destination) -> Unit = mock()
        composeTestRule.setContent {
            RailNavigationBar(
                currentDestination = Destination.Feed,
                onNavigate = onNavigate,
                onCreateItem = {}
            )
        }

        composeTestRule.onNodeWithText(destination.title)
            .performClick()

        verify(onNavigate).invoke(destination)
    }
}