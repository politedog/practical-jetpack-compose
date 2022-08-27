package academy.compose.calendar

import academy.compose.calendar.Tags.TAG_EVENTS_STACK
import academy.compose.calendar.Tags.TAG_WEEK_ROW
import academy.compose.calendar.ui.WeekRow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import java.util.*

class WeekTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Days_Of_Week_Displayed() {
        val calendar = Calendar.getInstance()
        composeTestRule.setContent {
            WeekRow(
                modifier = Modifier.testTag(TAG_WEEK_ROW),
                calendarWeek = calendar,
                events = emptyList()
            )
        }
        repeat(7) {
            composeTestRule.onNodeWithTag(
                TAG_WEEK_ROW
            ).onChildAt(it)
                .assertTextEquals(calendar.get(Calendar.DAY_OF_MONTH).toString())
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }
    }

    @Test
    fun Events_Displayed() {
        val calendar = Calendar.getInstance()
        composeTestRule.setContent {
            WeekRow(
                modifier = Modifier.testTag(TAG_WEEK_ROW),
                calendarWeek = calendar,
                events = TestDataFactory.events(5, Calendar.getInstance())
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_EVENTS_STACK
        ).assertIsDisplayed()
    }
}