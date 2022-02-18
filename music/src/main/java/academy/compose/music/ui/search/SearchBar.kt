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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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
    val inputHasFocus = remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    val onSurfaceWithAlpha = MaterialTheme.colors.onSurface.copy(alpha = 0.4f)
    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        TextField(
            modifier = modifier
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputHasFocus.value = it.hasFocus
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
                if (query.isNullOrEmpty() || !inputHasFocus.value) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.cd_search)
                    )
                } else {
                    IconButton(onClick = {
                        focusRequester.freeFocus()
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
        if (!inputHasFocus.value && query.isNullOrEmpty()) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                color = onSurfaceWithAlpha,
                text = stringResource(id = R.string.hint_search)
            )
        }
    }
}

@Preview
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