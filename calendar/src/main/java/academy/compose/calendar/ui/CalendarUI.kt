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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import java.util.Calendar.getInstance

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun Calendar() {
    val state = remember { CalendarState(getInstance()) }
    MonthCalendar(
        modifier = Modifier.fillMaxSize(),
        calendarState = state
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun Preview_Calendar() {
    Calendar()
}