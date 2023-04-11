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

import academy.compose.editor.model.EditorTool
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Tool(
    modifier: Modifier = Modifier,
    tool: EditorTool,
    onSelected: (tool: EditorTool) -> Unit
) {
    IconButton(onClick = { onSelected(tool) }) {
        Icon(
            modifier = modifier,
            imageVector = tool.icon,
            contentDescription = stringResource(id = tool.description),
            tint = Color.White
        )
    }
}

@Preview(showBackground = false)
@Composable
fun Preview_Tool() {
    Tool(
        tool = EditorTool.Emoji,
        onSelected = { }
    )
}