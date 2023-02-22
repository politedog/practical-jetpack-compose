package academy.compose.editor.tool

import academy.compose.editor.model.BrushConfiguration
import academy.compose.editor.ui.ColorPicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BrushSettings(
    modifier: Modifier = Modifier,
    brushConfiguration: BrushConfiguration,
    onColorSelected: (color: Color) -> Unit,
    onThicknessChanged: (thickness: Float) -> Unit
) {
    Box(modifier = modifier) {
        Surface(
            color = Color.Black.copy(alpha = 0.4f),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            ColorPicker(
                onColorSelected = onColorSelected,
                selectedColor = brushConfiguration.color,
                onClose = {}
            )
        }
        Slider(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .rotate(270f)
                .offset(x = 0.dp, y = (-165).dp),
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
    }
}