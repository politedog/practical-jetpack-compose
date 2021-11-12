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

import academy.compose.messaging.MessageFactory
import academy.compose.messaging.ui.message.Messages
import academy.compose.messaging.Tags.TAG_EMPTY
import academy.compose.messaging.Tags.TAG_MESSAGE
import academy.compose.messaging.Tags.TAG_MESSAGES
import academy.compose.messaging.util.groupMessagesByDate
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MessagesTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Messages_Displayed() {
        val messages = MessageFactory.makeMessages()
        composeTestRule.setContent {
            Messages(
                messages = MessageFactory.makeMessages(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }
        groupMessagesByDate(messages).forEach { entry ->
            composeTestRule.onNodeWithTag(TAG_MESSAGES)
                .onChildren()
                .assertAny(hasTestTag(entry.key.timeInMillis.toString()))

            entry.value.forEach { message ->
                composeTestRule.onNodeWithTag(TAG_MESSAGES)
                    .onChildren()
                    .assertAny(hasTestTag(TAG_MESSAGE + message.id))
            }
        }
    }

    @Test
    fun Empty_Messages_Never_Displayed() {
        composeTestRule.setContent {
            Messages(
                messages = MessageFactory.makeMessages(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_EMPTY)
            .assertDoesNotExist()
    }

    @Test
    fun Empty_Messages_Displayed() {
        composeTestRule.setContent {
            Messages(
                messages = emptyList(),
                onMessageSelected = { },
                unSendMessage = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_EMPTY)
            .assertIsDisplayed()
    }

}
