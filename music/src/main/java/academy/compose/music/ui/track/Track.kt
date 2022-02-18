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
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 8.dp
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