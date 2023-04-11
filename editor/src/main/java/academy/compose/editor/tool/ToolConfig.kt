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

import academy.compose.editor.model.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ToolConfig(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    configuration: BrushConfiguration,
    currentObject: EditorObject?,
    handleEvent: (event: EditorEvent) -> Unit,
    addText: (test: String, color: Color) -> Unit
) {
    if (selectedTool is EditorTool.Brush) {
        BrushSettings(
            modifier = modifier,
            brushConfiguration = configuration,
            onColorSelected = {
                handleEvent(EditorEvent.UpdateToolColor(it))
            },
            onThicknessChanged = {
                handleEvent(EditorEvent.UpdateToolThickness(it))
            },
            onClose = {

            }
        )
    } else if (selectedTool is EditorTool.Text) {
        TextSettings(
            modifier = modifier,
            addText = addText
        )
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_ToolConfigurationBrush() {
    ToolConfig(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Brush,
        configuration = BrushConfiguration(),
        currentObject = null,
        handleEvent = {},
        addText = { text, color -> }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_ToolConfigurationText() {
    ToolConfig(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Text,
        configuration = BrushConfiguration(),
        currentObject = null,
        handleEvent = {},
        addText = { text, color -> }
    )
}