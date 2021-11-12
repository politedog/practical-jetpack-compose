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

package academy.compose.authentication

import academy.compose.authentication.model.PasswordRequirement
import academy.compose.authentication.ui.PasswordRequirements
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class PasswordRequirementsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Password_Requirements_Displayed_As_Not_Satisfied() {
        val requirements = PasswordRequirement.values().toList()
        val satisfiedRequirement = requirements[(0 until requirements.count()).random()]

        composeTestRule.setContent {
            PasswordRequirements(satisfiedRequirements = listOf(satisfiedRequirement))
        }

        PasswordRequirement.values().forEach { passwordRequirement ->
            val requirement = InstrumentationRegistry.getInstrumentation().targetContext
                .getString(passwordRequirement.label)
            val message = if (passwordRequirement == satisfiedRequirement) {
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.password_requirement_satisfied, requirement)
            } else {
                InstrumentationRegistry.getInstrumentation().targetContext
                    .getString(R.string.password_requirement_needed, requirement)
            }

            composeTestRule
                .onNodeWithText(message)
                .assertIsDisplayed()
        }
    }
}