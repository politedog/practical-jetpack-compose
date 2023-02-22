package academy.compose.editor.ui

import academy.compose.editor.model.EditorObject
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp

@Composable
fun StickerArea(
    modifier: Modifier = Modifier,
    drawingObjects: List<EditorObject>,
    onTransform: (id: String, offset: Offset, rotation: Float, scale: Float) -> Unit
) {
    Box(modifier = modifier) {
        drawingObjects.forEach { text ->
            if (text is EditorObject.Text) {
                Text(
                    modifier = Modifier
                        .graphicsLayer {
                            rotationZ = text.rotation
                            scaleX = text.scale
                            scaleY = text.scale
                            translationX = text.offset.x
                            translationY = text.offset.y
                        }
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, gestureZoom, gestureRotate ->
                                onTransform(
                                    text.id!!,
                                    Offset(pan.x, pan.y),
                                    gestureRotate,
                                    gestureZoom
                                )
                            }
                        },
                    text = text.text,
                    color = text.color,
                    fontSize = 100.sp
                )
            }
        }
    }
}