package academy.compose.editor

import academy.compose.editor.emoji.EmojiSheet
import academy.compose.editor.model.EditorEvent
import academy.compose.editor.model.EditorObject
import academy.compose.editor.model.EditorState
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
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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
            LaunchedEffect(Unit, block = {
                centerCanvas = Offset(
                    (constraints.maxWidth / 2).toFloat(),
                    (constraints.maxHeight / 2).toFloat()
                )
            })
            DrawingArea(
                modifier = Modifier.fillMaxSize(),
                state, handleEvent
            )
            StickerArea(
                modifier = Modifier.fillMaxSize(),
                drawingObjects = state.drawingObjects.filter {
                    it !is EditorObject.BrushPath
                },
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
                showEmojiPicker = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                handleEvent = handleEvent
            )
            ToolConfig(
                state = state,
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
