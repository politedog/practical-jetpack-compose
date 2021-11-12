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

import academy.compose.authentication.model.AuthenticationMode
import academy.compose.authentication.ui.AuthenticationButton
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATE_BUTTON
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class AuthenticationButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Sign_In_Action_Displayed() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_IN,
                enableAuthentication = true
            ) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_in)
            )
    }

    @Test
    fun Sign_Up_Action_Displayed() {
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = true
            ) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_up)
            )
    }

    @Test
    fun Authenticate_Triggered() {
        val authenticate: () -> Unit = mock()
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = true,
                onAuthenticate = authenticate
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .performClick()

        verify(authenticate).invoke()
    }

    @Test
    fun Authenticate_Never_Triggered() {
        val authenticate: () -> Unit = mock()
        composeTestRule.setContent {
            AuthenticationButton(
                authenticationMode = AuthenticationMode.SIGN_UP,
                enableAuthentication = false,
                onAuthenticate = authenticate
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .performClick()

        verify(authenticate, never()).invoke()
    }
}