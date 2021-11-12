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

package academy.compose.settings

import academy.compose.settings.model.Theme
import academy.compose.settings.Tags.TAG_SELECT_THEME
import academy.compose.settings.Tags.TAG_THEME
import academy.compose.settings.Tags.TAG_THEME_OPTION
import academy.compose.settings.ui.ThemeSettingItem
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class ThemeSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Selected_Theme_Displayed() {
        val option = Theme.DARK

        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = option, onThemeSelected = {})
        }

        composeTestRule.onNodeWithTag(TAG_THEME, useUnmergedTree = true)
            .assertTextEquals(
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(option.label)
            )
    }

    @Test
    fun Theme_Options_Displayed() {
        composeTestRule.setContent {
            ThemeSettingItem(selectedTheme = Theme.DARK, onThemeSelected = {})
        }

        composeTestRule.onNodeWithTag(TAG_SELECT_THEME)
            .performClick()

        Theme.values().forEach { theme ->
            composeTestRule.onNodeWithTag(
                TAG_THEME_OPTION + InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(theme.label)
            ).assertIsDisplayed()
        }
    }
}