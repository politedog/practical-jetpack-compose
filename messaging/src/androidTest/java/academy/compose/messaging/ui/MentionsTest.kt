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

import academy.compose.messaging.ui.input.Mentions
import academy.compose.messaging.ContactFactory
import academy.compose.messaging.Tags.TAG_CONTACTS
import academy.compose.messaging.util.stripMentionSymbol
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import java.util.*

@ExperimentalAnimationApi
class MentionsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Contacts_Displayed() {
        val contacts = ContactFactory.makeContacts()
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = "@",
                onMentionClicked = { _, _ ->

                }
            )
        }

        contacts.forEachIndexed { index, contact ->
            composeTestRule.onNodeWithTag(TAG_CONTACTS)
                .onChildAt(index)
                .assertTextEquals(contact.name)
        }
    }

    @Test
    fun Filtered_Contacts_Displayed() {
        val contacts = ContactFactory.makeContacts()
        val query = "@j"
        val mentions = contacts.filter {
            val withoutMentionSymbol =
                stripMentionSymbol(query.lowercase(Locale.getDefault())) ?: ""
            it.name.lowercase(Locale.getDefault()).startsWith(withoutMentionSymbol)
        }
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = query,
                onMentionClicked = { _, _ ->

                }
            )
        }

        mentions.forEachIndexed { index, contact ->
            composeTestRule.onNodeWithTag(TAG_CONTACTS)
                .onChildAt(index)
                .assertTextEquals(contact.name)
        }
    }

    @Test
    fun Contact_Triggers_Callback() {
        val onMentionClicked: (query: String, mention: String) -> Unit = mock()
        val contacts = ContactFactory.makeContacts()
        val query = "@"
        composeTestRule.setContent {
            Mentions(
                contacts = contacts,
                query = query,
                onMentionClicked = onMentionClicked
            )
        }

        composeTestRule.onNodeWithTag(TAG_CONTACTS)
            .onChildAt(1)
            .performClick()
        verify(onMentionClicked).invoke(query, "@" + contacts[1].name)
    }

}