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

import academy.compose.home.Tags.TAG_CONTENT_ICON
import academy.compose.home.Tags.TAG_CONTENT_TITLE
import academy.compose.home.model.Destination
import academy.compose.home.ui.ContentArea
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ContentAreaTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Destination_Displayed() {
        val destination = Destination.Feed
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(destination.path)
            .assertIsDisplayed()
    }

    @Test
    fun Content_Title_Displayed() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_TITLE)
            .assert(hasText(destination.title))
    }

    @Test
    fun Content_Icon_Displayed() {
        val destination = Destination.Contacts
        composeTestRule.setContent {
            ContentArea(destination = destination)
        }
        composeTestRule.onNodeWithTag(TAG_CONTENT_ICON)
            .assertContentDescriptionEquals(destination.title)
    }
}