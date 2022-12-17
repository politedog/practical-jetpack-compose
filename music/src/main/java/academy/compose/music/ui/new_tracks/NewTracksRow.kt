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
import academy.compose.music.R
import academy.compose.music.Tags.TAG_NEW_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun NewTracksRow(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        val lazyListState = rememberLazyListState()
        LazyRow(
            modifier = modifier
                .testTag(TAG_NEW_TRACKS),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(tracks) { track ->
                val playTrackDescription = stringResource(
                    id = R.string.cd_play_track,
                    track.title, track.artist
                )
                NewTrack(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClickLabel = playTrackDescription) {
                            onTrackClicked(track)
                        }
                        .testTag(TAG_TRACK + track.id),
                    track = track
                )

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_NewTracksRow() {
    MaterialTheme {
        NewTracksRow(
            modifier = Modifier.fillMaxWidth(),
            tracks = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}