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
package academy.compose.music.ui

import academy.compose.music.R
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.music.Tags.TAG_NEW_TRACKS
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.Tags.TAG_TRACKS_DASHBOARD
import academy.compose.music.TestDataFactory
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.model.Track
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalFoundationApi
class TracksDashboardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Content_Area_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(),
                onTrackClicked = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_TRACKS_DASHBOARD)
            .assertIsDisplayed()
    }

    @Test
    fun Recently_Played_Tracks_Header_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    tracks = TestDataFactory.makeMixedContentList()
                ),
                onTrackClicked = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_TRACKS_DASHBOARD)
            .onChildAt(4)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.heading_recently_played)
            )
    }

    @Test
    fun Recently_Played_Tracks_Displayed() {
        val tracks = TestDataFactory.makeMixedContentList()
        val state = MusicDashboardState(
            tracks = tracks
        )
        val recentTracks = state.recentTracks()
        composeTestRule.setContent {
            TracksDashboard(
                state = state,
                onTrackClicked = {}
            )
        }

        recentTracks.forEachIndexed { index, track ->
            composeTestRule
                .onNodeWithTag(TAG_TRACKS_DASHBOARD)
                .onChildAt(5 + index)
                .performScrollTo()
                .assert(hasTestTag(TAG_TRACK + track.id))
        }
    }

    @Test
    fun Clicking_Recently_Played_Track_Plays_Selected_Track() {
        val content = TestDataFactory.makeMixedContentList()
        val state = MusicDashboardState(
            tracks = content
        )
        val trackToSelect = state.recentTracks().first()
        val trackListener: (track: Track) -> Unit = mock()
        composeTestRule.setContent {
            TracksDashboard(
                state = state,
                onTrackClicked = trackListener
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_TRACKS_DASHBOARD)
            .onChildAt(5)
            .performClick()

        verify(trackListener).invoke(trackToSelect)
    }

    @Test
    fun Featured_Tracks_Header_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    tracks = TestDataFactory.makeContentList(5)
                ),
                onTrackClicked = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_TRACKS_DASHBOARD)
            .onChildAt(0)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.heading_featured)
            )
    }

    @Test
    fun Featured_Tracks_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    tracks = TestDataFactory.makeMixedContentList()
                ),
                onTrackClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_FEATURED_TRACKS)
            .assertIsDisplayed()
    }

    @Test
    fun Clicking_Featured_Track_Plays_Selected_Track() {
        val content = TestDataFactory.makeMixedContentList()
        val state = MusicDashboardState(
            tracks = content
        )
        val trackToSelect = state.featuredTracks().first()
        val trackListener: (track: Track) -> Unit = mock()
        composeTestRule.setContent {
            TracksDashboard(
                state = state,
                onTrackClicked = trackListener
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_FEATURED_TRACKS)
            .onChildAt(0)
            .performClick()

        verify(trackListener).invoke(trackToSelect)
    }

    @Test
    fun New_Tracks_Header_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    tracks = TestDataFactory.makeContentList(5)
                ),
                onTrackClicked = {}
            )
        }

        composeTestRule.onNodeWithTag(TAG_TRACKS_DASHBOARD)
            .onChildAt(2)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.heading_new)
            )
    }

    @Test
    fun New_Tracks_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    tracks = TestDataFactory.makeMixedContentList()
                ),
                onTrackClicked = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_NEW_TRACKS)
            .assertIsDisplayed()
    }

    @Test
    fun Clicking_New_Track_Plays_Selected_Track() {
        val content = TestDataFactory.makeMixedContentList()
        val state = MusicDashboardState(
            tracks = content
        )
        val trackToSelect = state.newTracks().first()
        val trackListener: (track: Track) -> Unit = mock()
        composeTestRule.setContent {
            TracksDashboard(
                state = state,
                onTrackClicked = trackListener
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_NEW_TRACKS)
            .onChildAt(0)
            .performClick()

        verify(trackListener).invoke(trackToSelect)
    }

    @Test
    fun Search_Results_Displayed() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    searchTerm = TestDataFactory.randomString()
                ),
                onTrackClicked = { },
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_SEARCH_RESULTS)
            .assertIsDisplayed()
    }

    @Test
    fun Search_Query_Cleared() {
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    searchTerm = null
                ),
                onTrackClicked = { },
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_SEARCH_RESULTS)
            .assertDoesNotExist()
    }

    @Test
    fun Clicking_Search_Result_Plays_Selected_Track() {
        val searchResults = TestDataFactory.makeMixedContentList()
        val trackListener: (track: Track) -> Unit = mock()
        composeTestRule.setContent {
            TracksDashboard(
                state = MusicDashboardState(
                    searchTerm = TestDataFactory.randomString(),
                    searchResults = searchResults
                ),
                onTrackClicked = trackListener,
            )
        }

        composeTestRule
            .onNodeWithTag(TAG_SEARCH_RESULTS)
            .onChildAt(0)
            .performClick()

        verify(trackListener).invoke(searchResults[0])
    }
}