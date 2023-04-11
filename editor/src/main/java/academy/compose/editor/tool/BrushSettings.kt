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
package academy.compose.editor.tool

import academy.compose.editor.model.BrushConfiguration
import academy.compose.editor.model.EditorTool
import academy.compose.editor.ui.ColorPicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BrushSettings(
    modifier: Modifier = Modifier,
    brushConfiguration: BrushConfiguration,
    onColorSelected: (color: Color) -> Unit,
    onThicknessChanged: (thickness: Float) -> Unit,
    onClose: () -> Unit
) {
    Column(modifier = modifier) {
        Slider(
            modifier = Modifier
                .align(Start)
                .rotate(270f)
                .offset(x = 0.dp, y = (-165).dp)
                .weight(1f),
            valueRange = 1f..50f,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.White,
                inactiveTrackColor = Color.White.copy(alpha = 0.4f)
            ),
            value = brushConfiguration.thickness,
            onValueChange = {
                onThicknessChanged(it)
            }
        )
        Surface(
            color = Color.Black.copy(alpha = 0.4f),
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
        ) {
            ColorPicker(
                onColorSelected = onColorSelected,
                selectedColor = brushConfiguration.color,
                onClose = onClose
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_BrushSettings() {
    BrushSettings(
        modifier = Modifier.wrapContentSize(),
        brushConfiguration = BrushConfiguration(),
        onColorSelected = {},
        onThicknessChanged = {},
        onClose = {},
    )
}
