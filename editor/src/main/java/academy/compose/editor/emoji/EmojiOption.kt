/*
 * Copyright 2022 Compose Academy
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
package academy.compose.editor.emoji

import academy.compose.editor.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiOption(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (text: String) -> Unit
) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .clickable(onClickLabel = stringResource(id = R.string.cd_select_emoji, text)) {
                onClick(text)
            },
        text = text,
        fontSize = 20.sp
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiOption() {
    EmojiOption(
        modifier = Modifier.wrapContentSize(),
        text = "🥳",
        onClick = {}
    )
}