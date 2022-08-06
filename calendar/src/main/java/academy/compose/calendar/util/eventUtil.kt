package academy.compose.calendar.util

import academy.compose.calendar.model.CalendarEvent
import academy.compose.calendar.model.EventType
import java.util.Calendar

fun eventsForCurrentWeek(
    date: Calendar,
    events: List<CalendarEvent>
): List<CalendarEvent> {
    return events
        .filter { withinCurrentWeek(date, it.startDate, it.endDate) }
}

fun eventType(
    currentDate: Calendar,
    eventStart: Calendar,
    eventEnd: Calendar
): EventType {
    return when {
        isSameDate(currentDate, eventStart) && isSameDate(currentDate, eventEnd)
        -> EventType.SINGLE_DAY
        isSameDate(currentDate, eventStart) -> EventType.START
        isSameDate(currentDate, eventEnd) -> EventType.END
        isBetweenDates(currentDate, eventStart, eventEnd) -> EventType.MIDDLE
        else -> EventType.NONE
    }
}