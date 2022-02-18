package academy.compose.music.ui.featured

import academy.compose.music.Tags
import academy.compose.music.Tags.TAG_FEATURED_TRACKS
import academy.compose.practical.music_catalog.TestDataFactory
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class FeaturedTracksGridTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Featured_Tracks_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = emptyList(), onTrackClicked = {})
        }
        composeTestRule.onNodeWithTag(
            TAG_FEATURED_TRACKS
        ).assertDoesNotExist()
    }

    @Test
    fun Featured_Tracks_Not_Displayed_When_Null() {
        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = null, onTrackClicked = {})
        }
        composeTestRule.onNodeWithTag(
            TAG_FEATURED_TRACKS
        ).assertDoesNotExist()
    }

    @Test
    fun Featured_Tracks_Displayed() {
        val tracks = TestDataFactory.makeContentList(5)

        composeTestRule.setContent {
            FeaturedTracksGrid(tracks = tracks, onTrackClicked = {})
        }

        var indexToAdd = 0
        tracks.chunked(2).forEachIndexed { rowIndex, trackRow ->
            trackRow.forEachIndexed { index, track ->
                composeTestRule.onNodeWithTag(TAG_FEATURED_TRACKS)
                    .onChildAt(rowIndex + index + indexToAdd)
                    .assert(hasTestTag(Tags.TAG_TRACK + track.id))
            }
            indexToAdd += 1
        }
    }
}