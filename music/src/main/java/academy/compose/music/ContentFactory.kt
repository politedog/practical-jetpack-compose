package academy.compose.music

import academy.compose.music.model.NowPlaying
import academy.compose.music.model.NowPlayingState
import academy.compose.music.model.Track
import androidx.compose.ui.graphics.Color

object ContentFactory {

    fun makeNowPlaying(track: Track): NowPlaying {
        return NowPlaying(
            track,
            0,
            NowPlayingState.PAUSED
        )
    }

    fun makeTrack(): Track {
        return Track(
            "1",
            "Dude Ranch",
            "Blink 182",
            Color.Red,
            length = 340,
            isFeatured = true,
            isNew = false
        )
    }

    fun makeContentList(): List<Track> {
        return listOf(
            Track(
                "1",
                "Dude Ranch",
                "Blink 182",
                Color.Red,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "2",
                "Liquor",
                "Lydia",
                Color.Magenta,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "3",
                "Blurryface",
                "Twenty One Pilots",
                Color.Cyan,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "4",
                "American Candy",
                "The Maine",
                Color.Blue,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "5",
                "Umbra",
                "Grayscale",
                Color.Gray,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "6",
                "The Myth of The Happily Ever After",
                "Biffy Clyro",
                Color.Blue,
                length = 340,
                isFeatured = true,
                isNew = false
            ),
            Track(
                "7",
                "Along the Shadow",
                "Saosin",
                Color.Red,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "8",
                "Still Sucks",
                "Limp Bizkit",
                Color.Magenta,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "9",
                "Survivors Guilt",
                "Kenny Hoopla",
                Color.Cyan,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "10",
                "Suckapunch",
                "You Me At Six",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "11",
                "Tell Me About Tomorrow",
                "jxdn",
                Color.Gray,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "12",
                "Greatest Hits",
                "Waterparks",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = true
            ),
            Track(
                "13",
                "Model Citizen",
                "Meet Me @ The Altar",
                Color.Red,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "14",
                "Futures",
                "Jimmy Eat World",
                Color.Magenta,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "15",
                "Don Broco",
                "Amazing Things",
                Color.Cyan,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "16",
                "Internal Atomics",
                "Stray From The Path",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "17",
                "Bloodlust",
                "nothing,nowhere.",
                Color.Gray,
                length = 340,
                isFeatured = false,
                isNew = false
            ),
            Track(
                "18",
                "Mother Nature",
                "The Dangerous Summer",
                Color.Blue,
                length = 340,
                isFeatured = false,
                isNew = false
            )
        )
    }

}