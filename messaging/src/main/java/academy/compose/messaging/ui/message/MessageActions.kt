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

import academy.compose.messaging.R
import academy.compose.messaging.Tags.TAG_MESSAGE_ACTIONS
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun MessageActions(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onUnsendMessage: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Box(
            modifier = modifier
                .background(MaterialTheme.colors.surface)
                .sizeIn(minWidth = 280.dp, minHeight = 80.dp)
                .testTag(TAG_MESSAGE_ACTIONS),
            contentAlignment = Alignment.Center
        ) {
            TextButton(onClick = {
                onUnsendMessage()
            }) {
                Text(text = stringResource(id = R.string.action_unsend_message))
            }
        }
    }
}

@Preview
@Composable
fun Preview_MessageActions() {
    MaterialTheme {
        MessageActions(
            modifier = Modifier.fillMaxSize(),
            onDismiss = { },
            onUnsendMessage =  { }
        )
    }
}