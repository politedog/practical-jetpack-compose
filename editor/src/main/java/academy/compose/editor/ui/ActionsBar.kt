package academy.compose.editor.ui

import academy.compose.editor.*
import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun ActionsBar(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    drawingObjects: List<EditorObject>?,
    showEmojiPicker: () -> Unit,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Surface(
        modifier = modifier,
        color = Color.Black.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (selectedTool == null) {
                EditorToolBar(
                    modifier = Modifier.fillMaxWidth(),
                    onToolSelected = {
                        if (it is EditorTool.Emoji) {
                            showEmojiPicker()
                        } else {
                            handleEvent(EditorEvent.ToolSelected(it))
                        }
                    },
                    closeEditor = {
                        handleEvent(EditorEvent.CloseEditor)
                    }
                )
            } else if (selectedTool is EditorTool.Brush &&
                !drawingObjects.isNullOrEmpty()
            ) {
                IconButton(onClick = {
                    handleEvent(EditorEvent.Undo)
                }) {
                    Icon(
                        imageVector = Icons.Default.Undo,
                        contentDescription = stringResource(id = R.string.cd_undo),
                        tint = Color.White
                    )
                }
            }
            if (selectedTool is EditorTool.Brush) {
                Spacer(Modifier.weight(1f))
                IconButton(onClick = {
                    handleEvent(EditorEvent.UnselectTool)
                }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(id = R.string.cd_done),
                        tint = Color.White
                    )
                }
            }
        }
    }
}