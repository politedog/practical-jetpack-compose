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
package academy.compose.music.ui

import academy.compose.music.ContentFactory
import academy.compose.music.Tags
import academy.compose.music.model.MusicCatalogEvent
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.ui.player.MusicPlayer
import academy.compose.music.ui.search.SearchBar
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BackdropScaffold
import androidx.compose.material.BackdropValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberBackdropScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@ExperimentalSnapperApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun Dashboard(
    state: MusicDashboardState,
    handleEvent: (contentEvent: MusicCatalogEvent) -> Unit
) {
    val scaffoldState = rememberBackdropScaffoldState(
        initialValue = BackdropValue.Revealed
    )

    LaunchedEffect(Unit) {
        handleEvent(MusicCatalogEvent.RefreshContent)
    }

    BackdropScaffold(
        modifier = Modifier.testTag(Tags.TAG_DASHBOARD),
        headerHeight = 65.dp,
        peekHeight = 0.dp,
        gesturesEnabled = false,
        backLayerBackgroundColor = MaterialTheme.colors.surface,
        scaffoldState = scaffoldState,
        frontLayerShape = RectangleShape,
        appBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(Tags.TAG_SEARCH_BAR),
                query = state.searchTerm,
                handleQuery = {
                    handleEvent(MusicCatalogEvent.Search(it))
                },
                clearQuery = {
                    handleEvent(MusicCatalogEvent.ClearSearchQuery)
                }
            )
        },
        backLayerContent = {
            TracksDashboard(
                modifier = Modifier
                    .fillMaxSize(),
                state = state,
                onTrackClicked = { track ->
                    handleEvent(MusicCatalogEvent.PlayTrack(track))
                }
            )
        },
        frontLayerContent = {
            MusicPlayer(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState,
                state = state,
                handleEvent = handleEvent
            )
        },
        frontLayerScrimColor = Color.Unspecified
    )
}

@ExperimentalSnapperApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
fun Preview_MusicPlayer() {
    MaterialTheme {
        Dashboard(
            state = MusicDashboardState(
                tracks = ContentFactory.makeContentList()
            ),
            handleEvent = { }
        )
    }
}

@ExperimentalSnapperApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
fun Preview_MusicPlayer_Player() {
    MaterialTheme {
        Dashboard(
            state = MusicDashboardState(
                tracks = ContentFactory.makeContentList(),
                nowPlaying = ContentFactory.makeNowPlaying(ContentFactory.makeTrack())
            ),
            handleEvent = { }
        )
    }
}