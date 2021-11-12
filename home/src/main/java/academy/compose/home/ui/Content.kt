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

package academy.compose.home.ui

import academy.compose.home.Tags.TAG_CONTENT_ICON
import academy.compose.home.Tags.TAG_CONTENT_TITLE
import academy.compose.home.model.Destination
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentArea(
    modifier: Modifier = Modifier,
    destination: Destination
) {
    Column(
        modifier = modifier.testTag(destination.path),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        destination.icon?.let { icon ->
            Icon(
                modifier = Modifier.size(80.dp).testTag(TAG_CONTENT_ICON),
                imageVector = icon, 
                contentDescription = destination.title
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            modifier = Modifier.testTag(TAG_CONTENT_TITLE),
            fontSize = 18.sp,
            text = destination.title
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_ContentArea() {
    MaterialTheme {
        ContentArea(
            modifier = Modifier.fillMaxSize(),
            destination = Destination.Contacts
        )
    }
}
