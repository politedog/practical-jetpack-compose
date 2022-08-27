package academy.compose.calendar

import academy.compose.calendar.TestDataFactory.randomString
import academy.compose.calendar.ui.Header
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class HeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Title_Displayed() {
        val title = randomString()
        composeTestRule.setContent {
            Header(
                title = title,
                nextMonth = { },
                previousMonth = { }
            )
        }
        composeTestRule.onNodeWithText(
            title
        ).assertIsDisplayed()
    }

    @Test
    fun Previous_Month_Triggers_Callback() {
        val previousMonth: () -> Unit = mock()
        composeTestRule.setContent {
            Header(
                title = randomString(),
                nextMonth = { },
                previousMonth = previousMonth
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().context
                .getString(R.string.cd_previous_month)
        ).performClick()
        verify(previousMonth).invoke()
    }

    @Test
    fun Next_Month_Triggers_Callback() {
        val nextMonth: () -> Unit = mock()
        composeTestRule.setContent {
            Header(
                title = randomString(),
                nextMonth = nextMonth,
                previousMonth = {  }
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().context
                .getString(R.string.cd_next_month)
        ).performClick()
        verify(nextMonth).invoke()
    }
}