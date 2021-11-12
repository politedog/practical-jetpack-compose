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

import academy.compose.home.model.Destination
import academy.compose.home.ui.RootDestinationTopBar
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RootDestinationTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Home,
                openDrawer = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithText(Destination.Home.title)
            .assertIsDisplayed()
    }

    @Test
    fun Menu_Icon_Displayed() {
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Home,
                openDrawer = { },
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_open_menu)
        ).assertIsDisplayed()
    }

    @Test
    fun Menu_Icon_Triggers_Callback() {
        val openDrawer: () -> Unit = mock()
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Home,
                openDrawer = openDrawer,
                showSnackbar = { }
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_open_menu)
        ).performClick()

        verify(openDrawer).invoke()
    }

    @Test
    fun Info_Icon_Displayed() {
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Contacts,
                openDrawer = { },
                showSnackbar = { }
            )
        }

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_more_info)
        ).assertIsDisplayed()
    }

    @Test
    fun Info_Icon_Not_Displayed_For_Feed() {
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Feed,
                openDrawer = { },
                showSnackbar = { }
            )
        }

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_more_info)
        ).assertDoesNotExist()
    }

    @Test
    fun Info_Icon_Triggers_Callback() {
        val showSnackbar: (message: String) -> Unit = mock()
        composeTestRule.setContent {
            RootDestinationTopBar(
                currentDestination = Destination.Contacts,
                openDrawer = { },
                showSnackbar = showSnackbar
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_more_info)
        ).performClick()

        verify(showSnackbar).invoke(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.not_available_yet))
    }
}