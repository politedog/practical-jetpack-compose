package academy.compose.editor.ui

import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorState
import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.graphics.drawscope.*

@ExperimentalComposeUiApi
@Composable
fun DrawingArea(
    modifier: Modifier = Modifier,
    state: EditorState,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Canvas(modifier = modifier
        .pointerInteropFilter { event ->
            if (state.selectedTool is EditorTool.Brush) {
                handleEvent(
                    EditorEvent.BrushEvent(
                        event, event.x, event.y
                    )
                )
            }
            true
        }) {
        (state.drawingObjects + state.currentDrawingPath).filterIsInstance<EditorObject.BrushPath>().forEach { drawingObject ->
            drawPath(
                path = drawingObject.path.value,
                color = drawingObject.brushConfiguration.color,
                alpha = 1F,
                style = Stroke(
                    drawingObject.brushConfiguration.thickness,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}