package academy.compose.editor.model

data class EditorState(
    val selectedTool: EditorTool? = null,
    val brushConfiguration: BrushConfiguration = BrushConfiguration(),
    val drawingObjects: List<EditorObject> = emptyList(),
    val currentDrawingPath: EditorObject.BrushPath? = null
)