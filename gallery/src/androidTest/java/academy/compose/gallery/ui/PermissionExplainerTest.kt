package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.Tags.TAG_REQUEST_PERMISSION_BUTTON
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

class PermissionExplainerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Message_Displayed() {
        composeTestRule.setContent {
            PermissionExplainer(
                requestPermission = { }
            )
        }
        composeTestRule
            .onNodeWithText(
                InstrumentationRegistry.getInstrumentation().targetContext.getString(
                    R.string.permission_explainer_message
                )
            )
            .assertIsDisplayed()
    }

    @Test
    fun Action_Displayed() {
        composeTestRule.setContent {
            PermissionExplainer(
                requestPermission = { }
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_REQUEST_PERMISSION_BUTTON)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext.getString(
                    R.string.permission_explainer_action
                )
            )
    }

    @Test
    fun Action_Triggers_Callback() {
        val requestPermission: () -> Unit = mock()
        composeTestRule.setContent {
            PermissionExplainer(
                requestPermission = requestPermission
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_REQUEST_PERMISSION_BUTTON)
            .performClick()
        verify(requestPermission).invoke()
    }
}