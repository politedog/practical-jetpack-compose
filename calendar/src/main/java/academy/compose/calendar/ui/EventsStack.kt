package academy.compose.calendar.ui

import academy.compose.calendar.R
import academy.compose.calendar.model.CalendarEvent
import academy.compose.calendar.Tags.TAG_EVENTS_STACK
import academy.compose.calendar.model.EventType
import academy.compose.calendar.util.eventType
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun EventStack(
    modifier: Modifier = Modifier,
    events: List<CalendarEvent>,
    date: Calendar
) {
    if (events.isNotEmpty()) {
        Layout(
            modifier = modifier.testTag(TAG_EVENTS_STACK),
            content = {
                events.forEach { event ->
                    val eventType = eventType(
                        date,
                        event.startDate, event.endDate
                    )
                    if (eventType != EventType.NONE) {
                        EventBox(event.color, eventType)
                    }
                }
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = stringResource(id = R.string.cd_view_more_events)
                )
            }
        ) { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                measurable.measure(constraints)
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var yPosition = 0

                val mHeight = constraints.maxHeight

                val showMore = placeables.subList(0, placeables.count() - 1).map {
                    it.height
                }.sum() > constraints.maxHeight

                val availableHeight = constraints.maxHeight - placeables.last().height

                placeables.subList(0, placeables.count() - 1).forEach { placeable ->
                    if (!showMore || (yPosition + placeable.height < availableHeight)) {
                        placeable.placeRelative(x = 0, y = yPosition)
                        yPosition += placeable.height
                    }
                }
                if (showMore) {
                    val more = placeables.last()
                    more.placeRelative(x = constraints.maxWidth - more.width - 5, y = yPosition)
                }
            }
        }
    }
}

@Composable
fun EventBox(color: Color, eventType: EventType) {
    Column {
        Box(
            Modifier
                .eventBackground(color, eventType)
                .height(5.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(2.dp))
    }
}

fun Modifier.eventBackground(
    color: Color,
    eventType: EventType
): Modifier {
    return when (eventType) {
        EventType.START -> {
            this
                .padding(start = 2.dp)
                .background(
                    color, RoundedCornerShape(
                        2.dp, 0.dp, 0.dp, 2.dp
                    )
                )
        }
        EventType.END -> {
            this
                .padding(end = 2.dp)
                .background(
                    color, RoundedCornerShape(
                        0.dp, 2.dp, 2.dp, 0.dp
                    )
                )
        }
        EventType.SINGLE_DAY -> {
            this
                .padding(horizontal = 2.dp)
                .background(
                    color, RoundedCornerShape(
                        2.dp, 2.dp, 2.dp, 2.dp
                    )
                )
        }
        else -> {
            this.background(
                color, RoundedCornerShape(
                    0.dp, 0.dp, 0.dp, 0.dp
                )
            )
        }
    }
}