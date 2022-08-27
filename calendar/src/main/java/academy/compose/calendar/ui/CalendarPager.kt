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

import academy.compose.calendar.Tags
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
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
        LocalOverscrollConfiguration provides null
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
            PagerMonth(modifier = Modifier.fillMaxWidth(), currentDate = currentDate)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_CalendarPager() {
    CalendarPager(
        PagerState(1),
        1,
        Calendar.getInstance(),
        3
    )
}