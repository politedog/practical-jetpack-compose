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
import academy.compose.home.model.Destination
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun Home(
    modifier: Modifier = Modifier,
    orientation: Int
) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination by remember {
        derivedStateOf {
            Destination.fromString(navBackStackEntry.value?.destination?.route)
        }
    }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState)
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = {
            DestinationTopBar(
                modifier = Modifier.fillMaxWidth(),
                currentDestination = currentDestination,
                openDrawer = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                },
                onNavigateUp = {
                    navController.popBackStack()
                },
                showSnackbar = { message ->
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = message
                        )
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                onNavigate = { destination ->
                    navController.navigate(destination.path)
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                onLogout = {
                    // handle logout
                }
            )
        },
        bottomBar = {
            if (orientation == Configuration.ORIENTATION_PORTRAIT &&
                currentDestination.isRootDestination
            ) {
                BottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = { destination ->
                        navController.navigate(destination.path) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            if (currentDestination == Destination.Feed
            ) {
                FloatingActionButton(onClick = {
                    navController.navigate(Destination.Creation.path)
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.cd_create_item)
                    )
                }
            }
        }
    ) {
        Body(
            modifier = Modifier.fillMaxSize(),
            destination = currentDestination,
            navController = navController,
            onCreateItem = {
                navController.navigate(Destination.Add.path)
            },
            onNavigate = { destination ->
                navController.navigate(destination.path) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            orientation = orientation
        )
    }
}

@Preview
@Composable
fun Preview_Home() {
    MaterialTheme {
        Home(modifier = Modifier.fillMaxSize(), orientation = Configuration.ORIENTATION_LANDSCAPE)
    }
}