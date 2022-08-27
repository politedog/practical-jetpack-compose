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

import academy.compose.calendar.R
import academy.compose.calendar.Tags.TAG_DAYS_OF_WEEK
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun DaysOfWeek(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .testTag(TAG_DAYS_OF_WEEK),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        stringArrayResource(id = R.array.days_of_week).forEach {
            Text(
                modifier = Modifier.weight(1f),
                text = it,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DaysOfWeek() {
    DaysOfWeek(
        modifier = Modifier.fillMaxWidth()
    )
}