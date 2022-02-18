package academy.compose.music.ui.new_tracks

import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_NEW_TRACKS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.model.Track
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock

class NewTracksRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun New_Tracks_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            NewTracksRow(tracks = emptyList(), onTrackClicked = {})
        }

        composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
            .assertDoesNotExist()
    }

    @Test
    fun New_Tracks_Not_Displayed_When_Null() {
        composeTestRule.setContent {
            NewTracksRow(tracks = null, onTrackClicked = {})
        }

        composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
            .assertDoesNotExist()
    }

    @Test
    fun New_Tracks_Displayed() {
        val tracks = ContentFactory.makeContentList()
        composeTestRule.setContent {
            NewTracksRow(tracks = tracks, onTrackClicked = {})
        }

        tracks.forEachIndexed { index, track ->
            composeTestRule.onNodeWithTag(TAG_NEW_TRACKS)
                .onChildAt(index)
                .performScrollTo()
                .assert(hasTestTag(TAG_TRACK + track.id))
                .assertIsDisplayed()
        }
    }

    @Test
    fun Selecting_New_Track_Triggers_Listener() {
        val trackListener: (track: Track) -> Unit = mock()
        val tracks = ContentFactory.makeContentList()
        composeTestRule.setContent {
            NewTracksRow(tracks = tracks, onTrackClicked = trackListener)
        }

        composeTestRule.onNodeWithTag(TAG_TRACK + tracks[2].id)
            .performClick()

        verify(trackListener).invoke(tracks[2])
    }
}