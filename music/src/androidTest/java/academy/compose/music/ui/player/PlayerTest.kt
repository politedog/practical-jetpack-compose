package academy.compose.music.ui.player

import academy.compose.music.R
import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.Tags
import academy.compose.music.Tags.TAG_PLAY_PAUSE
import academy.compose.music.Tags.TAG_SEEK_BAR
import academy.compose.music.model.NowPlayingState
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class PlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Track_Title_Displayed() {
        val track = TestDataFactory.makeTrack()
        composeTestRule.setContent {
            Player(
                nowPlaying = TestDataFactory.makeNowPlaying(
                    track = track
                ),
                toggleNowPlayingState = {},
                rewindTrack = {},
                fastForwardTrack = {},
                onSeekChanged = {},
                onClose = {}
            )
        }
        composeTestRule.onNodeWithTag(Tags.TAG_TRACK_TITLE)
            .assertTextEquals(track.title)
            .assertIsDisplayed()
    }

    @Test
    fun Track_Artist_Displayed() {
        val track = TestDataFactory.makeTrack()
        composeTestRule.setContent {
            Player(
                nowPlaying = TestDataFactory.makeNowPlaying(
                    track = track
                ),
                toggleNowPlayingState = {},
                rewindTrack = {},
                fastForwardTrack = {},
                onSeekChanged = {},
                onClose = {}
            )
        }
        composeTestRule.onNodeWithTag(Tags.TAG_TRACK_ARTIST)
            .assertTextEquals(track.artist)
            .assertIsDisplayed()
    }

    @Test
    fun Track_Position_Displayed() {
        val nowPlaying = TestDataFactory.makeNowPlaying(
            track = TestDataFactory.makeTrack(),
            state = NowPlayingState.PLAYING
        )
        composeTestRule.setContent {
            Player(
                nowPlaying = nowPlaying,
                toggleNowPlayingState = {},
                rewindTrack = {},
                fastForwardTrack = {},
                onSeekChanged = {},
                onClose = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_SEEK_BAR)
            .assertRangeInfoEquals(
                ProgressBarRangeInfo(
                    current = nowPlaying.position.toFloat() / nowPlaying.track.length.toFloat(),
                    range = 0f..1f,
                    steps = 0
                )
            )
    }

    @Test
    fun Playing_State_Displayed() {
        val track = TestDataFactory.makeTrack()
        composeTestRule.setContent {
            Player(
                nowPlaying = TestDataFactory.makeNowPlaying(
                    track = track,
                    state = NowPlayingState.PLAYING
                ),
                toggleNowPlayingState = {},
                rewindTrack = {},
                fastForwardTrack = {},
                onSeekChanged = {},
                onClose = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAY_PAUSE)
            .assertContentDescriptionEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_pause)
            )
    }

    @Test
    fun Paused_State_Displayed() {
        composeTestRule.setContent {
            Player(
                nowPlaying = TestDataFactory.makeNowPlaying(
                    track = TestDataFactory.makeTrack(),
                    state = NowPlayingState.PAUSED
                ),
                toggleNowPlayingState = {},
                rewindTrack = {},
                fastForwardTrack = {},
                onSeekChanged = {},
                onClose = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAY_PAUSE)
            .assertContentDescriptionEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_play)
            )
    }
}