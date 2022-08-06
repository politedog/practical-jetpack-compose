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
import androidx.compose.foundation.layout.fillMaxSize
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

@Preview(showBackground = true)
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

@Preview(showBackground = true)
@Composable
fun Preview_SearchResults_empty() {
    MaterialTheme {
        SearchResults(
            modifier = Modifier.fillMaxSize(),
            results = emptyList(),
            onTrackClicked = { }
        )
    }
}