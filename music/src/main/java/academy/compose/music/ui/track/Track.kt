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
package academy.compose.music.ui.track

import academy.compose.music.ContentFactory
import academy.compose.music.model.Track
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Track(
    modifier: Modifier = Modifier,
    track: Track
) {
    Surface(
        modifier = modifier.semantics(mergeDescendants = true) { },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
                .fillMaxWidth()
        ) {
            CoverArt(
                modifier = Modifier.size(40.dp),
                track = track
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = track.title,
                    fontSize = 16.sp
                )
                Text(
                    text = track.artist,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_Track() {
    MaterialTheme {
        Track(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}