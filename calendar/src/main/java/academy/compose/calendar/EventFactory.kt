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