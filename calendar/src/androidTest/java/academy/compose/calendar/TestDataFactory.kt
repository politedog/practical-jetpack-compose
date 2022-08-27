package academy.compose.calendar

import academy.compose.calendar.model.CalendarEvent
import androidx.compose.ui.graphics.Color
import java.util.UUID
import java.util.Calendar

object TestDataFactory {
    fun randomString() = UUID.randomUUID().toString()

    fun events(count: Int, date: Calendar): List<CalendarEvent> {
        return (0 until count).map {
            CalendarEvent(
                Color.Red,
                date,
                date
            )
        }
    }
}