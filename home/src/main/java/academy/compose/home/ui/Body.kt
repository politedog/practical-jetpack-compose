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

import academy.compose.home.model.Destination
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun Body(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    destination: Destination,
    orientation: Int,
    onNavigate: (destination: Destination) -> Unit,
    onCreateItem: () -> Unit
) {
    Row(modifier = modifier) {
        if (destination.isRootDestination && orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RailNavigationBar(
                currentDestination = destination,
                onNavigate = onNavigate,
                onCreateItem = onCreateItem
            )
        }
        Navigation(
            modifier = Modifier.fillMaxSize(),
            navController = navController
        )
    }
}