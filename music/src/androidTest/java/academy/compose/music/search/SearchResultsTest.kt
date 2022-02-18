package academy.compose.music.search

import academy.compose.music.R
import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.ui.search.SearchResults
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollTo
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class SearchResultsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Results_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            SearchResults(results = emptyList(), onTrackClicked = {})
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.message_search_results
            )
        ).assertIsDisplayed()
    }

    @Test
    fun Empty_State_Not_Displayed_When_ResultsExist() {
        composeTestRule.setContent {
            SearchResults(results = ContentFactory.makeContentList(), onTrackClicked = {})
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.message_search_results
            )
        ).assertDoesNotExist()
    }

    @ExperimentalTestApi
    @Test
    fun Search_Results_Displayed() {
        val tracks = TestDataFactory.makeContentList(5)

        composeTestRule.setContent {
            SearchResults(results = tracks, onTrackClicked = {})
        }

        tracks.forEachIndexed { index, track ->
            composeTestRule.onNodeWithTag(TAG_SEARCH_RESULTS)
                .onChildAt(index).apply {
                    performScrollTo()
                    assert(hasTestTag(TAG_TRACK + track.id))
                }
        }
    }

    @Test
    fun Search_Results_Not_Displayed_When_Empty() {
        composeTestRule.setContent {
            SearchResults(results = emptyList(), onTrackClicked = {})
        }
        composeTestRule.onNodeWithTag(TAG_SEARCH_RESULTS)
            .assertDoesNotExist()
    }
}