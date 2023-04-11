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

import academy.compose.editor.emoji.EmojiSheet
import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorState
import academy.compose.editor.model.EditorTool
import academy.compose.editor.tool.ToolConfig
import academy.compose.editor.ui.ActionsBar
import academy.compose.editor.ui.DrawingArea
import academy.compose.editor.ui.StickerArea
import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StoryCreator(
    modifier: Modifier = Modifier,
    state: EditorState,
    handleEvent: (event: EditorEvent) -> Unit
) {
    val defaultTextPaint = remember {
        Paint().apply {
            textSize = 80f
        }
    }
    var centerCanvas by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            EmojiSheet(
                modifier = Modifier.fillMaxWidth()
            ) {
                Rect().also { bounds ->
                    defaultTextPaint.getTextBounds(it, 0, it.length, bounds)
                    handleEvent(
                        EditorEvent.AddText(
                            centerCanvas.x,
                            centerCanvas.y,
                            bounds.width(),
                            bounds.height(),
                            it
                        )
                    )
                }
            }
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.dog),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            LaunchedEffect(this.constraints, block = {
                centerCanvas = Offset(
                    (constraints.maxWidth / 2).toFloat(),
                    (constraints.maxHeight / 2).toFloat()
                )
            })
            DrawingArea(
                modifier = Modifier.fillMaxSize(),
                state.selectedTool, state.drawingObjects, state.currentDrawingPath, handleEvent
            )
            StickerArea(
                modifier = Modifier.fillMaxSize(),
                drawingObjects = state.drawingObjects.filterIsInstance(EditorObject.Text::class.java),
                onTransform = { id, offset, rotation, scale ->
                    handleEvent(
                        EditorEvent.TransformObject(
                            id = id,
                            offset = offset,
                            rotation = rotation,
                            scale = scale
                        )
                    )
                }
            )
            ActionsBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                selectedTool = state.selectedTool,
                drawingObjects = state.drawingObjects,
                handleEvent = {
                    if (it is EditorEvent.ToolSelected && it.tool == EditorTool.Emoji) {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    } else handleEvent(it)
                }
            )
            ToolConfig(
                modifier = Modifier.fillMaxSize(),
                selectedTool = state.selectedTool,
                configuration = state.brushConfiguration,
                currentObject = state.currentDrawingPath,
                handleEvent = handleEvent,
                addText = { text, color ->
                    Rect().also { bounds ->
                        defaultTextPaint.getTextBounds(text, 0, text.length, bounds)
                        handleEvent(
                            EditorEvent.AddText(
                                centerCanvas.x,
                                centerCanvas.y,
                                bounds.width(),
                                bounds.height(),
                                text,
                                color
                            )
                        )
                    }
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Preview_StoryCreator() {
    StoryCreator(
        state = EditorState(),
        handleEvent = {}
    )
}