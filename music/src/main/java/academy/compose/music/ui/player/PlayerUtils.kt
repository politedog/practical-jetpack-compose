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
package academy.compose.music.ui.player

import academy.compose.music.R
import academy.compose.music.model.NowPlayingState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

fun iconForPlayingState(state: NowPlayingState): ImageVector {
    return if (state == NowPlayingState.PLAYING) {
        Icons.Default.Pause
    } else Icons.Default.PlayArrow
}

fun descriptionForNowPlayingState(state: NowPlayingState): Int {
    return if (state == NowPlayingState.PLAYING) {
        R.string.cd_pause
    } else R.string.cd_play
}