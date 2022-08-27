/*
 * Copyright 2022 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
