package academy.compose.practical.calendar

import academy.compose.calendar.model.CalendarEvent
import java.util.UUID
import java.util.Calendar

object TestDataFactory {
    fun randomString() = UUID.randomUUID().toString()

    fun events(count: Int, date: Calendar): List<CalendarEvent> {
        return (0 until count).map {
            CalendarEvent(
                randomString(),
                date,
                date
            )
        }
    }
}