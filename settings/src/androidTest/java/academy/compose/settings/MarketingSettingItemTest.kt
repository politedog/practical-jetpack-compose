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

import academy.compose.settings.model.MarketingOption
import academy.compose.settings.ui.MarketingSettingItem
import academy.compose.settings.Tags.TAG_MARKETING_OPTION
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class MarketingSettingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Marketing_Option_Selected() {
        val option = MarketingOption.NOT_ALLOWED

        composeTestRule.setContent {
            MarketingSettingItem(selectedOption = option, onOptionSelected = { })
        }

        composeTestRule.onNodeWithTag(TAG_MARKETING_OPTION + option.id)
            .assertIsSelected()
    }
}