package academy.compose.gallery.ui

import academy.compose.gallery.Tags.TAG_IMAGE
import academy.compose.gallery.Tags.TAG_IMAGE_GRID
import academy.compose.gallery.Tags.TAG_IMAGE_PREVIEW
import academy.compose.gallery.model.Image
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class ImageGalleryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Images_Displayed() {
        val media = listOf(Image(0, Uri.EMPTY, ""), Image(1, Uri.EMPTY, ""))
        composeTestRule.setContent {
            ImageGallery(
                images = media
            )
        }
        media.forEachIndexed { index, image ->
            composeTestRule
                .onNodeWithTag(TAG_IMAGE_GRID)
                .onChildAt(index)
                .assert(hasTestTag(TAG_IMAGE + image.id))
        }
    }

    @Test
    fun Preview_Displayed() {
        val media = listOf(Image(0, Uri.EMPTY, ""), Image(1, Uri.EMPTY, ""))
        composeTestRule.setContent {
            ImageGallery(
                images = media
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_IMAGE_GRID)
            .onChildAt(0)
            .performClick()

        composeTestRule
            .onNodeWithTag(TAG_IMAGE_PREVIEW)
            .assertIsDisplayed()
    }
}