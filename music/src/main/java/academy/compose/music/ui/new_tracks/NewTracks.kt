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
import academy.compose.music.model.Track
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
fun LazyListScope.NewTracks(
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    item {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = academy.compose.music.R.string.heading_new),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        NewTracksRow(
            modifier = Modifier.fillMaxWidth(),
            tracks = tracks,
            onTrackClicked = { track ->
                onTrackClicked(track)
            }
        )
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_NewTracks() {
    MaterialTheme {
        LazyColumn(content = {
            NewTracks(tracks = ContentFactory.makeContentList(), onTrackClicked = {})
        })
    }
}