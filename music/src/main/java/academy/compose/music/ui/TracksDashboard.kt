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

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_DASHBOARD
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACKS_DASHBOARD
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.model.Track
import academy.compose.music.ui.featured.FeaturedTracks
import academy.compose.music.ui.new_tracks.NewTracks
import academy.compose.music.ui.recent.RecentTracks
import academy.compose.music.ui.search.SearchResults
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalSnapperApi
@Composable
fun TracksDashboard(
    modifier: Modifier = Modifier,
    state: MusicDashboardState,
    onTrackClicked: (track: Track) -> Unit
) {
    if (state.searchTerm.isNullOrEmpty()) {
        LazyColumn(
            modifier = modifier.testTag(TAG_TRACKS_DASHBOARD),
            contentPadding = PaddingValues(top = 12.dp, bottom = 8.dp)
        ) {
            FeaturedTracks(
                tracks = state.featuredTracks(),
                onTrackClicked = onTrackClicked
            )
            NewTracks(
                tracks = state.newTracks(),
                onTrackClicked = onTrackClicked
            )
            if (!state.recentTracks().isNullOrEmpty()) {
                RecentTracks(
                    tracks = state.recentTracks(),
                    onTrackClicked = onTrackClicked
                )
            }
        }
    } else {
        SearchResults(
            modifier = modifier
                .fillMaxWidth()
                .testTag(TAG_SEARCH_RESULTS),
            results = state.searchResults,
            onTrackClicked = onTrackClicked
        )
    }
}

@ExperimentalSnapperApi
@Preview(showBackground = true)
@Composable
fun TracksDashboard_Preview() {
    MaterialTheme {
        TracksDashboard(
            modifier = Modifier.fillMaxWidth(),
            state = MusicDashboardState(
                tracks = ContentFactory.makeContentList()
            ),
            onTrackClicked = { }
        )
    }
}