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

package academy.compose.authentication

import academy.compose.authentication.ui.PasswordInput
import academy.compose.authentication.ui.Tags.TAG_INPUT_PASSWORD
import academy.compose.authentication.ui.Tags.TAG_PASSWORD_TOGGLE
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextRange
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class PasswordInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Password_Displayed() {
        val password = "password123"
        composeTestRule.setContent {
            PasswordInput(password = password, onPasswordChanged = {}, onDoneClicked = {})
        }
        composeTestRule.onNodeWithTag(TAG_PASSWORD_TOGGLE + "true")
            .performClick()
        composeTestRule.onNodeWithTag(TAG_INPUT_PASSWORD)
            .assert(hasText(password))
    }

    @ExperimentalTestApi
    @Test
    fun Password_Changed_Triggered() {
        val onPasswordChanged: (password: String) -> Unit = mock()
        val password = "password123"
        composeTestRule.setContent {
            PasswordInput(
                password = password,
                onPasswordChanged = onPasswordChanged,
                onDoneClicked = {}
            )
        }
        val passwordText = "456"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_PASSWORD)
            .apply {
                performTextInputSelection(TextRange(password.length))
                performTextInput(passwordText)
            }

        verify(onPasswordChanged).invoke(password + passwordText)
    }

    @Test
    fun Password_Toggled_Reflects_State() {
        composeTestRule.setContent {
            PasswordInput(password = "password123", onPasswordChanged = {}, onDoneClicked = {})
        }

        composeTestRule
            .onNodeWithTag(TAG_PASSWORD_TOGGLE + "true")
            .performClick()

        composeTestRule
            .onNodeWithTag(TAG_PASSWORD_TOGGLE + "false")
            .assertIsDisplayed()
    }
}