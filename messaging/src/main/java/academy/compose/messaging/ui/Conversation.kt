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
import academy.compose.messaging.model.ConversationEvent
import academy.compose.messaging.model.ConversationState
import academy.compose.messaging.ui.input.InputBar
import academy.compose.messaging.ui.message.MessageActions
import academy.compose.messaging.ui.message.Messages
import academy.compose.messaging.ContactFactory
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Conversation(
    modifier: Modifier = Modifier,
    state: ConversationState,
    handleEvent: (event: ConversationEvent) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Header(
            modifier = Modifier.fillMaxWidth(),
            onClose = {
                // finish activity
            }
        )
        Messages(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            messages = state.messages,
            onMessageSelected = { messageId ->
                handleEvent(ConversationEvent.SelectMessage(messageId))
            },
            unSendMessage = { messageId ->
                handleEvent(ConversationEvent.UnsendMessage(messageId))
            }
        )
        InputBar(
            modifier = Modifier.fillMaxWidth(),
            sendMessage = { message ->
                handleEvent(ConversationEvent.SendMessage(message))
            },
            contacts = state.contacts
        )
    }
    if (state.selectedMessage != null) {
        MessageActions(
            onDismiss = {
                handleEvent(ConversationEvent.UnselectMessage)
            },
            onUnsendMessage = {
                handleEvent(ConversationEvent.UnsendMessage(state.selectedMessage.id))
            }
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_Conversation() {
    MaterialTheme {
        Conversation(
            modifier = Modifier.fillMaxSize(),
            state = ConversationState(
                messages = MessageFactory.makeMessages(),
                contacts = ContactFactory.makeContacts()
            ),
            handleEvent = { }
        )
    }
}