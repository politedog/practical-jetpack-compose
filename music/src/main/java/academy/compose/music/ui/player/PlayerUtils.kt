package academy.compose.music.ui.player

import academy.compose.music.R
import academy.compose.music.model.NowPlayingState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

fun iconForPlayingState(state: NowPlayingState): ImageVector {
    return if (state == NowPlayingState.PLAYING) {
        Icons.Default.Pause
    } else Icons.Default.PlayArrow
}

fun descriptionForNowPlayingState(state: NowPlayingState): Int {
    return if (state == NowPlayingState.PLAYING) {
        R.string.cd_pause
    } else R.string.cd_play
}