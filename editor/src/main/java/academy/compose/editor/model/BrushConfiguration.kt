package academy.compose.editor.model

import androidx.compose.ui.graphics.Color

data class BrushConfiguration(
    val color: Color = Color.Black,
    val thickness: Float = 20F
)
