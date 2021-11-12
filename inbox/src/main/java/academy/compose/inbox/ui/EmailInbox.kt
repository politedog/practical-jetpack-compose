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

package academy.compose.inbox.ui

import academy.compose.inbox.R
import academy.compose.inbox.model.InboxEvent
import academy.compose.inbox.model.InboxState
import academy.compose.inbox.model.InboxStatus
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun EmailInbox(
    modifier: Modifier = Modifier,
    inboxState: InboxState,
    handleEvent: (event: InboxEvent) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.surface,
                elevation = 0.dp
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(
                        id = R.string.inbox_emails,
                        inboxState.emails?.count() ?: 0),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (inboxState.status) {
                InboxStatus.LOADING -> Loading()
                InboxStatus.ERROR -> {
                    ErrorState(modifier = Modifier.fillMaxWidth()) {
                        handleEvent(InboxEvent.RefreshContent)
                    }
                }
                InboxStatus.SUCCESS -> {
                    EmailList(
                        modifier = Modifier.fillMaxSize(),
                        emails = inboxState.emails!!,
                        onEmailDeleted = { id ->
                            handleEvent(InboxEvent.DeleteEmail(id))
                        }
                    )
                }
                else -> {
                    EmptyState(modifier = Modifier.fillMaxWidth()) {
                        handleEvent(InboxEvent.RefreshContent)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun Preview_EmailInbox_Success() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.SUCCESS
        ), handleEvent = { })
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun Preview_EmailInbox_Loading() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.LOADING
        ), handleEvent = { })
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun Preview_EmailInbox_Error() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.ERROR
        ), handleEvent = { })
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun Preview_EmailInbox_Empty() {
    MaterialTheme {
        EmailInbox(inboxState = InboxState(
            status = InboxStatus.EMPTY
        ), handleEvent = { })
    }
}