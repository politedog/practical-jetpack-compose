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

package academy.compose.home.ui

import academy.compose.home.R
import academy.compose.home.Tags.TAG_RAIL_CREATE
import academy.compose.home.Tags.TAG_RAIL_NAVIGATION
import academy.compose.home.model.Destination
import academy.compose.home.model.NavigationBarItem.Companion.buildNavigationItems
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RailNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {
    NavigationRail(
        modifier = modifier.testTag(TAG_RAIL_NAVIGATION),
        header = {
            FloatingActionButton(
                modifier = Modifier.testTag(TAG_RAIL_CREATE),
                onClick = {
                    onCreateItem()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.cd_create_item)
                )
            }
        }
    ) {
        buildNavigationItems(
            currentDestination = currentDestination,
            onNavigate = onNavigate
        ).forEach { destination ->
            NavigationRailItem(
                selected = destination.selected,
                icon = { destination.icon() },
                label = { destination.label() },
                onClick = { destination.onClick() }
            )
        }
    }
}

@Preview
@Composable
fun Preview_RailNavigationBar() {
    MaterialTheme {
        RailNavigationBar(
            modifier = Modifier.fillMaxHeight(),
            currentDestination = Destination.Feed,
            onNavigate = {},
            onCreateItem = {}
        )
    }
}