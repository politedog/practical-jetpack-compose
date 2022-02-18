package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.Tags.TAG_PERMISSIONS_BUTTON
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class DeniedPermissionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Message_Displayed() {
        composeTestRule.setContent {
            DeniedPermission(
                handleLaunchSettings = { }
            )
        }
        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext.getString(
                    R.string.permission_message
                )
            )
            .assertIsDisplayed()
    }

    @Test
    fun Action_Displayed() {
        composeTestRule.setContent {
            DeniedPermission(
                handleLaunchSettings = { }
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_PERMISSIONS_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext.getString(
                    R.string.launch_settings
                )
            )
    }

    @Test
    fun Action_Triggers_Callback() {
        val handleLaunchSettings: () -> Unit = mock()
        composeTestRule.setContent {
            DeniedPermission(
                handleLaunchSettings = handleLaunchSettings
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_PERMISSIONS_BUTTON)
            .performClick()
        verify(handleLaunchSettings).invoke()
    }
}