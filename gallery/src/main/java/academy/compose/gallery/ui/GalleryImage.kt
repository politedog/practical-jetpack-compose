package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun GalleryImage(
    modifier: Modifier = Modifier,
    uri: Uri,
    scaleType: ContentScale
) {
    Image(
        painter = rememberImagePainter(
            data = uri,
            builder = {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
                error(R.drawable.ic_baseline_error_24)
            }
        ),
        contentDescription = null,
        contentScale = scaleType,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Preview_GalleryImage() {
    PracticalJetpackComposeTheme {
        GalleryImage(
            modifier = Modifier.fillMaxSize(),
            uri = Uri.EMPTY,
            scaleType = ContentScale.Crop
        )
    }
}