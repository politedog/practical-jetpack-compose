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

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(
    val path: String,
    val icon: ImageVector? = null,
    val isRootDestination: Boolean = true
) {

    companion object {
        fun fromString(route: String?): Destination {
            return when (route) {
                Feed.path -> Feed
                Calendar.path -> Calendar
                Contacts.path -> Contacts
                Settings.path -> Settings
                Upgrade.path -> Upgrade
                Add.path -> Add
                Creation.path -> Creation
                else -> Home
            }
        }
    }

    val title = path.replaceFirstChar {
        it.uppercase()
    }

    object Home : Destination("home")

    object Feed : Destination("feed", Icons.Default.List)

    object Contacts : Destination("contacts", Icons.Default.Person)

    object Calendar : Destination("calendar", Icons.Default.DateRange)

    object Settings : Destination("settings", Icons.Default.Settings, isRootDestination = false)

    object Upgrade : Destination("upgrade", Icons.Default.Star, isRootDestination = false)

    object Creation : Destination("creation", isRootDestination = false)

    object Add : Destination("add", Icons.Default.Add, isRootDestination = false)
}
