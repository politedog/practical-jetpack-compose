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

import academy.compose.editor.R
import academy.compose.editor.ui.ColorPicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatColorText
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextSettings(
    modifier: Modifier = Modifier,
    addText: (text: String, color: Color) -> Unit
) {
    Box(
        modifier = modifier.background(Color.Black.copy(alpha = 0.8f))
    ) {
        var content by remember {
            mutableStateOf("Hello there")
        }
        var color by remember {
            mutableStateOf(Color.White)
        }
        val focusRequester = FocusRequester()
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
        var showingColors by remember { mutableStateOf(false) }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (!showingColors) {
                IconButton(onClick = { showingColors = !showingColors }) {
                    Icon(
                        modifier = Modifier
                            .padding(20.dp),
                        imageVector = Icons.Default.FormatColorText,
                        contentDescription = stringResource(id = R.string.cd_select_color),
                        tint = Color.White
                    )
                }
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
                    }
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
            textStyle = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center),
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

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Preview_TextSettings() {
    TextSettings(
        modifier = Modifier.fillMaxSize(),
        addText = { a, b ->

        }
    )
}