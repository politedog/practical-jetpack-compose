package academy.compose.music

import academy.compose.music.Tags.TAG_DASHBOARD
import academy.compose.music.Tags.TAG_PLAYER
import academy.compose.music.Tags.TAG_PLAYER_BAR
import academy.compose.music.Tags.TAG_SEARCH_BAR
import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.ui.Dashboard
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalAnimationApi
class ScaffoldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Content_Area_Displayed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_DASHBOARD)
            .assertIsDisplayed()
    }

    @Test
    fun Search_Bar_Displayed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_SEARCH_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Full_Screen_Player_Revealed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()
    }

    @Test
    fun Player_Dismissed_Hides_Player() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_close_now_playing)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertDoesNotExist()
    }

    @Test
    fun Player_Dismissed_Shows_PLayer_Bar() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().targetContext
                .getString(R.string.cd_close_now_playing)
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertIsDisplayed()
    }
}