package academy.compose.music.ui.player

import academy.compose.music.Tags.TAG_PLAYER
import academy.compose.music.Tags.TAG_PLAYER_BAR
import academy.compose.practical.music_catalog.TestDataFactory
import academy.compose.music.model.MusicDashboardState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class MusicPlayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Test
    fun Collapsed_Player_Bar_Displayed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBackdropScaffoldState(
                initialValue = BackdropValue.Revealed
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertIsDisplayed()
    }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Test
    fun Player_Never_Revealed_While_Player_Bar_Collapsed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBackdropScaffoldState(
                initialValue =
                BackdropValue.Concealed
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertDoesNotExist()
    }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Test
    fun Revealed_Player_Bar_Displayed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBackdropScaffoldState(
                initialValue = BackdropValue.Concealed
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()
    }

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    @Test
    fun Player_Bar_Never_Displayed_When_Player_Revealed() {
        composeTestRule.setContent {
            val scaffoldState = rememberBackdropScaffoldState(
                initialValue =
                BackdropValue.Revealed
            )
            MusicPlayer(
                scaffoldState = scaffoldState,
                state = MusicDashboardState(),
                handleEvent = {}
            )
        }
        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertDoesNotExist()
    }
}