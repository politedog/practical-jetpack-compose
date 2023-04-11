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
package academy.compose.editor.model

import android.view.MotionEvent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

sealed class EditorEvent {

    class ToolSelected(val tool: EditorTool) : EditorEvent()

    object UnselectTool : EditorEvent()

    object Undo : EditorEvent()

    object CloseEditor : EditorEvent()

    class TransformObject(
        val id: String,
        val offset: Offset,
        val rotation: Float = 1f,
        val scale: Float = 1f
    ) : EditorEvent()

    class BrushEvent(
        val event: MotionEvent
    ) : EditorEvent()

    class AddText(
        val x: Float,
        val y: Float,
        val width: Int,
        val height: Int,
        val text: String,
        val color: Color? = null
    ) : EditorEvent()

    class UpdateToolColor(
        val color: Color
    ): EditorEvent()

    class UpdateToolThickness(
        val thickness: Float
    ): EditorEvent()
}