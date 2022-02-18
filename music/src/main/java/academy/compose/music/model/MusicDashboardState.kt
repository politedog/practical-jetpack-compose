package academy.compose.music.model

data class MusicDashboardState(
    val status: ResultStatus = ResultStatus.LOADING,
    val tracks: List<Track>? = null,
    val nowPlaying: NowPlaying? = null,
    val searchTerm: String? = null,
    val searchResults: List<Track>? = null
) {

    val newTracks = tracks?.filter { it.isNew }

    val featuredTracks = tracks?.filter { it.isFeatured }

    val recentTracks = tracks?.filter { !it.isFeatured && !it.isNew }

}