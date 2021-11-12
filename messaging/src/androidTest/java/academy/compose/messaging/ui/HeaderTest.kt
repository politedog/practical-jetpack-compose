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

package academy.compose.messaging.ui

import academy.compose.messaging.R
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

class HeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        composeTestRule.setContent {
            Header(onClose = {})
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.title_chat)
        ).assertIsDisplayed()
    }

    @Test
    fun Close_Icon_Displayed() {
        composeTestRule.setContent {
            Header(onClose = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.cd_close_conversation)
        ).assertIsDisplayed()
    }

    @Test
    fun Close_Listener_Triggered() {
        val onClose: () -> Unit = mock()
        composeTestRule.setContent {
            Header(onClose = onClose)
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation()
                .targetContext.getString(R.string.cd_close_conversation)
        ).performClick()

        verify(onClose).invoke()
    }
}