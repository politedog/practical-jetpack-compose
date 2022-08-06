package academy.compose.practical.calendar

import academy.compose.example.calendar.R
import academy.compose.calendar.Tags.TAG_DAYS_OF_WEEK
import academy.compose.calendar.ui.DaysOfWeek
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class DaysOfWeekTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Days_Of_Week_Displayed() {
        composeTestRule.setContent {
            DaysOfWeek()
        }
        InstrumentationRegistry.getInstrumentation().context
            .resources.getStringArray(R.array.days_of_week)
            .forEachIndexed { index, day ->
                composeTestRule.onNodeWithTag(
                    TAG_DAYS_OF_WEEK
                ).onChildAt(index).assertTextEquals(day)
            }
    }
}