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
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATION_TOGGLE
import academy.compose.authentication.ui.ToggleAuthenticationMode
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AuthenticationModeToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Need_Account_Action_Displayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(authenticationMode = AuthenticationMode.SIGN_IN) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_need_account)
            )
    }

    @Test
    fun Already_Have_Account_Action_Displayed() {
        composeTestRule.setContent {
            ToggleAuthenticationMode(authenticationMode = AuthenticationMode.SIGN_UP) {

            }
        }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_already_have_account)
            )
    }

    @Test
    fun Toggle_Authentication_Triggered() {
        val toggleAuthentication: () -> Unit = mock()
        composeTestRule.setContent {
            ToggleAuthenticationMode(
                authenticationMode = AuthenticationMode.SIGN_UP,
                toggleAuthentication = toggleAuthentication
            )
        }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE)
            .performClick()

        verify(toggleAuthentication).invoke()
    }
}