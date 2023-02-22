package academy.compose.editor.model

import academy.compose.editor.R
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.InsertEmoticon
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.ui.graphics.vector.ImageVector

sealed class EditorTool(
    @StringRes val description: Int,
    val icon: ImageVector
) {

    class Text :
        EditorTool(R.string.label_text, Icons.Default.TextFormat)

    class Brush :
        EditorTool(R.string.label_brush, Icons.Default.Brush)

    class Emoji :
        EditorTool(R.string.label_emoji, Icons.Default.InsertEmoticon)

}