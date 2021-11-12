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

import academy.compose.authentication.ui.EmailInput
import academy.compose.authentication.ui.Tags.TAG_INPUT_EMAIL
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.TextRange
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class EmailInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Email_Displayed() {
        val email = "contact@compose.academy"
        composeTestRule.setContent {
            EmailInput(email = email, onEmailChanged = {}, onNextClicked = { })
        }

        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .assert(hasText(email))
    }

    @ExperimentalTestApi
    @Test
    fun Email_Change_Triggered() {
        val onEmailChanged: (email: String) -> Unit = mock()
        val email = "contact@compose.academy"
        composeTestRule.setContent {
            EmailInput(email = email, onEmailChanged = onEmailChanged, onNextClicked = { })
        }

        val appendedText = ".jetpack"
        composeTestRule
            .onNodeWithTag(TAG_INPUT_EMAIL)
            .apply {
                performTextInputSelection(TextRange(email.length))
                performTextInput(appendedText)
            }

        verify(onEmailChanged).invoke(email + appendedText)
    }
}