package academy.compose.music.search

import academy.compose.music.R
import academy.compose.music.Tags.TAG_SEARCH_BAR
import academy.compose.music.ui.search.SearchBar
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

        composeTestRule.onNodeWithTag(TAG_SEARCH_BAR)
            .performClick()

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext.getString(
                R.string.cd_clear_query
            )
        ).assertIsDisplayed()
    }
}