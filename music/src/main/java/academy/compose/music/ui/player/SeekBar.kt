package academy.compose.music.ui.player

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_SEEK_BAR
import academy.compose.music.model.NowPlaying
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PlayerSeekBar(
    modifier: Modifier = Modifier,
    nowPlaying: NowPlaying,
    canSeekTrack: Boolean,
    onSeekChanged: ((value: Float) -> Unit)? = null
) {
    val currentPosition = nowPlaying.position.toFloat() / nowPlaying.track.length.toFloat()
    if (canSeekTrack) {
        Slider(
            modifier = modifier.testTag(TAG_SEEK_BAR),
            enabled = canSeekTrack,
            value = currentPosition,
            onValueChange = {
                onSeekChanged?.invoke(nowPlaying.track.length * it)
            }
        )
    } else {
        LinearProgressIndicator(
            modifier = modifier.testTag(TAG_SEEK_BAR),
            progress = currentPosition
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PlayerSeekBar_CanSeek() {
    MaterialTheme {
        PlayerSeekBar(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            canSeekTrack = true,
            onSeekChanged = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_PlayerSeekBar_CannotSeek() {
    MaterialTheme {
        PlayerSeekBar(
            modifier = Modifier.fillMaxWidth(),
            nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack()),
            canSeekTrack = false,
            onSeekChanged = { }
        )
    }
}