/*
 * Copyright 2022 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package academy.compose.music.ui.search

import academy.compose.music.R
import academy.compose.music.ContentFactory
import academy.compose.music.Tags.TAG_SEARCH_RESULTS
import academy.compose.music.Tags.TAG_TRACK
import academy.compose.music.TestDataFactory
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