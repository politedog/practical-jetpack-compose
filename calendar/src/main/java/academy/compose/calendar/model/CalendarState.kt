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
package academy.compose.calendar.model

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import java.util.*

class CalendarState(val date: Calendar) {

    val pageCount = Int.MAX_VALUE
    val startPosition = Int.MAX_VALUE / 2

    var pagePosition by mutableStateOf(startPosition)

    val currentMonth: String by derivedStateOf {
        (date.clone() as Calendar).apply {
            add(Calendar.MONTH, pagePosition - startPosition)
        }.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG_FORMAT, Locale.getDefault()
        )
    }
}
