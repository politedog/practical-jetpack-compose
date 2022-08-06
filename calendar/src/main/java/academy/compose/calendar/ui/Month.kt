package academy.compose.calendar.ui

import academy.compose.calendar.EventFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun Month(
    modifier: Modifier = Modifier,
    date: Calendar
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        (0 until 6).forEach { columnPosition ->
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            val dateForMonthCell = date.clone() as Calendar
            dateForMonthCell.add(
                Calendar.WEEK_OF_YEAR,
                columnPosition
            )
            WeekRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .testTag(dateForMonthCell.get(Calendar.WEEK_OF_YEAR).toString()),
                calendarWeek = dateForMonthCell,
                EventFactory.events
            )
        }
    }
}