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
import academy.compose.messaging.Tags.TAG_EMPTY
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmptyConversation(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.testTag(TAG_EMPTY),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.label_no_messages))
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_Empty() {
    MaterialTheme {
        EmptyConversation(
            modifier = Modifier.fillMaxSize()
        )
    }
}