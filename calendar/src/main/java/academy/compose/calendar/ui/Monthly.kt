package academy.compose.calendar.ui

import academy.compose.calendar.model.CalendarState
import academy.compose.calendar.Tags.TAG_HEADER
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun MonthCalendar(
    calendarState: CalendarState
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = calendarState.startPosition)
        Header(
            modifier = Modifier.testTag(TAG_HEADER),
            title = calendarState.currentMonth,
            previousMonth = {
                coroutineScope.launch {
                    pagerState.scrollToPage(
                        pagerState.currentPage - 1
                    )
                }
            },
            nextMonth = {
                coroutineScope.launch {
                    pagerState.scrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        snapshotFlow {
            pagerState.currentPage
        }.collectAsState(initial = calendarState.startPosition).value.let {
            calendarState.pagePosition = it
        }

        CalendarPager(
            pagerState = pagerState,
            startIndex = calendarState.startPosition,
            dates = calendarState.date,
            pageCount = calendarState.pageCount
        )
    }
}
