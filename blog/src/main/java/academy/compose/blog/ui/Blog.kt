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

package academy.compose.blog.ui

import academy.compose.blog.model.Post
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val KEY_LIST = "KEY_LIST"
private const val KEY_POST = "KEY_POST"
private const val ARG_INDEX = "ARG_INDEX"

@ExperimentalComposeUiApi
@ExperimentalMotionApi
@Composable
fun Blog(
    modifier: Modifier = Modifier,
    posts: List<Post>
) {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(modifier = modifier, navController = navController, startDestination = KEY_LIST) {
            composable(KEY_LIST) {
                BlogList(
                    modifier = Modifier.fillMaxSize(),
                    posts = posts,
                    onPostSelected = { index ->
                        navController.navigate("$KEY_POST/$index")
                    }
                )
            }
            composable(
                "$KEY_POST/{$ARG_INDEX}",
                arguments = listOf(navArgument(ARG_INDEX) { type = NavType.IntType })
            ) { backStackEntry ->
                PostDetail(
                    modifier = Modifier.fillMaxWidth(),
                    post = posts[backStackEntry.arguments?.getInt(ARG_INDEX, 0) ?: 0],
                    handleNavigateUp = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

