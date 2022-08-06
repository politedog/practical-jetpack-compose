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
package academy.compose.graphs.ui

import academy.compose.graphs.R
import academy.compose.graphs.model.GraphType
import academy.compose.graphs.Tags.TAG_CHART_TYPES
import academy.compose.graphs.Tags.TAG_SELECTED_CHART
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChartPicker(
    modifier: Modifier = Modifier,
    chart: GraphType,
    expanded: Boolean,
    dismiss: () -> Unit,
    onItemSelected: (chart: GraphType) -> Unit,
    onPickerRequested: () -> Unit
) {
    Box(modifier = modifier) {
        val clickLabel = stringResource(id = R.string.cd_select_chart)
        Row(
            modifier = Modifier
                .clickable(onClickLabel = clickLabel) { onPickerRequested() }
                .semantics(mergeDescendants = true) { },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .widthIn(min = 60.dp)
                    .testTag(TAG_SELECTED_CHART),
                text = stringResource(id = chart.label),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        }
        DropdownMenu(
            modifier = Modifier.testTag(TAG_CHART_TYPES),
            expanded = expanded,
            onDismissRequest = dismiss
        ) {
            GraphType.values().map {
                DropdownMenuItem(onClick = { onItemSelected(it) }) {
                    Text(text = stringResource(id = it.label))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ChartPicker() {
    MaterialTheme {
        ChartPicker(
            modifier = Modifier,
            GraphType.COLUMN,
            false,
            {},
            {},
            {}
        )
    }
}