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
package academy.compose.editor

import academy.compose.editor.model.*
import android.view.MotionEvent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import java.util.UUID
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class EditorViewModel : ViewModel() {

    val uiState = MutableStateFlow(EditorState())

    fun handleEvent(contentEvent: EditorEvent) {
        when (contentEvent) {
            is EditorEvent.ToolSelected -> {
                uiState.value = uiState.value.copy(
                    selectedTool = contentEvent.tool
                )
            }
            is EditorEvent.TransformObject -> {
                val selectedObject = uiState.value.drawingObjects.find {
                    it.id == contentEvent.id
                } as EditorObject.Text
                val scale = selectedObject.scale * contentEvent.scale
                val rotation = selectedObject.rotation + contentEvent.rotation

                val transformedOffset = contentEvent.offset.copy(
                    x = contentEvent.offset.x * scale,
                    y = contentEvent.offset.y * scale
                ).rotateBy(rotation)

                uiState.value = uiState.value.copy(
                    drawingObjects = uiState.value.drawingObjects.toMutableList()
                        .apply {
                            val index = uiState.value.drawingObjects.indexOf(selectedObject)
                            val offset = selectedObject.offset.plus(transformedOffset)
                            set(
                                index,
                                selectedObject.copy(
                                    rotation = rotation,
                                    scale = scale,
                                    offset = offset
                                )
                            )
                        }.toList()
                )
            }
            is EditorEvent.BrushEvent -> {
                when (contentEvent.event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        val path = Path().apply {
                            moveTo(contentEvent.event.x, contentEvent.event.y)
                        }
                        uiState.value = uiState.value.copy(
                            currentDrawingPath = EditorObject.BrushPath(
                                mutableStateOf(path),
                                uiState.value.brushConfiguration
                            )
                        )
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val brushPath = (uiState.value.currentDrawingPath as EditorObject.BrushPath)
                        val updatedPath = brushPath.path.value.apply {
                                lineTo(contentEvent.event.x, contentEvent.event.y)
                            }
                        val config = brushPath.brushConfiguration
                        uiState.value = uiState.value.copy(
                            currentDrawingPath = EditorObject.BrushPath(
                                mutableStateOf(updatedPath),
                                config
                            )
                        )
                    }
                    MotionEvent.ACTION_UP -> {
                        uiState.value.currentDrawingPath?.let { path ->
                            uiState.value = uiState.value.copy(
                                drawingObjects = uiState.value.drawingObjects.toMutableList().apply {
                                    add(path)
                                }.toList(),
                                currentDrawingPath = null
                            )
                        }
                    }
                }
            }
            is EditorEvent.UpdateToolColor -> {
                uiState.value = uiState.value.copy(
                    brushConfiguration = uiState.value.brushConfiguration.copy(
                        color = contentEvent.color
                    )
                )
            }
            is EditorEvent.UpdateToolThickness -> {
                uiState.value = uiState.value.copy(
                    brushConfiguration = uiState.value.brushConfiguration.copy(
                        thickness = contentEvent.thickness
                    )
                )
            }
            EditorEvent.CloseEditor -> {
                // Close stories editor
            }
            EditorEvent.Undo -> {
                uiState.value = uiState.value.copy(
                    drawingObjects = uiState.value.drawingObjects.toMutableList().apply {
                        removeLast()
                    }.toList()
                )
            }
            is EditorEvent.AddText -> {
                val text = EditorObject.Text(
                    textId = UUID.randomUUID().toString(),
                    offset = Offset(
                        (contentEvent.x + (contentEvent.width / 2)),
                        (contentEvent.y + (contentEvent.height / 2))
                    ),
                    text = contentEvent.text,
                    color = contentEvent.color ?: Color.Unspecified
                )
                uiState.value = uiState.value.copy(
                    drawingObjects = uiState.value.drawingObjects.toMutableList().apply {
                        add(text)
                    }.toList(),
                    selectedTool = null
                )
            }
            EditorEvent.UnselectTool -> {
                uiState.value = uiState.value.copy(
                    selectedTool = null
                )
            }
        }
    }

    private fun Offset.rotateBy(angle: Float): Offset {
        val angleInRadians = angle * PI / 180
        return Offset(
            (x * cos(angleInRadians) - y * sin(angleInRadians)).toFloat(),
            (x * sin(angleInRadians) + y * cos(angleInRadians)).toFloat()
        )
    }
}