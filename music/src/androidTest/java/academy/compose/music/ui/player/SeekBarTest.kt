package academy.compose.music.ui.player

import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.Tags.TAG_SEEK_BAR
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertRangeInfoEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.swipeLeft
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

class SeekBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Track_Progress_Displayed_When_Can_Seek() {
        val nowPlaying = TestDataFactory.makeNowPlaying()
        composeTestRule.setContent {
            PlayerSeekBar(
                nowPlaying = nowPlaying,
                canSeekTrack = true
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
    fun Track_Progress_Displayed_When_Cannot_Seek() {
        val nowPlaying = TestDataFactory.makeNowPlaying()
        composeTestRule.setContent {
            PlayerSeekBar(
                nowPlaying = nowPlaying,
                canSeekTrack = false
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
    fun Track_Progress_Updated_When_Can_Seek() {
        val seekListener: (progress: Float) -> Unit = mock()
        composeTestRule.setContent {
            PlayerSeekBar(
                nowPlaying = TestDataFactory.makeNowPlaying(),
                canSeekTrack = true,
                onSeekChanged = seekListener
            )
        }

        composeTestRule.onNodeWithTag(TAG_SEEK_BAR)
            .performGesture {
                swipeLeft()
            }

        verify(seekListener, atLeastOnce()).invoke(any())
    }

    @Test
    fun Track_Progress_Not_Updated_When_Cannot_Seek() {
        val seekListener: (progress: Float) -> Unit = mock()
        composeTestRule.setContent {
            PlayerSeekBar(
                nowPlaying = TestDataFactory.makeNowPlaying(),
                canSeekTrack = false,
                onSeekChanged = seekListener
            )
        }

        composeTestRule.onNodeWithTag(TAG_SEEK_BAR)
            .performGesture {
                swipeLeft()
            }

        verify(seekListener, never()).invoke(any())
    }
}