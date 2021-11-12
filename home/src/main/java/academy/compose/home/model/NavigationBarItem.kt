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

package academy.compose.home.model

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

class NavigationBarItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val label: @Composable () -> Unit
) {

    companion object {
        fun buildNavigationItems(
            currentDestination: Destination,
            onNavigate: (destination: Destination) -> Unit
        ): List<NavigationBarItem> {
            return listOf(
                Destination.Feed,
                Destination.Contacts,
                Destination.Calendar
            ).map {  destination ->
                NavigationBarItem(
                    selected = currentDestination == destination,
                    label = {
                        Text(text = destination.title)
                    },
                    icon = {
                        destination.icon?.let { image ->
                            Icon(
                                imageVector = image,
                                contentDescription = destination.path
                            )
                        }
                    },
                    onClick = {
                        onNavigate(destination)
                    }
                )
            }
        }
    }

}