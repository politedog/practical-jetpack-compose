/*
 * Copyright 2022 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package academy.compose.editor.ui

import academy.compose.editor.R
import academy.compose.editor.model.BrushConfiguration
import academy.compose.editor.model.EditorTool
import academy.compose.editor.tool.ToolConfig
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (color: Color) -> Unit,
    onClose: (() -> Unit)?
) {
    val colors = listOf(
        Color.Black, Color.Blue, Color.White, Color.Gray, Color.DarkGray, Color.Red,
        Color.Cyan, Color.Green, Color.Magenta, Color.Yellow
    )
    Row(
        modifier = modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        onClose?.let {
            IconButton(onClick = { onClose() }) {
                Icon(
                    modifier = Modifier
                        .padding(end = 12.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.cd_close_color_selection),
                    tint = Color.White
                )
            }
        }
        colors.forEach { color ->
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
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ColorPicker() {
    ColorPicker(
        modifier = Modifier.wrapContentSize(),
        selectedColor = Color.Black,
        onColorSelected = {},
        onClose = {}
    )
}
