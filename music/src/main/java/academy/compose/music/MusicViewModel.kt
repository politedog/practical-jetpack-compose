package academy.compose.music

import academy.compose.music.model.MusicCatalogEvent
import academy.compose.music.model.MusicDashboardState
import academy.compose.music.model.NowPlayingState
import academy.compose.music.model.ResultStatus
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.*

class MusicViewModel : ViewModel() {

    private var nowPlayingFlow: Job? = null

    val uiState = MutableStateFlow(MusicDashboardState())

    override fun onCleared() {
        nowPlayingFlow?.cancel()
        super.onCleared()
    }

    fun handleEvent(contentEvent: MusicCatalogEvent) {
        when (contentEvent) {
            is MusicCatalogEvent.Search -> performSearch(contentEvent.searchTerm)
            MusicCatalogEvent.RefreshContent -> loadContent()
            MusicCatalogEvent.ToggleNowPlayingState -> {
                toggleNowPlayingState()
            }
            is MusicCatalogEvent.SeekTrack -> {
                uiState.value = uiState.value.copy(
                    nowPlaying = uiState.value.nowPlaying!!.copy(
                        position = contentEvent.position.toLong()
                    )
                )
            }
            MusicCatalogEvent.RewindNowPlaying -> rewindMusic()
            MusicCatalogEvent.FastForwardNowPlaying -> fastForwardMusic()
            MusicCatalogEvent.ClearSearchQuery -> {
                uiState.value = uiState.value.copy(
                    searchTerm = null
                )
            }
            is MusicCatalogEvent.PlayTrack -> {
                uiState.value = uiState.value.copy(
                    nowPlaying = ContentFactory.makeNowPlaying(contentEvent.track)
                )
                playMusic()
            }
        }
    }

    private fun performSearch(searchTerm: String) {
        val results = uiState.value.tracks!!.filter {
            it.title.contains(searchTerm, ignoreCase = true) ||
                it.artist.contains(searchTerm, ignoreCase = true)
        }
        uiState.value = uiState.value.copy(
            searchTerm = searchTerm,
            searchResults = results
        )
    }

    private fun rewindMusic() {
        val nowPlaying = uiState.value.nowPlaying!!
        val newPosition = nowPlaying.position - 10
        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                position = if (newPosition < 0) 0 else newPosition
            )
        )
    }

    private fun fastForwardMusic() {
        val nowPlaying = uiState.value.nowPlaying!!

        val maxLength = nowPlaying.track.length
        val newPosition = nowPlaying.position + 10
        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                position = if (newPosition > maxLength) maxLength else newPosition
            )
        )
    }

    private fun toggleNowPlayingState() {
        if (uiState.value.nowPlaying?.state == NowPlayingState.PLAYING) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    private fun pauseMusic() {
        nowPlayingFlow?.cancel()
        uiState.value = uiState.value.copy(
            nowPlaying = uiState.value.nowPlaying!!.copy(
                state = NowPlayingState.PAUSED
            )
        )
    }

    private fun playMusic() {
        nowPlayingFlow?.cancel()
        val nowPlaying = uiState.value.nowPlaying!!

        uiState.value = uiState.value.copy(
            nowPlaying = nowPlaying.copy(
                state = NowPlayingState.PLAYING
            )
        )
        nowPlayingFlow = viewModelScope.launch {
            while (isActive) {
                val maxLength = uiState.value.nowPlaying!!.track.length
                val newPosition = uiState.value.nowPlaying!!.position + 1
                if (newPosition >= maxLength) {
                    uiState.value = uiState.value.copy(
                        nowPlaying = nowPlaying.copy(
                            state = NowPlayingState.STOPPED,
                            position = 0
                        )
                    )
                    cancel()
                } else {
                    uiState.value = uiState.value.copy(
                        nowPlaying = nowPlaying.copy(
                            state = NowPlayingState.PLAYING,
                            position = newPosition
                        )
                    )
                }
                delay(1000)
            }
        }
    }

    private fun loadContent() {
        val data = ContentFactory.makeContentList()
        uiState.value = uiState.value.copy(
            status = ResultStatus.SUCCESS,
            tracks = data,
            nowPlaying = ContentFactory.makeNowPlaying(data[0])
        )
    }
}