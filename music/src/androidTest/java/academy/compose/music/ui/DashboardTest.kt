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
package academy.compose.music.ui

import academy.compose.music.Tags.TAG_DASHBOARD
import academy.compose.music.Tags.TAG_DISMISS_PLAYER
import academy.compose.music.Tags.TAG_PLAYER
import academy.compose.music.Tags.TAG_PLAYER_BAR
import academy.compose.music.Tags.TAG_SEARCH_BAR
import academy.compose.music.TestDataFactory
import academy.compose.music.model.MusicDashboardState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
class DashboardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Content_Area_Displayed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_DASHBOARD)
            .assertIsDisplayed()
    }

    @Test
    fun Search_Bar_Displayed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_SEARCH_BAR)
            .assertIsDisplayed()
    }

    @Test
    fun Full_Screen_Player_Revealed() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()
    }

    @Test
    fun Player_Dismissed_Hides_Player() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            TAG_DISMISS_PLAYER
        ).performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertDoesNotExist()
    }

    @Test
    fun Player_Dismissed_Shows_Player_Bar() {
        composeTestRule.setContent {
            Dashboard(
                state = MusicDashboardState(
                    nowPlaying = TestDataFactory.makeNowPlaying()
                ),
                handleEvent = { }
            )
        }

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TAG_DISMISS_PLAYER)
            .performClick()

        composeTestRule.onNodeWithTag(TAG_PLAYER_BAR)
            .assertIsDisplayed()
    }
}