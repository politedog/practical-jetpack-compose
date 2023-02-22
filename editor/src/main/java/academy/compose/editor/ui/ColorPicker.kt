package academy.compose.editor.ui

import academy.compose.editor.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (color: Color) -> Unit,
    onClose: () -> Unit,
    isVertical: Boolean = false
) {
    val colors = listOf(
        Color.Black, Color.Blue, Color.White, Color.Gray, Color.DarkGray, Color.Red,
        Color.Cyan, Color.Green, Color.Magenta, Color.Yellow
    )
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isVertical) {
            item {
                IconButton(onClick = { onClose() }) {
                    Icon(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 12.dp)
                            .clickable { },
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.cd_close_color_selection),
                        tint = Color.White
                    )
                }
            }
        }
        itemsIndexed(colors) { index, color ->
            Box(
                modifier = Modifier
                    .background(
                        color = if (color == selectedColor) color else color.copy(alpha = 0.7f),
                        shape = CircleShape
                    )
                    .border(
                        width = if (color == selectedColor) 4.dp else 2.dp,
                        color = if (color == selectedColor) {
                            MaterialTheme.colors.primary
                        } else {
                            Color.White
                        },
                        shape = CircleShape
                    )
                    .size(35.dp)
                    .toggleable(color == selectedColor) {
                        onColorSelected(color)
                    }
            )
            if (index < colors.count() - 1) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}