package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest

@ExperimentalCoilApi
@Composable
fun GalleryImage(
    modifier: Modifier = Modifier,
    uri: Uri,
    scaleType: ContentScale
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_baseline_image_24),
        error = painterResource(id = R.drawable.ic_baseline_error_24),
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