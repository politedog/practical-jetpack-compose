package academy.compose.editor.tool

import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorState
import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ToolConfig(
    state: EditorState,
    handleEvent: (event: EditorEvent) -> Unit,
    addText: (test: String, color: Color) -> Unit
) {
    if (state.selectedTool is EditorTool.Brush) {
        BrushSettings(
            modifier = Modifier.fillMaxSize(),
            brushConfiguration = state.brushConfiguration,
            onColorSelected = {
                handleEvent(EditorEvent.UpdateToolColor(it))
            },
            onThicknessChanged = {
                handleEvent(EditorEvent.UpdateToolThickness(it))
            })
    } else if (state.selectedTool is EditorTool.Text) {
        TextSettings(
            modifier = Modifier.fillMaxSize(),
            text = state.drawingObjects.lastOrNull { it is EditorObject.Text } as EditorObject.Text?,
            addText = addText
        )
    }
}