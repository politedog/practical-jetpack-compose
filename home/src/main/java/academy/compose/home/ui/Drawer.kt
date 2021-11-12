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

import academy.compose.home.R
import academy.compose.home.model.Destination
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnScope.DrawerContent(
    onNavigate: (destination: Destination) -> Unit,
    onLogout: () -> Unit
) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.label_name),
        fontSize = 20.sp
    )
    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = stringResource(id = R.string.label_email),
        fontSize = 16.sp
    )
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )

    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Upgrade.path,
        onClick = {
            onNavigate(Destination.Upgrade)
        }
    )
    Spacer(modifier = Modifier.height(8.dp))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = Destination.Settings.path,
        onClick = {
            onNavigate(Destination.Settings)
        }
    )
    Spacer(modifier = Modifier.weight(1f))
    DrawerItem(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.log_out),
        onClick = {
            onLogout()
        }
    )
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable {
                onClick()
            }
            .padding(16.dp),
        text = label.replaceFirstChar {
            it.titlecase()
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_DrawerContent() {
    MaterialTheme {
        Column {
            DrawerContent(
                onNavigate = { },
                onLogout = { }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DrawerItem() {
    DrawerItem(label = "Upgrade") {

    }
}