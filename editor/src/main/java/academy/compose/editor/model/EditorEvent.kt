package academy.compose.editor.model

import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

sealed class EditorEvent {

    class ToolSelected(val tool: EditorTool) : EditorEvent()

    object UnselectTool : EditorEvent()

    object Undo : EditorEvent()

    object CloseEditor : EditorEvent()

    class TransformObject(
        val id: String,
        val offset: Offset,
        val rotation: Float? = null,
        val scale: Float? = null
    ) : EditorEvent()

    class BrushEvent(
        val event: MotionEvent,
        val x: Float,
        val y: Float
    ) : EditorEvent()

    class AddText(
        val x: Float,
        val y: Float,
        val width: Int,
        val height: Int,
        val text: String,
        val color: Color? = null
    ) : EditorEvent()

    class UpdateToolColor(
        val color: Color
    ): EditorEvent()

    class UpdateToolThickness(
        val thickness: Float
    ): EditorEvent()
}