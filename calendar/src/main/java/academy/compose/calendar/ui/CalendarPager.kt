package academy.compose.calendar.ui

import academy.compose.calendar.Tags
import academy.compose.calendar.util.currentDateForCalendarPage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import java.util.*

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun CalendarPager(
    pagerState: PagerState,
    startIndex: Int,
    dates: Calendar,
    pageCount: Int
) {
    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .testTag(Tags.TAG_MONTH_PAGER),
            state = pagerState,
            count = pageCount
        ) { index ->
            val currentDate = (dates.clone() as Calendar).apply {
                add(Calendar.MONTH, index - startIndex)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DaysOfWeek(
                    modifier = Modifier.testTag(
                        Tags.TAG_DAYS_OF_WEEK + "_" + currentDate
                            .get(Calendar.MONTH)
                            .toString()
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Month(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(
                            Tags.TAG_MONTH_PAGE + currentDate
                                .get(Calendar.MONTH)
                                .toString()
                        ),
                    date = currentDateForCalendarPage(
                        currentDate
                    )
                )
            }
        }
    }
}