package academy.compose.calendar.ui

import academy.compose.calendar.model.CalendarEvent
import academy.compose.calendar.util.eventsForCurrentWeek
import academy.compose.calendar.util.isSameDate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun WeekRow(
    modifier: Modifier,
    calendarWeek: Calendar,
    events: List<CalendarEvent>
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        (0 until 7).forEach { rowPosition ->
            val dateForMonthCell = calendarWeek.clone() as Calendar
            dateForMonthCell.add(
                Calendar.DAY_OF_WEEK,
                (rowPosition)
            )
            WeekDay(dateForMonthCell,
                eventsForCurrentWeek(dateForMonthCell, events)
            )
        }
    }
}

@Composable
fun RowScope.WeekDay(
    date: Calendar,
    events: List<CalendarEvent>
) {
    Column(
        Modifier
            .weight(1f)
            .fillMaxHeight()
            .drawBehind {
                val strokeWidth = 1 * density
                drawLine(
                    Color.LightGray,
                    Offset(size.width, 0f),
                    Offset(size.width, size.height),
                    strokeWidth
                )
            },
        horizontalAlignment = Alignment.End
    ) {
        val isToday = isSameDate(Calendar.getInstance(), date)
        Text(
            modifier = Modifier.dateBackground(isToday),
            text = date.get(Calendar.DAY_OF_MONTH).toString(),
            fontSize = 12.sp,
            textAlign = TextAlign.End,
            color = if (isToday) Color.White else Color.Black
        )
        Spacer(modifier = Modifier.height(2.dp))
        EventStack(
            modifier = Modifier.fillMaxWidth(),
            events = events,
            date = date
        )
    }
}

fun Modifier.dateBackground(
    isToday: Boolean
): Modifier {
    return if (isToday) {
        this.padding(4.dp)
            .background(
                Color.Blue, CircleShape
            )
            .padding(4.dp)
    } else {
        this.padding(8.dp)
    }
}