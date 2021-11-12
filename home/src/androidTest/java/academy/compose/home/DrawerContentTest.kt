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
import academy.compose.home.ui.DrawerContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DrawerContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Account_Header_Displayed() {
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = {},
                    onLogout = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_name)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.label_email)
        ).assertIsDisplayed()
    }

    //<editor-fold desc="Upgrade">
    @Test
    fun Upgrade_Displayed() {
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = {},
                    onLogout = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            Destination.Upgrade.title
        ).assertIsDisplayed()
    }

    @Test
    fun Upgrade_Navigation_Callback_Triggered() {
        val onNavigate: (destination: Destination) -> Unit = mock()
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = onNavigate,
                    onLogout = {}
                )
            }
        }
        composeTestRule.onNodeWithText(
            Destination.Upgrade.title
        ).performClick()

        verify(onNavigate).invoke(Destination.Upgrade)
    }
    //</editor-fold>

    //<editor-fold desc="Settings">
    @Test
    fun Settings_Displayed() {
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = {},
                    onLogout = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            Destination.Settings.title
        ).assertIsDisplayed()
    }

    @Test
    fun Settings_Navigation_Callback_Triggered() {
        val onNavigate: (destination: Destination) -> Unit = mock()
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = onNavigate,
                    onLogout = {}
                )
            }
        }
        composeTestRule.onNodeWithText(
            Destination.Settings.title
        ).performClick()

        verify(onNavigate).invoke(Destination.Settings)
    }
    //</editor-fold>

    //<editor-fold desc="Logout">
    @Test
    fun Logout_Displayed() {
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = {},
                    onLogout = {}
                )
            }
        }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.log_out)
        ).assertIsDisplayed()
    }

    @Test
    fun Logout_Navigation_Callback_Triggered() {
        val onLogout: () -> Unit = mock()
        composeTestRule.setContent {
            Column {
                DrawerContent(
                    onNavigate = {},
                    onLogout = onLogout
                )
            }
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.log_out)
        ).performClick()

        verify(onLogout).invoke()
    }
    //</editor-fold>
}