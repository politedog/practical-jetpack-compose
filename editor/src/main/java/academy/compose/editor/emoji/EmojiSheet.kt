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

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun EmojiSheet(
    modifier: Modifier = Modifier,
    onEmojiSelected: (emoji: String) -> Unit
) {
    val emojis = listOf(
        "❤️", "🙌", "🥳️", "👏️",
        "🤩️", "👀", "🙄", "😇",
        "😂️", "😅", "🤣", "🙃"
    )
    Column(modifier = modifier) {
        emojis.chunked(4).map { emojiRow ->
            EmojiRow(
                modifier = Modifier.fillMaxWidth(),
                emojis = emojiRow,
                onSelected = onEmojiSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiSheet() {
    EmojiSheet(
        modifier = Modifier.padding(16.dp),
        onEmojiSelected = {}
    )
}

@Composable
fun EmojiRow(
    modifier: Modifier,
    emojis: List<String>,
    onSelected: (emoji: String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        emojis.forEach { emoji ->
            EmojiOption(
                text = emoji,
                onClick = onSelected
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_EmojiRow() {
    EmojiRow(
        modifier = Modifier.padding(16.dp),
        emojis = listOf(
            "❤️", "🙌", "🥳️", "👏️"
        ),
        onSelected = {}
    )
}