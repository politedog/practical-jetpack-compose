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

import academy.compose.authentication.model.AuthenticationState
import academy.compose.authentication.ui.Authentication
import academy.compose.authentication.ui.AuthenticationContent
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATE_BUTTON
import academy.compose.authentication.ui.Tags.TAG_AUTHENTICATION_TOGGLE
import academy.compose.authentication.ui.Tags.TAG_CONTENT
import academy.compose.authentication.ui.Tags.TAG_ERROR_ALERT
import academy.compose.authentication.ui.Tags.TAG_INPUT_EMAIL
import academy.compose.authentication.ui.Tags.TAG_INPUT_PASSWORD
import academy.compose.authentication.ui.Tags.TAG_PROGRESS
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class AuthenticationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    //<editor-fold desc="Authentication Mode">
    @Test
    fun Sign_In_Title_Displayed_By_Default() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.title_sign_in)
        ).assertIsDisplayed()
    }

    @Test
    fun Need_Account_Displayed_By_Default() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.action_need_account)
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Title_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.action_need_account)
        ).performClick()

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.title_sign_up)
        ).assertIsDisplayed()
    }

    @Test
    fun Sign_Up_Button_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(
            TAG_AUTHENTICATION_TOGGLE
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_sign_up)
            )
    }

    @Test
    fun Already_Have_Account_Displayed_After_Toggle() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATION_TOGGLE).apply {
            performClick()
            assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.action_already_have_account)
            )
        }
    }
    //</editor-fold>

    //<editor-fold desc="Content Validity">
    @Test
    fun Authentication_Button_Disabled_By_Default() {
        composeTestRule.setContent { Authentication() }

        composeTestRule
            .onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsNotEnabled()
    }

    @Test
    fun Authentication_Button_Enabled_With_Valid_Content() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(TAG_INPUT_EMAIL)
            .performTextInput("contact@compose.academy")
        composeTestRule.onNodeWithTag(TAG_INPUT_PASSWORD)
            .performTextInput("password")

        composeTestRule.onNodeWithTag(TAG_AUTHENTICATE_BUTTON)
            .assertIsEnabled()
    }
    //</editor-fold>

    //<editor-fold desc="Error alert">
    @Test
    fun Error_Alert_Not_Displayed_By_Default() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertDoesNotExist()
    }

    @Test
    fun Error_Alert_Displayed_After_Error() {
        composeTestRule.setContent {
            AuthenticationContent(
                state = AuthenticationState(error = "Some error"),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_ERROR_ALERT)
            .assertIsDisplayed()
    }
    //</editor-fold>

    //<editor-fold desc="Loading state">
    @Test
    fun Progress_Not_Displayed_By_Default() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(TAG_PROGRESS)
            .assertDoesNotExist()
    }

    @Test
    fun Progress_Displayed_While_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(state = AuthenticationState(isLoading = true), handleEvent = {})
        }
        composeTestRule.onNodeWithTag(TAG_PROGRESS)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Displayed_When_Not_Loading() {
        composeTestRule.setContent { Authentication() }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Not_Displayed_When_Loading() {
        composeTestRule.setContent {
            AuthenticationContent(state = AuthenticationState(isLoading = true), handleEvent = { })
        }

        composeTestRule.onNodeWithTag(TAG_CONTENT)
            .assertDoesNotExist()
    }
    //</editor-fold>
}