package academy.compose.music.model

import androidx.compose.ui.graphics.Color

data class Track(
    val id: String,
    val title: String,
    val artist: String,
    val cover: Color,
    val length: Long,
    val isNew: Boolean,
    val isFeatured: Boolean
)