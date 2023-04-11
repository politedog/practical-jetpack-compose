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

import academy.compose.editor.*
import academy.compose.editor.model.BrushConfiguration
import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Undo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ActionsBar(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    drawingObjects: List<EditorObject>?,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Surface(
        modifier = modifier,
        color = Color.Black.copy(alpha = 0.4f)
    ) {
        if (selectedTool == null) {
            EditorToolBar(
                modifier = Modifier.fillMaxWidth(),
                onToolSelected = {
                    handleEvent(EditorEvent.ToolSelected(it))
                },
                closeEditor = {
                    handleEvent(EditorEvent.CloseEditor)
                }
            )
        } else if (selectedTool is EditorTool.Brush) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (!drawingObjects.isNullOrEmpty()) {
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

@Preview(showBackground = false)
@Composable
fun Preview_ActionsBar() {
    ActionsBar(
        modifier = Modifier.wrapContentSize(),
        selectedTool = EditorTool.Brush,
        drawingObjects = listOf(EditorObject.BrushPath(
            remember {
                mutableStateOf(Path())
            },
            BrushConfiguration()
        )),
        handleEvent = { }
    )
}