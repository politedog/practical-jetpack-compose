package academy.compose.calendar.ui

import academy.compose.calendar.model.CalendarState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.pager.ExperimentalPagerApi
import java.util.Calendar.getInstance

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun Calendar() {
    val state = remember { CalendarState(getInstance()) }
    MonthCalendar(state)
}
