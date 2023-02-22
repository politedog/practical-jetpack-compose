package academy.compose.editor.emoji

import academy.compose.editor.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiOption(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (text: String) -> Unit
) {
    Text(
        text = text,
        fontSize = 20.sp,
        modifier = modifier
            .padding(16.dp)
            .clickable(onClickLabel = stringResource(id = R.string.cd_select_emoji, text)) {
                onClick(text)
            }
    )
}