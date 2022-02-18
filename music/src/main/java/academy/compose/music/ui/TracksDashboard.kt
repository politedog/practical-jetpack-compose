package academy.compose.music.ui

import academy.compose.music.R
import academy.compose.music.Tags.TAG_DASHBOARD
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.ui.featured.FeaturedTracksGrid
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.model.Track
import academy.compose.music.ui.new_tracks.NewTracksRow
import academy.compose.music.ui.search.SearchResults
import academy.compose.music.ui.track.Track
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TracksDashboard(
    modifier: Modifier = Modifier,
    state: MusicDashboardState,
    onTrackClicked: (track: Track) -> Unit
) {
    if (state.searchTerm.isNullOrEmpty()) {
        LazyColumn(
            modifier = modifier.testTag(TAG_DASHBOARD),
            contentPadding = PaddingValues(top = 12.dp, bottom = 8.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.heading_featured),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                FeaturedTracksGrid(
                    modifier = Modifier.fillMaxWidth().testTag(TAG_FEATURED_TRACKS),
                    tracks = state.featuredTracks,
                    onTrackClicked = { track ->
                        onTrackClicked(track)
                    }
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(id = R.string.heading_new),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                NewTracksRow(
                    modifier = Modifier.fillMaxWidth(),
                    tracks = state.newTracks,
                    onTrackClicked = { track ->
                        onTrackClicked(track)
                    }
                )
            }
            if (!state.recentTracks.isNullOrEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                        text = stringResource(id = R.string.heading_recently_played),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                items(state.recentTracks, key = { it.id }) {
                    Track(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { onTrackClicked(it) }
                            .testTag(TAG_TRACK + it.id),
                        track = it
                    )
                }
            }
        }
    } else {
        SearchResults(
            modifier = modifier.fillMaxWidth().testTag(TAG_SEARCH_RESULTS),
            results = state.searchResults,
            onTrackClicked = { track ->
                onTrackClicked(track)
            }
        )
    }
}