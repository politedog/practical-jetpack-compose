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

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@ExperimentalFoundationApi
@Composable
fun FeaturedTracksGrid(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        val itemSize = (LocalConfiguration.current.screenWidthDp.dp / 2)
        FlowRow(
            modifier = modifier
                .testTag(TAG_FEATURED_TRACKS),
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
        ) {
            tracks.forEach { track ->
                FeaturedTrack(
                    modifier = Modifier
                        .width(itemSize)
                        .clickable {
                            onTrackClicked(track)
                        }
                        .testTag(TAG_TRACK + track.id),
                    track = track
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
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