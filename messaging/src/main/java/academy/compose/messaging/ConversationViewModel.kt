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

package academy.compose.messaging

import academy.compose.messaging.model.ConversationEvent
import academy.compose.messaging.model.ConversationState
import academy.compose.messaging.model.Message
import academy.compose.messaging.model.MessageDirection
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.*

class ConversationViewModel : ViewModel() {

    val uiState = MutableStateFlow(ConversationState())

    fun handleEvent(authenticationEvent: ConversationEvent) {
        when (authenticationEvent) {
            is ConversationEvent.SendMessage -> {
                uiState.value = uiState.value.copy(
                    messages = uiState.value.messages.toMutableList().apply {
                        add(
                            Message(
                                uiState.value.messages.count().toString(),
                                MessageDirection.SENT, Calendar.getInstance(), "me",
                                authenticationEvent.message
                            )
                        )
                    }.toList()
                )
            }
            is ConversationEvent.UnsendMessage -> {
                uiState.value = uiState.value.copy(
                    messages = uiState.value.messages.toMutableList().apply {
                        removeAt(uiState.value.messages.indexOfFirst {
                            it.id == authenticationEvent.id
                        })
                    }.toList(),
                    selectedMessage = null
                )
            }
            is ConversationEvent.SelectMessage -> {
                uiState.value = uiState.value.copy(
                    selectedMessage = uiState.value.messages.find { it.id == authenticationEvent.id }
                )
            }
            ConversationEvent.UnselectMessage -> {
                uiState.value = uiState.value.copy(
                    selectedMessage = null
                )
            }
        }
    }
}


