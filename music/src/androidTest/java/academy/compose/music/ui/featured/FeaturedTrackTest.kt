package academy.compose.music.ui.featured

import academy.compose.practical.music_catalog.TestDataFactory
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class FeaturedTrackTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val track = TestDataFactory.makeTrack()
        composeTestRule.setContent {
            FeaturedTrack(track = track)
        }
        composeTestRule.onNodeWithText(
            track.title
        ).assertIsDisplayed()
    }

}