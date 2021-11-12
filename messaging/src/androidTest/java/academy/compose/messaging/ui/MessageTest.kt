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

package academy.compose.messaging.ui

import academy.compose.messaging.R
import academy.compose.messaging.TestDataFactory
import academy.compose.messaging.TestDataFactory.randomString
import academy.compose.messaging.ui.message.Message
import academy.compose.messaging.Tags.TAG_IMAGE
import academy.compose.messaging.Tags.TAG_TEXT
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalAnimationApi
class MessageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Text_Body_Displayed() {
        composeTestRule.setContent {
            Message(message = TestDataFactory.makeMessage(
                text = randomString()
            ), onLongPress = { })
        }
        composeTestRule.onNodeWithTag(
            TAG_TEXT
        ).assertIsDisplayed()
    }

    @Test
    fun Image_Body_Never_Displayed() {
        composeTestRule.setContent {
            Message(message = TestDataFactory.makeMessage(
                text = randomString()
            ), onLongPress = { })
        }
        composeTestRule.onNodeWithTag(
            TAG_IMAGE
        ).assertDoesNotExist()
    }

    @Test
    fun Text_Content_Displayed() {
        val message = randomString()
        composeTestRule.setContent {
            Message(message = TestDataFactory.makeMessage(
                text = message
            ), onLongPress = { })
        }
        composeTestRule.onNodeWithTag(
            TAG_TEXT
        ).assertTextEquals(message)
    }

    @Test
    fun Image_Body_Displayed() {
        composeTestRule.setContent {
            Message(message = TestDataFactory.makeMessage(
                image = R.drawable.roxy
            ), onLongPress = { })
        }
        composeTestRule.onNodeWithTag(
            TAG_IMAGE
        ).assertIsDisplayed()
    }

    @Test
    fun Text_Body_Never_Displayed() {
        composeTestRule.setContent {
            Message(message = TestDataFactory.makeMessage(
                image = R.drawable.roxy
            ), onLongPress = { })
        }
        composeTestRule.onNodeWithTag(
            TAG_TEXT
        ).assertDoesNotExist()
    }

    @Test
    fun Long_Pressed_Triggered() {
        val parentTag = "parent"
        val onLongPress: (id: String) -> Unit = mock()
        val message = TestDataFactory.makeMessage(
            text = randomString()
        )
        composeTestRule.setContent {
            Message(
                modifier = Modifier.testTag(parentTag),
                message = message,
                onLongPress = onLongPress
            )
        }
        composeTestRule.onNodeWithTag(
            parentTag
        ).performTouchInput {
            longClick()
        }

        verify(onLongPress).invoke(message.id)
    }
}