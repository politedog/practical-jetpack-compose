package academy.compose.music.ui

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
        peekHeight = 0.dp,
        headerHeight = 65.dp,
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
                    .fillMaxSize()
                    .testTag(Tags.TAG_DASHBOARD),
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

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Preview
@Composable
fun Preview_MusicPlayer() {
    MaterialTheme {
        Dashboard(
            state = MusicDashboardState(),
            handleEvent = { }
        )
    }
}