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

import academy.compose.home.Tags.TAG_CHILD_TOP_BAR
import academy.compose.home.Tags.TAG_ROOT_TOP_BAR
import academy.compose.home.model.Destination
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun DestinationTopBar(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    openDrawer: () -> Unit,
    onNavigateUp: () -> Unit,
    showSnackbar: (message: String) -> Unit
) {
    if (currentDestination.isRootDestination) {
        RootDestinationTopBar(
            modifier = modifier.testTag(TAG_ROOT_TOP_BAR),
            currentDestination = currentDestination,
            openDrawer = {
                openDrawer()
            },
            showSnackbar = { message ->
                showSnackbar(message)
            }
        )
    } else {
        ChildDestinationTopBar(
            modifier = modifier.testTag(TAG_CHILD_TOP_BAR),
            title = currentDestination.title,
            onNavigateUp = onNavigateUp
        )
    }
}