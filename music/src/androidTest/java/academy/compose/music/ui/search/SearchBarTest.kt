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
import academy.compose.music.Tags.TAG_SEARCH_BAR
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalAnimationApi
class SearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Hint_Displayed_By_Default() {
        composeTestRule.setContent {
            SearchBar(query = "", handleQuery = {}, clearQuery = {})
        }
        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.hint_search
            )
        ).assertIsDisplayed()
    }

    @Test
    fun Hint_Hidden_When_Focused() {
        composeTestRule.setContent {
            SearchBar(query = "", handleQuery = {}, clearQuery = {})
        }

        composeTestRule.onNodeWithTag(TAG_SEARCH_BAR)
            .performClick()

        composeTestRule.onNodeWithText(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.hint_search
            )
        ).assertDoesNotExist()
    }

    @Test
    fun Search_Icon_Displayed_By_Default() {
        composeTestRule.setContent {
            SearchBar(query = "", handleQuery = {}, clearQuery = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_search
            )
        ).assertIsDisplayed()
    }

    @Test
    fun Clear_Icon_Hidden_By_Default() {
        composeTestRule.setContent {
            SearchBar(query = "", handleQuery = {}, clearQuery = {})
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_clear_query
            )
        ).assertDoesNotExist()
    }

    @Test
    fun Search_Icon_Hidden_When_Query_Entered() {
        composeTestRule.setContent {
            SearchBar(query = "Test", handleQuery = {}, clearQuery = {})
        }

        composeTestRule.onNodeWithTag(TAG_SEARCH_BAR)
            .performClick()

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_search
            )
        ).assertDoesNotExist()
    }

    @Test
    fun Close_Icon_Displayed_When_Query_Entered() {
        composeTestRule.setContent {
            SearchBar(query = "Test", handleQuery = {}, clearQuery = {})
        }

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_clear_query
            )
        ).assertIsDisplayed()
    }
}