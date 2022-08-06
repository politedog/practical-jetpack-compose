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
