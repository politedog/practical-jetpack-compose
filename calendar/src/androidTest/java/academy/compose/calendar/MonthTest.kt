package academy.compose.practical.calendar

import academy.compose.calendar.Tags.TAG_MONTH
import academy.compose.calendar.util.currentDateForCalendarPage
import academy.compose.calendar.ui.Month
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import java.util.*

class MonthTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Weeks_Of_Month_Displayed() {
        val calendar = currentDateForCalendarPage(Calendar.getInstance())
        composeTestRule.setContent {
            Month(
                modifier = Modifier.testTag(TAG_MONTH),
                date = calendar
            )
        }
        repeat(6) {
            composeTestRule.onNodeWithTag(
                TAG_MONTH
            ).onChildAt(it)
                .assert(hasTestTag(calendar.get(Calendar.WEEK_OF_YEAR).toString()))
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }
    }
}