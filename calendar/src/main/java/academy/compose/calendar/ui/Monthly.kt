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
package academy.compose.calendar.ui

import academy.compose.calendar.model.CalendarState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalFoundationApi
@Composable
fun MonthCalendar(
    modifier: Modifier = Modifier,
    calendarState: CalendarState
) {
    Column(modifier = modifier) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState(initialPage = calendarState.startPosition)
        Header(
            modifier = Modifier.fillMaxWidth(),
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

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_MonthCalendar() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        MonthCalendar(
            Modifier.fillMaxSize(),
            CalendarState(java.util.Calendar.getInstance())
        )
    }
}
