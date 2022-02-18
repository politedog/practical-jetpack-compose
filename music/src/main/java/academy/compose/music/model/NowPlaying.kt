package academy.compose.music.model

data class NowPlaying(
    val track: Track,
    val position: Long,
    val state: NowPlayingState
)