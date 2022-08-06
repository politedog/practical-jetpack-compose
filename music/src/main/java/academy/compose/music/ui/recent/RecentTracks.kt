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
package academy.compose.music.ui.recent

import academy.compose.music.R
import academy.compose.music.Tags
import academy.compose.music.model.Track
import academy.compose.music.ui.track.Track
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun LazyListScope.RecentTracks(
    tracks: List<Track>,
    onTrackClicked: (track: Track) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
            text = stringResource(id = R.string.heading_recently_played),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
    items(tracks, key = { it.id }) {
        val playTrackDescription = stringResource(
            id = R.string.cd_play_track,
            it.title, it.artist
        )
        Track(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClickLabel = playTrackDescription) { onTrackClicked(it) }
                .testTag(Tags.TAG_TRACK + it.id),
            track = it
        )
    }
}