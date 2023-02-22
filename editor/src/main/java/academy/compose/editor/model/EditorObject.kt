package academy.compose.editor.model

import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

sealed class EditorObject(val id: String? = null) {

    data class BrushPath(
        val path: MutableState<Path>,
        val brushConfiguration: BrushConfiguration
    ) : EditorObject()

    data class Text(
        val textId: String,
        val text: String,
        val offset: Offset,
        val scale: Float = 1f,
        val rotation: Float = 1f,
        val textSize: Float = 60f,
        val color: Color = Color.White
    ) : EditorObject(textId)

}