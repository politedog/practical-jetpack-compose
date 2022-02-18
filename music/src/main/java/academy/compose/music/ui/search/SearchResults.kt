package academy.compose.music.ui.search

import academy.compose.music.ContentFactory
import academy.compose.music.R
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import academy.compose.music.ui.track.Track
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchResults(
    modifier: Modifier = Modifier,
    results: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (results.isNullOrEmpty()) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(id = R.string.message_search_results))
        }
    } else {
        LazyColumn(
            modifier = modifier.testTag(TAG_SEARCH_RESULTS),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(results) {
                val selectTrackAction = stringResource(id = R.string.action_select_track, it.title)
                Track(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClickLabel = selectTrackAction) {
                            onTrackClicked(it)
                        }
                        .testTag(TAG_TRACK + it.id),
                    track = it
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_SearchResults() {
    MaterialTheme {
        SearchResults(
            modifier = Modifier.fillMaxWidth(),
            results = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}