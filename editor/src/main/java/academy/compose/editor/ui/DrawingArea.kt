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

import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorState
import academy.compose.editor.model.EditorTool
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.graphics.drawscope.*

@ExperimentalComposeUiApi
@Composable
fun DrawingArea(
    modifier: Modifier = Modifier,
    selectedTool: EditorTool?,
    drawingObjects: List<EditorObject>,
    currentPath: EditorObject?,
    handleEvent: (event: EditorEvent) -> Unit
) {
    Canvas(modifier = modifier
        .pointerInteropFilter { event ->
            if (selectedTool is EditorTool.Brush) {
                handleEvent(
                    EditorEvent.BrushEvent(
                        event
                    )
                )
            }
            true
        }) {
        (drawingObjects + currentPath).filterIsInstance<EditorObject.BrushPath>().forEach { drawingObject ->
            drawPath(
                path = drawingObject.path.value,
                color = drawingObject.brushConfiguration.color,
                alpha = 1F,
                style = Stroke(
                    drawingObject.brushConfiguration.thickness,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }
}