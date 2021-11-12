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

package academy.compose.inbox

import academy.compose.inbox.model.EmailFactory
import academy.compose.inbox.model.InboxEvent
import academy.compose.inbox.model.InboxState
import academy.compose.inbox.model.InboxStatus
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class InboxViewModel : ViewModel() {

    val uiState = MutableStateFlow(InboxState())

    fun loadContent() {
        uiState.value = uiState.value.copy(
            status = InboxStatus.LOADING
        )
        uiState.value = uiState.value.copy(
            status = InboxStatus.SUCCESS,
            emails = EmailFactory.makeContentList()
        )
    }

    private fun deleteEmail(id: String) {
        uiState.value = uiState.value.copy(
            emails = uiState.value.emails?.filter {
                it.id != id
            }
        )
    }

    fun handleEvent(inboxEvent: InboxEvent) {
        when (inboxEvent) {
            is InboxEvent.RefreshContent -> loadContent()
            is InboxEvent.DeleteEmail -> deleteEmail(inboxEvent.id)
        }
    }

}