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
package academy.compose.music.ui.search

import academy.compose.music.R
import academy.compose.music.Tags.TAG_SEARCH_BAR
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String?,
    handleQuery: (query: String) -> Unit,
    clearQuery: () -> Unit
) {
    var inputHasFocus by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    val onSurfaceWithAlpha = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputHasFocus = it.hasFocus
                }
                .testTag(TAG_SEARCH_BAR),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = MaterialTheme.colors.onSurface.copy(
                    alpha = 0.6f
                ),
                unfocusedIndicatorColor = onSurfaceWithAlpha,
            ),
            singleLine = true,
            value = query ?: "",
            onValueChange = {
                handleQuery(it)
            },
            trailingIcon = {
                if (query.isNullOrEmpty() && !inputHasFocus) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.cd_search)
                    )
                } else {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                        clearQuery()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(id = R.string.cd_clear_query)
                        )
                    }
                }
            }
        )
        if (!inputHasFocus && query.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                color = onSurfaceWithAlpha,
                text = stringResource(id = R.string.hint_search)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_SearchBar() {
    MaterialTheme {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = "",
            handleQuery = {},
            clearQuery = {}
        )
    }
}