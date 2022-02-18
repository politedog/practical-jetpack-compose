package academy.compose.music.ui.new_tracks

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_NEW_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NewTracksRow(
    modifier: Modifier = Modifier,
    tracks: List<Track>?,
    onTrackClicked: (track: Track) -> Unit
) {
    if (!tracks.isNullOrEmpty()) {
        val scrollState = rememberScrollState()
        val coroutineScope = rememberCoroutineScope()
        val hasDragged = remember { mutableStateOf(false) }

        val width = LocalDensity.current.run { 136.dp.toPx() }
        val padding = LocalDensity.current.run { 16.dp.toPx() }

        scrollState.interactionSource.collectIsDraggedAsState().value.also {
            if (!it && hasDragged.value && !scrollState.isScrollInProgress) {
                hasDragged.value = false
                coroutineScope.launch {
                    val pos =
                        if (scrollState.value + (width / 3) - padding >= scrollState.maxValue) {
                            scrollState.maxValue.toFloat()
                        } else {
                            width * (Math.round(scrollState.value / width))
                        }
                    scrollState.animateScrollTo(pos.toInt())
                }
            } else {
                hasDragged.value = true
            }
        }
        Row(
            modifier = modifier
                .horizontalScroll(scrollState)
                .testTag(TAG_NEW_TRACKS)
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            tracks.forEach { track ->
                NewTrack(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onTrackClicked(track)
                        }
                        .testTag(TAG_TRACK + track.id),
                    track = track
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun Preview_NewTracksRow() {
    MaterialTheme {
        NewTracksRow(
            modifier = Modifier.fillMaxWidth(),
            tracks = ContentFactory.makeContentList(),
            onTrackClicked = { }
        )
    }
}