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

import academy.compose.video.Tags.TAG_CONTROLS
import academy.compose.video.Tags.TAG_VIDEO_PLAYER
import academy.compose.video.model.VideoState
import academy.compose.video.ui.VideoPlayer
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@ExperimentalAnimationApi
@RunWith(AndroidJUnit4::class)
class VideoPlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalComposeUiApi
    @Test
    fun Player_Controls_Displayed_By_Default() {
        composeTestRule.setContent {
            VideoPlayer(
                videoState = VideoState(),
                handleEvent = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_CONTROLS
        ).assertIsDisplayed()
    }

    @ExperimentalComposeUiApi
    @Test
    fun Video_Player_Is_Displayed() {
        composeTestRule.setContent {
            VideoPlayer(
                videoState = VideoState(),
                handleEvent = { }
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_VIDEO_PLAYER
        ).assertIsDisplayed()
    }
}