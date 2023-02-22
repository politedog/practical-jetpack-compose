package academy.compose.editor.ui

import academy.compose.editor.R
import academy.compose.editor.model.EditorTool
import academy.compose.editor.tool.Tool
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun EditorToolBar(
    modifier: Modifier = Modifier,
    onToolSelected: (tool: EditorTool) -> Unit,
    closeEditor: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { closeEditor() }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.cd_close_story_editor),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Tool(Modifier.padding(end = 12.dp), EditorTool.Text(), onToolSelected)
        Tool(Modifier.padding(end = 12.dp), EditorTool.Brush(), onToolSelected)
        Tool(Modifier.padding(end = 12.dp), EditorTool.Emoji(), onToolSelected)
    }
}