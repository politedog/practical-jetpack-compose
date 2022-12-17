package academy.compose.calendar

import academy.compose.calendar.model.CalendarState
import academy.compose.calendar.Tags.TAG_DAYS_OF_WEEK
import academy.compose.calendar.Tags.TAG_HEADER
import academy.compose.calendar.Tags.TAG_MONTH_HEADER
import academy.compose.calendar.Tags.TAG_MONTH_PAGE
import academy.compose.calendar.ui.MonthCalendar
import android.os.SystemClock
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Rule
import org.junit.Test
import java.util.*

@ExperimentalFoundationApi
@ExperimentalPagerApi
class MonthlyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Header_Displayed() {
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(
                    Calendar.getInstance()
                )
            )
        }

        composeTestRule.onNodeWithTag(TAG_HEADER)
            .assertIsDisplayed()
    }

    @Test
    fun Current_Month_Name_Displayed() {
        val currentMonth = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(
                    currentMonth
                )
            )
        }
        composeTestRule.onNodeWithTag(TAG_MONTH_HEADER)
            .assertTextEquals(
                currentMonth.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG_FORMAT, Locale.getDefault()
                )
            )
    }

    @Test
    fun Month_Page_Displayed() {
        val calendar = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(
                    calendar
                )
            )
        }
        composeTestRule
            .onNodeWithTag(TAG_MONTH_PAGE + calendar.get(Calendar.MONTH))
            .assertIsDisplayed()
    }

    @Test
    fun Next_Month_Page_Displayed() {
        val calendar = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(calendar)
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_next_month)
            )
            .performClick()

        val nextMonth = (calendar.clone() as Calendar).apply {
            add(Calendar.MONTH, 1)
        }

        composeTestRule
            .onNodeWithTag(TAG_MONTH_PAGE + nextMonth.get(Calendar.MONTH))
            .assertIsDisplayed()
    }

    @Test
    fun Next_Month_Name_Displayed() {
        val currentMonth = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(
                    currentMonth
                )
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_next_month)
            )
            .performClick()

        val nextMonth = (currentMonth.clone() as Calendar).apply {
            add(Calendar.MONTH, 1)
        }
        composeTestRule.onNodeWithTag(TAG_MONTH_HEADER)
            .assertTextEquals(
                nextMonth.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG_FORMAT, Locale.getDefault()
                )
            )
    }

    @Test
    fun Previous_Month_Page_Displayed() {
        val calendar = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(calendar)
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_previous_month)
            )
            .performClick()

        val nextMonth = (calendar.clone() as Calendar).apply {
            add(Calendar.MONTH, -1)
        }

        composeTestRule
            .onNodeWithTag(TAG_MONTH_PAGE + nextMonth.get(Calendar.MONTH))
            .assertIsDisplayed()
    }

    @Test
    fun Previous_Month_Name_Displayed() {
        val currentMonth = Calendar.getInstance()
        composeTestRule.setContent {
            MonthCalendar(
                calendarState = CalendarState(
                    currentMonth
                )
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.cd_previous_month)
            )
            .performClick()

        val previousMonth = (currentMonth.clone() as Calendar).apply {
            add(Calendar.MONTH, -1)
        }
        composeTestRule.onNodeWithTag(TAG_MONTH_HEADER)
            .assertTextEquals(
                previousMonth.getDisplayName(
                    Calendar.MONTH,
                    Calendar.LONG_FORMAT, Locale.getDefault()
                )
            )
    }
}