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

package academy.compose.messaging.ui.message

import academy.compose.messaging.MessageFactory
import academy.compose.messaging.R
import academy.compose.messaging.model.Message
import academy.compose.messaging.Tags.TAG_MESSAGE
import academy.compose.messaging.Tags.TAG_MESSAGES
import academy.compose.messaging.ui.EmptyConversation
import academy.compose.messaging.util.groupMessagesByDate
import academy.compose.messaging.util.isToday
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun Messages(
    modifier: Modifier = Modifier,
    messages: List<Message>? = null,
    onMessageSelected: (messageId: String) -> Unit,
    unSendMessage: (messageId: String) -> Unit
) {
    if (messages.isNullOrEmpty()) {
        EmptyConversation(
            modifier = Modifier.fillMaxSize()
        )
    } else {
        val grouped = groupMessagesByDate(messages)
        val scrollState = rememberLazyListState()
        LazyColumn(
            modifier = modifier.testTag(TAG_MESSAGES),
            state = scrollState,
            reverseLayout = true,
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            grouped.onEach { entry ->

                items(entry.value) { message ->
                    val unSendMessageLabel = stringResource(id = R.string.action_unsend_message)
                    Message(
                        Modifier
                            .semantics {
                                customActions = listOf(
                                    CustomAccessibilityAction(unSendMessageLabel) {
                                        unSendMessage(message.id)
                                        true
                                    }
                                )
                            }
                            .fillMaxWidth()
                            .testTag(TAG_MESSAGE + message.id),
                        message = message,
                        onLongPress = { messageId ->
                            onMessageSelected(messageId)
                        }
                    )
                }

                stickyHeader {
                    MessageHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag(entry.key.timeInMillis.toString()),
                        isToday = isToday(entry.key),
                        date = entry.key
                    )
                }
            }
        }
        LaunchedEffect(messages) {
            scrollState.scrollToItem(0)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_Messages() {
    MaterialTheme {
        Messages(
            modifier = Modifier.fillMaxSize(),
            messages = MessageFactory.makeMessages(),
            onMessageSelected = {},
            unSendMessage = {}
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_Messages_Empty() {
    MaterialTheme {
        Messages(
            modifier = Modifier.fillMaxSize(),
            messages = emptyList(),
            onMessageSelected = {},
            unSendMessage = {}
        )
    }
}