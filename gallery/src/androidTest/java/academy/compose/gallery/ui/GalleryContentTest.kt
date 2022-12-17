package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.Tags.TAG_DENIED_PERMISSION
import academy.compose.gallery.Tags.TAG_IMAGE_GALLERY
import academy.compose.gallery.Tags.TAG_PROGRESS
import academy.compose.gallery.model.Image
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
class GalleryContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Image_Gallery_Displayed() {
        val permissionState = object : PermissionState {
            override val permission: String
                get() = ""
            override val status: PermissionStatus
                get() = PermissionStatus.Granted

            override fun launchPermissionRequest() { }
        }
        composeTestRule.setContent {
            GalleryContent(
                media = listOf(Image(0, Uri.EMPTY, "")),
                permissionState = permissionState,
                openSettings = {}
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_IMAGE_GALLERY)
            .assertIsDisplayed()
    }

    @Test
    fun Progress_Displayed() {
        val permissionState = object : PermissionState {
            override val permission: String
                get() = ""
            override val status: PermissionStatus
                get() = PermissionStatus.Granted

            override fun launchPermissionRequest() { }
        }
        composeTestRule.setContent {
            GalleryContent(
                media = null,
                permissionState = permissionState,
                openSettings = {}
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_PROGRESS)
            .assertIsDisplayed()
    }

    @Test
    fun Denied_Permission_Displayed() {
        val permissionState = object : PermissionState {
            override val permission: String
                get() = ""
            override val status: PermissionStatus
                get() = PermissionStatus.Denied(shouldShowRationale = false)

            override fun launchPermissionRequest() { }
        }
        composeTestRule.setContent {
            GalleryContent(
                media = null,
                permissionState = permissionState,
                openSettings = {}
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_DENIED_PERMISSION)
            .assertIsDisplayed()
    }

    @Test
    fun Permission_Request_Triggered() {
        val permissionState = mock<PermissionState>()
        whenever(permissionState.status)
            .doReturn(PermissionStatus.Denied(shouldShowRationale = true))

        composeTestRule.setContent {
            GalleryContent(
                media = null,
                permissionState = permissionState,
                openSettings = {}
            )
        }
        val grantPermission = InstrumentationRegistry.getInstrumentation().targetContext
            .getString(R.string.permission_explainer_action)
        composeTestRule
            .onNodeWithText(grantPermission)
            .performClick()
        verify(permissionState).launchPermissionRequest()
    }
}