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
package academy.compose.music.ui.new_tracks

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_NEW_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

@ExperimentalFoundationApi
class NewTracksRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun New_Tracks_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            NewTracksRow(tracks = emptyList(), onTrackClicked = {})
        }

        composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
            .assertDoesNotExist()
    }

    @Test
    fun New_Tracks_Not_Displayed_When_Null() {
        composeTestRule.setContent {
            NewTracksRow(tracks = null, onTrackClicked = {})
        }

        composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
            .assertDoesNotExist()
    }

    @Test
    fun New_Tracks_Displayed() {
        val tracks = ContentFactory.makeContentList()
        composeTestRule.setContent {
            NewTracksRow(tracks = tracks, onTrackClicked = {})
        }

        tracks.forEach { track ->
            composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
                .performScrollToNode(hasTestTag(TAG_TRACK + track.id))
                .assertIsDisplayed()
        }
    }

    @Test
    fun Selecting_New_Track_Triggers_Listener() {
        val trackListener: (track: Track) -> Unit = mock()
        val tracks = ContentFactory.makeContentList()
        composeTestRule.setContent {
            NewTracksRow(tracks = tracks, onTrackClicked = trackListener)
        }

        composeTestRule.onNodeWithTag(TAG_TRACK + tracks[2].id)
            .performClick()

        verify(trackListener).invoke(tracks[2])
    }
}