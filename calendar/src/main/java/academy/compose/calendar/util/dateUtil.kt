package academy.compose.calendar.util

import org.joda.time.DateTime
import java.util.Calendar

fun currentDateForCalendarPage(
    startingDate: Calendar
): Calendar {
    val datesToDraw = startingDate.clone() as Calendar
    datesToDraw.apply {
        timeInMillis = DateTime(this).withDayOfMonth(1).millis
    }
    val dayOfWeek = datesToDraw.get(Calendar.DAY_OF_WEEK)
    if (dayOfWeek != 1) datesToDraw.add(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1))
    return datesToDraw
}

fun isSameDate(
    firstDate: Calendar,
    secondDate: Calendar
): Boolean {
    return firstDate.get(Calendar.DAY_OF_MONTH) == secondDate.get(Calendar.DAY_OF_MONTH) &&
        firstDate.get(Calendar.MONTH) == secondDate.get(Calendar.MONTH) &&
        firstDate.get(Calendar.YEAR) == secondDate.get(Calendar.YEAR)
}

fun isBeforeDate(
    firstDate: Calendar,
    secondDate: Calendar
): Boolean {
    return firstDate.time < secondDate.time
}

fun isBetweenDates(
    currentDate: Calendar,
    startDate: Calendar,
    endDate: Calendar
): Boolean {
    return isBeforeDate(startDate, currentDate) && isAfterDate(endDate, currentDate)
}

fun isAfterDate(
    firstDate: Calendar,
    secondDate: Calendar
): Boolean {
    return firstDate.time > secondDate.time
}

fun withinCurrentWeek(
    weekStart: Calendar,
    eventStart: Calendar,
    eventEnd: Calendar
): Boolean {
    return isBetweenDates(weekStart, eventStart, eventEnd) ||
        isSameDate(weekStart, eventStart) || isSameDate(weekStart, eventEnd)
}
