package academy.compose.music.ui.featured

import academy.compose.music.ContentFactory
import academy.compose.music.ui.track.CoverArt
import academy.compose.music.model.Track
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FeaturedTrack(
    modifier: Modifier = Modifier,
    track: Track
) {
    Surface(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .semantics(mergeDescendants = true) { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoverArt(
                modifier = Modifier.size(50.dp),
                track = track
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.padding(
                    end = 8.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = track.title,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.caption.fontSize
            )
        }
    }
}

@Preview
@Composable
fun Preview_FeaturedTrack() {
    MaterialTheme {
        FeaturedTrack(
            modifier = Modifier.fillMaxWidth(),
            track = ContentFactory.makeTrack()
        )
    }
}