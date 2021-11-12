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
import academy.compose.authentication.ui.AuthenticationTitle
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class AuthenticationTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Sign_In_Title_Displayed() {
        composeTestRule.setContent {
            AuthenticationTitle(authenticationMode = AuthenticationMode.SIGN_IN)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.title_sign_in)
            )
            .assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Title_Displayed() {
        composeTestRule.setContent {
            AuthenticationTitle(authenticationMode = AuthenticationMode.SIGN_UP)
        }

        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.title_sign_up)
            )
            .assertIsDisplayed()
    }
}