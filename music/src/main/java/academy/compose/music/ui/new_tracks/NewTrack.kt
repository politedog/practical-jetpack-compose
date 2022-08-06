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
import academy.compose.music.ui.track.CoverArt
import academy.compose.music.model.Track
import academy.compose.music.ui.featured.FeaturedTracksGrid
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewTrack(
    modifier: Modifier = Modifier,
    track: Track
) {
    Column(
        modifier = modifier.padding(8.dp)
            .widthIn(max = 120.dp)
            .semantics(mergeDescendants = true) { },
    ) {
        CoverArt(
            modifier = Modifier.fillMaxWidth().height(120.dp),
            track = track
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = track.title,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp
        )
        Text(
            text = track.artist,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_NewTrack() {
    MaterialTheme {
        NewTrack(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}