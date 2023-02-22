package academy.compose.editor.tool

import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun Tool(
    modifier: Modifier = Modifier,
    tool: EditorTool,
    onSelected: (tool: EditorTool) -> Unit
) {
    val selected = false
    Icon(
        modifier = modifier
            .background(
                color = if (selected) {
                    MaterialTheme.colors.surface
                } else {
                    Color.Unspecified
                },
                shape = CircleShape
            )
            .padding(8.dp)
            .toggleable(
                value = selected,
                onValueChange = {
                    onSelected(tool)
                }
            ),
        imageVector = tool.icon,
        contentDescription = stringResource(id = tool.description),
        tint = Color.White
    )
}