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

package academy.compose.home

import academy.compose.home.model.Destination
import academy.compose.home.ui.Navigation
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get: Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Feed_Displayed_By_Default() {
        composeTestRule.setContent {
            Navigation(navController = rememberNavController())
        }
        composeTestRule.onNodeWithTag(Destination.Feed.path)
            .assertIsDisplayed()
    }

    @Test
    fun Contacts_Displayed() {
        assertNavigation(Destination.Contacts)
    }

    @Test
    fun Calendar_Displayed() {
        assertNavigation(Destination.Calendar)
    }

    @Test
    fun Create_Displayed() {
        assertNavigation(Destination.Add)
    }

    @Test
    fun Upgrade_Displayed() {
        assertNavigation(Destination.Upgrade)
    }

    @Test
    fun Settings_Displayed() {
        assertNavigation(Destination.Settings)
    }

    private fun assertNavigation(destination: Destination) {
        composeTestRule.setContent {
            val navController = rememberNavController()
            Navigation(navController = navController)
            navController.navigate(destination.path)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }
}
