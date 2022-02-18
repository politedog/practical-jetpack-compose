package academy.compose.music.model

sealed class MusicCatalogEvent {

    object RefreshContent: MusicCatalogEvent()

    class PlayTrack(val track: Track): MusicCatalogEvent()

    class SeekTrack(val position: Float): MusicCatalogEvent()

    object RewindNowPlaying: MusicCatalogEvent()

    object ToggleNowPlayingState: MusicCatalogEvent()

    object FastForwardNowPlaying: MusicCatalogEvent()

    class Search(val searchTerm: String): MusicCatalogEvent()

    object ClearSearchQuery: MusicCatalogEvent()

}