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

@Preview
@Composable
fun Preview_NewTrack() {
    MaterialTheme {
        NewTrack(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}