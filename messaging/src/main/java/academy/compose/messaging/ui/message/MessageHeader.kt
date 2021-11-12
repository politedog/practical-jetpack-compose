/*
 * Copyright 2021 Compose Academy
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

package academy.compose.messaging.ui.message

import academy.compose.messaging.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageHeader(
    modifier: Modifier = Modifier,
    isToday: Boolean,
    date: Calendar
) {
    val label = if (isToday) {
        stringResource(id = R.string.label_today)
    } else {
        val dateFormat = remember { SimpleDateFormat("dd MMM yyyy") }
        dateFormat.format(date.time)
    }

    Text(
        modifier = modifier
            .background(
                MaterialTheme.colors.onSurface.copy(
                    alpha = 0.05f
                )
            )
            .padding(vertical = 4.dp),
        text = label,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun Preview_MessageHeader() {
    MaterialTheme {
        MessageHeader(
            isToday = true,
            date = Calendar.getInstance()
        )
    }
}