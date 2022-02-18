package academy.compose.gallery.model

import android.net.Uri

data class Image(
    val id: Long,
    val uri: Uri,
    val name: String
)