package academy.compose.practical.calendar

import academy.compose.calendar.EventFactory
import academy.compose.example.calendar.R
import academy.compose.calendar.Tags.TAG_EVENTS_STACK
import academy.compose.calendar.ui.EventStack
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import java.util.*

class EventsStackTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Events_Stack_Displayed() {
        composeTestRule.setContent {
            EventStack(
                events = EventFactory.events,
                date = Calendar.getInstance()
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_EVENTS_STACK
        ).assertIsDisplayed()
    }

    @Test
    fun Events_Stack_Not_Displayed_When_No_Events_Exist() {
        composeTestRule.setContent {
            EventStack(
                events = emptyList(),
                date = Calendar.getInstance()
            )
        }
        composeTestRule.onNodeWithTag(
            TAG_EVENTS_STACK
        ).assertDoesNotExist()
    }

    @Test
    fun View_More_Events_Displayed_When_Events_Exceed_Height() {
        composeTestRule.setContent {
            EventStack(
                modifier = Modifier.height(100.dp),
                events = TestDataFactory.events(10, Calendar.getInstance()),
                date = Calendar.getInstance()
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().context
                .getString(R.string.cd_view_more_events)
        ).assertIsDisplayed()
    }

    @Test
    fun View_More_Events_Not_Displayed_When_Events_Do_Not_Exceed_Height() {
        composeTestRule.setContent {
            EventStack(
                events = TestDataFactory.events(1, Calendar.getInstance()),
                date = Calendar.getInstance()
            )
        }
        composeTestRule.onNodeWithContentDescription(
            InstrumentationRegistry.getInstrumentation().context
                .getString(R.string.cd_view_more_events)
        ).assertIsNotDisplayed()
    }
}