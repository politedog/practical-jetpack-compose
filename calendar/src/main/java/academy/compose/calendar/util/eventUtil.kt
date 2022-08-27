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