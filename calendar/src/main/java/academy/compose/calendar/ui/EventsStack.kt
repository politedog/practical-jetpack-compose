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

import academy.compose.calendar.EventFactory
import academy.compose.calendar.R
import academy.compose.calendar.model.CalendarEvent
import academy.compose.calendar.Tags.TAG_EVENTS_STACK
import academy.compose.calendar.model.EventType
import academy.compose.calendar.util.eventType
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.tooling.preview.Preview
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
    Box(
        Modifier
            .padding(bottom = 2.dp)
            .eventBackground(color, eventType)
            .height(5.dp)
            .fillMaxWidth()
    )
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

@Preview(showBackground = true)
@Composable
fun Preview_EventBox() {
    EventBox(Color.Red, EventType.START)
}

@Preview(showBackground = true)
@Composable
fun Preview_EventStack() {
    EventStack(
        modifier = Modifier.fillMaxWidth(),
        events = EventFactory.events,
        date = Calendar.getInstance()
    )
}