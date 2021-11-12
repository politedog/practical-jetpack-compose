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

import academy.compose.messaging.R
import academy.compose.messaging.Tags.TAG_HEADER
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    TopAppBar(
        modifier = modifier.testTag(TAG_HEADER)
    ) {
        IconButton(onClick = {
            onClose()
        }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.cd_close_conversation)
            )
        }
        Text(text = stringResource(id = R.string.title_chat), fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Header() {
    MaterialTheme {
        Header(
            modifier = Modifier.fillMaxWidth()
        ) {

        }
    }
}