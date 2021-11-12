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

package academy.compose.messaging.ui.input

import academy.compose.messaging.R
import academy.compose.messaging.model.Contact
import academy.compose.messaging.ContactFactory
import academy.compose.messaging.Tags.TAG_CONTACT
import academy.compose.messaging.Tags.TAG_CONTACTS
import academy.compose.messaging.util.stripMentionSymbol
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Mentions(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    query: String?,
    onMentionClicked: (query: String, mention: String) -> Unit
) {
    val mentions = contacts.filter {
        val withoutMentionSymbol = stripMentionSymbol(query?.lowercase())
        it.name.lowercase().startsWith(withoutMentionSymbol)
    }
    Column(
        modifier = modifier
    ) {
        Divider()
        LazyRow(
            modifier = Modifier.testTag(TAG_CONTACTS)
        ) {
            items(mentions) { contact ->
                val clickLabel = stringResource(id = R.string.cd_mention_contact, contact.name)
                Text(text = contact.name, modifier = Modifier
                    .clickable(onClickLabel = clickLabel) {
                        onMentionClicked(query!!, "@${contact.name}")
                    }
                    .padding(16.dp)
                    .testTag(TAG_CONTACT + contact.name)
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview_Mentions() {
    MaterialTheme {
        Mentions(
            modifier = Modifier.fillMaxWidth(),
            contacts = ContactFactory.makeContacts(),
            query = "Jo",
            onMentionClicked = { mention, m -> }
        )
    }
}