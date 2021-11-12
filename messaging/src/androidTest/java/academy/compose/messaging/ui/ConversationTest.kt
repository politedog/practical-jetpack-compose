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

import academy.compose.messaging.TestDataFactory
import academy.compose.messaging.model.ConversationState
import academy.compose.messaging.Tags.TAG_HEADER
import academy.compose.messaging.Tags.TAG_INPUT_BAR
import academy.compose.messaging.Tags.TAG_MESSAGES
import academy.compose.messaging.Tags.TAG_MESSAGE_ACTIONS
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class ConversationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Header_Displayed() {
        composeTestRule.setContent {
            Conversation(
                state = ConversationState(),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_HEADER)
            .assertIsDisplayed()
    }

    @Test
    fun Messages_Displayed() {
        composeTestRule.setContent {
            Conversation(
                state = ConversationState(),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGES)
            .assertIsDisplayed()
    }

    @Test
    fun Input_Bar_Displayed() {
        composeTestRule.setContent {
            Conversation(
                state = ConversationState(),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_INPUT_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Message_Actions_Not_Displayed() {
        composeTestRule.setContent {
            Conversation(
                state = ConversationState(),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_ACTIONS)
            .assertDoesNotExist()
    }

    @Test
    fun Message_Actions_Displayed() {
        composeTestRule.setContent {
            Conversation(
                state = ConversationState(
                    selectedMessage = TestDataFactory.makeMessage()
                ),
                handleEvent = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_MESSAGE_ACTIONS)
            .assertIsDisplayed()
    }

}