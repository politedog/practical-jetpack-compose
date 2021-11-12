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
import academy.compose.inbox.Tags
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier.testTag(Tags.TAG_ERROR),
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.error_status)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            onTryAgain()
        }) {
            Text(text = stringResource(id = R.string.label_try_again))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ErrorState() {
    MaterialTheme {
        ErrorState(modifier = Modifier.fillMaxWidth(), onTryAgain = {})
    }
}