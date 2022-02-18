package academy.compose.practical.music_catalog

import academy.compose.music.model.NowPlaying
import academy.compose.music.model.NowPlayingState
import academy.compose.music.model.Track
import androidx.compose.ui.graphics.Color
import java.util.*
import kotlin.random.Random.Default.nextLong

object TestDataFactory {

    fun randomString() = UUID.randomUUID().toString()

    fun makeNowPlaying(
        track: Track = makeTrack(),
        state: NowPlayingState = NowPlayingState.PAUSED,
        progress: Long = track.length / 3
    ): NowPlaying {
        return NowPlaying(
            track,
            progress,
            state
        )
    }

    fun makeTrack(
        isFeatured: Boolean = Math.random() < 0.5,
        isNew: Boolean = Math.random() < 0.5
    ): Track {
        return Track(
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            UUID.randomUUID().toString(),
            Color.Red,
            length = nextLong(500),
            isFeatured = isFeatured,
            isNew = isNew
        )
    }

    fun makeContentList(count: Int): List<Track> {
        return (0..count).map { makeTrack() }
    }

    fun makeMixedContentList(): List<Track> {
        return listOf(
            makeTrack(false, false),
            makeTrack(true, true),
            makeTrack(true, false),
            makeTrack(false, true),
            makeTrack(true, true),
            makeTrack(false, false),
            makeTrack(false, true),
            makeTrack(true, false)
        )
    }

}