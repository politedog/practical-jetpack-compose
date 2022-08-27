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
import academy.compose.calendar.util.currentDateForCalendarPage
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun PagerMonth(
    modifier: Modifier = Modifier,
    currentDate: Calendar
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        DaysOfWeek(
            modifier = Modifier.fillMaxWidth().testTag(
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

@Preview(showBackground = true)
@Composable
fun Preview_PagerMonth() {
    PagerMonth(
        modifier = Modifier.fillMaxSize(),
        currentDate = Calendar.getInstance()
    )
}