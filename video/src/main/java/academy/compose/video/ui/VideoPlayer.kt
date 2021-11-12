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

package academy.compose.video.ui

import academy.compose.video.R
import academy.compose.video.Tags
import academy.compose.video.model.PlayerStatus
import academy.compose.video.model.VideoEvent
import academy.compose.video.model.VideoState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    videoState: VideoState,
    handleEvent: (event: VideoEvent) -> Unit
) {
    val context = LocalContext.current
    val exoPlayer = remember {
        val media: MediaItem =
            MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        SimpleExoPlayer.Builder(context).build().apply {
            setMediaItem(media)
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    super.onPlaybackStateChanged(state)
                    if (state == Player.STATE_READY) {
                        handleEvent(VideoEvent.VideoLoaded)
                    } else if (state == Player.EVENT_PLAYER_ERROR) {
                        handleEvent(VideoEvent.VideoError)
                    }
                }
            })
        }
    }
    Box(
        modifier = modifier.background(Color.Black)
    ) {
        var controlsVisible by remember {
            mutableStateOf(
                true
            )
        }

        val controlsClickLabel = if (controlsVisible) {
            R.string.label_hide_controls
        } else {
            R.string.label_display_controls
        }

        Playback(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClickLabel = stringResource(id = controlsClickLabel)
                ) {
                    controlsVisible = !controlsVisible
                }
                .testTag(Tags.TAG_VIDEO_PLAYER),
            lifecycleOwner = lifecycleOwner,
            state = videoState.playerStatus,
            context = context,
            exoPlayer = exoPlayer
        )

        val alphaAnimation by animateFloatAsState(
            targetValue = if (controlsVisible) 0.7f else 0f,
            animationSpec = if (controlsVisible) {
                tween(delayMillis = 0)
            } else tween(delayMillis = 750)
        )

        Controls(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .alpha(alphaAnimation),
            playerStatus = videoState.playerStatus,
            togglePlayingState = {
                handleEvent(VideoEvent.ToggleStatus)
                if (videoState.playerStatus != PlayerStatus.PLAYING) {
                    controlsVisible = false
                }
            }
        )
    }
}
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun Preview_VideoPlayer() {
    MaterialTheme {
        VideoPlayer(
            modifier = Modifier.fillMaxSize(),
            videoState = VideoState(),
            handleEvent = { }
        )
    }
}
