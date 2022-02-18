package academy.compose.music.ui.featured

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeaturedTracksGrid(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .testTag(TAG_FEATURED_TRACKS)
        ) {
            tracks.chunked(2).forEach { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    for ((index, item) in row.withIndex()) {
                        FeaturedTrack(
                            modifier = Modifier
                                .fillMaxWidth(1f / (2 - index))
                                .clickable {
                                    onTrackClicked(item)
                                }
                                .testTag(TAG_TRACK + item.id),
                            track = item
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_FeaturedTracksGrid() {
    MaterialTheme {
        FeaturedTracksGrid(
            modifier = Modifier.fillMaxWidth(),
            tracks = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}