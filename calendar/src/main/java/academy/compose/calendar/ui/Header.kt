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
import academy.compose.calendar.Tags
import academy.compose.calendar.Tags.TAG_MONTH_HEADER
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    previousMonth: () -> Unit,
    nextMonth: () -> Unit
) {
    Row(modifier = modifier.padding(16.dp).testTag(Tags.TAG_HEADER)) {
        Icon(
            modifier = Modifier.clickable(
                onClickLabel = stringResource(id = R.string.cd_previous_month)
            ) {
                previousMonth()
            },
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null
        )
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .testTag(TAG_MONTH_HEADER),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Icon(
            modifier = Modifier.clickable(
                onClickLabel = stringResource(id = R.string.cd_next_month)
            ) {
                nextMonth()
            },
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun Preview_Header() {
    Header(
        modifier = Modifier.fillMaxWidth(),
        title = "March",
        previousMonth = {},
        nextMonth = {}
    )
}