package academy.compose.music.ui.track

import academy.compose.music.ContentFactory
import academy.compose.music.model.Track
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CoverArt(
    modifier: Modifier = Modifier,
    track: Track
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.primaryVariant,
                        track.cover
                    )
                ),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

@Preview
@Composable
fun Preview_CoverArt() {
    MaterialTheme {
        CoverArt(
            modifier = Modifier.size(50.dp),
            track = ContentFactory.makeTrack()
        )
    }
}