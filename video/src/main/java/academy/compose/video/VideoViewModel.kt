/*
 * Copyright 2021 Compose Academy
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

package academy.compose.video

import academy.compose.video.model.PlayerStatus
import academy.compose.video.model.VideoEvent
import academy.compose.video.model.VideoState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class VideoViewModel : ViewModel() {

    val uiState = MutableStateFlow(VideoState())

    fun handleEvent(videoEvent: VideoEvent) {
        when (videoEvent) {
            VideoEvent.VideoLoaded -> {
                uiState.value = uiState.value.copy(
                    playerStatus = PlayerStatus.IDLE
                )
            }
            VideoEvent.VideoError -> {
                uiState.value = uiState.value.copy(
                    playerStatus = PlayerStatus.ERROR
                )
            }
            VideoEvent.ToggleStatus -> togglePlayerStatus()
        }
    }

    private fun togglePlayerStatus() {
        val playerStatus = uiState.value.playerStatus
        val newPlayerStatus = if (playerStatus != PlayerStatus.PLAYING) {
            PlayerStatus.PLAYING
        } else {
            PlayerStatus.PAUSED
        }
        uiState.value = uiState.value.copy(
            playerStatus = newPlayerStatus
        )
    }
}