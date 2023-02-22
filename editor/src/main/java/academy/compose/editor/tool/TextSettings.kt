package academy.compose.editor.tool

import academy.compose.editor.model.EditorObject
import academy.compose.editor.R
import academy.compose.editor.ui.ColorPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSettings(
    modifier: Modifier = Modifier,
    text: EditorObject.Text? = null,
    addText: (text: String, color: Color) -> Unit
) {
    Box(
        modifier = modifier.background(Color.Black.copy(alpha = 0.8f))
    ) {
        var content by remember {
            mutableStateOf("")
        }
        var color by remember {
            mutableStateOf(text?.color ?: Color.White)
        }
        LaunchedEffect(text) {
            content = text?.text ?: ""
        }
        var showingColors by remember { mutableStateOf(false) }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (!showingColors) {
                Icon(
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            showingColors = !showingColors
                        },
                    imageVector = Icons.Default.FormatColorText,
                    contentDescription = "Color",
                    tint = Color.White
                )
            }
            if (showingColors) {
                ColorPicker(
                    selectedColor = color,
                    onColorSelected = {
                        color = it
                        showingColors = !showingColors
                    },
                    onClose = {
                        showingColors = !showingColors
                    },
                    isVertical = true
                )
            } else {
                Spacer(Modifier.weight(1f))
                TextButton(
                    modifier = Modifier.padding(end = 12.dp, top = 14.dp),
                    onClick = {
                        if (content.isNotEmpty()) {
                            addText(content, color)
                        }
                    }) {
                    Text(
                        text = stringResource(id = R.string.label_done),
                        color = Color.White
                    )
                }
            }
        }

        val focusRequester = FocusRequester()
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        TextField(
            modifier = Modifier
                .widthIn(min = 28.dp)
                .padding(16.dp)
                .focusRequester(focusRequester)
                .align(Alignment.Center),
            value = content,
            onValueChange = {
                content = it
            },
            textStyle = TextStyle(fontSize = 24.sp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Unspecified,
                textColor = color,
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                cursorColor = Color.White
            )
        )
    }
}