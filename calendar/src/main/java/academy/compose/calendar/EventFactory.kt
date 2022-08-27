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
package academy.compose.calendar

import academy.compose.calendar.model.CalendarEvent
import androidx.compose.ui.graphics.Color
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

object EventFactory {

    val events = listOf(
        CalendarEvent(
            Color.Red,
            Calendar.getInstance(),
            Calendar.getInstance()
        ),
        CalendarEvent(
            Color.Blue,
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 1)
            },
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        ),
        CalendarEvent(
            Color.Gray,
            Calendar.getInstance(),
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, 9)
            }
        ),
        CalendarEvent(
            Color.Magenta,
            Calendar.getInstance(),
            Calendar.getInstance()
        ),
        CalendarEvent(
            Color.Blue,
            Calendar.getInstance(),
            Calendar.getInstance()
        ),
        CalendarEvent(
            Color.Red,
            Calendar.getInstance(),
            Calendar.getInstance()
        ),
        CalendarEvent(
            Color.Blue,
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, -19)
            },
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_YEAR, -14)
            }
        ),
        CalendarEvent(
            Color.Blue,
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
            },
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
            }
        ),
        CalendarEvent(
            Color.Red,
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
                add(Calendar.DAY_OF_YEAR, 5)
            },
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
                add(Calendar.DAY_OF_YEAR, 7)
            }
        ),
        CalendarEvent(
            Color.Red,
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
                add(Calendar.DAY_OF_YEAR, -12)
            },
            Calendar.getInstance().apply {
                add(Calendar.MONTH, 1)
                add(Calendar.DAY_OF_YEAR, -5)
            }
        )
    ).sortedByDescending {
        TimeUnit.MILLISECONDS.toDays(abs(it.endDate.time.time - it.startDate.time.time))
    }
}