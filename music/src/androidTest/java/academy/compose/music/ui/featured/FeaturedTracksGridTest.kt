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
package academy.compose.music.ui.featured

import academy.compose.music.Tags
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.music.TestDataFactory
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class FeaturedTracksGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Featured_Tracks_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = emptyList(), onTrackClicked = {})
        }
        composeTestRule.onNodeWithTag(
            TAG_FEATURED_TRACKS
        ).assertDoesNotExist()
    }

    @Test
    fun Featured_Tracks_Not_Displayed_When_Null() {
        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = null, onTrackClicked = {})
        }
        composeTestRule.onNodeWithTag(
            TAG_FEATURED_TRACKS
        ).assertDoesNotExist()
    }

    @Test
    fun Featured_Tracks_Displayed() {
        val tracks = TestDataFactory.makeContentList(5)

        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = tracks, onTrackClicked = {})
        }

        tracks.forEachIndexed { rowIndex, track ->
            composeTestRule.onNodeWithTag(TAG_FEATURED_TRACKS)
                .onChildAt(rowIndex)
                .assert(hasTestTag(Tags.TAG_TRACK + track.id))
        }
    }
}