package academy.compose.editor.emoji

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
                emojis = emojiRow,
                onSelected = onEmojiSelected
            )
        }
    }
}

@Composable
fun EmojiRow(
    emojis: List<String>,
    onSelected: (emoji: String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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