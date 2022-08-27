package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.Tags.TAG_IMAGE
import academy.compose.gallery.Tags.TAG_IMAGE_GRID
import academy.compose.gallery.Tags.TAG_IMAGE_PREVIEW
import academy.compose.gallery.model.Image
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun ImageGallery(
    modifier: Modifier = Modifier,
    images: List<Image>
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    Box(
        contentAlignment = Alignment.Center
    ) {
        val enlargeImageLabel = stringResource(id = R.string.cd_enlarge_image)
        LazyVerticalGrid(
            modifier = modifier.testTag(TAG_IMAGE_GRID),
            columns = GridCells.Fixed(2)
        ) {
            items(images) {
                GalleryImage(
                    modifier = Modifier
                        .clickable(
                            onClickLabel = enlargeImageLabel
                        ) {
                            selectedImage = it.uri
                        }
                        .height(150.dp)
                        .fillMaxWidth()
                        .testTag(TAG_IMAGE + it.id),
                    uri = it.uri,
                    scaleType = ContentScale.None
                )
            }
        }
        AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = selectedImage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            selectedImage?.let { image ->
                GalleryPreview(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag(TAG_IMAGE_PREVIEW),
                    image = image,
                    onDismiss = {
                        selectedImage = null
                    }
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun Preview_ImageGallery() {
    PracticalJetpackComposeTheme {
        ImageGallery(
            modifier = Modifier.fillMaxWidth(),
            images = listOf(Image(id = 0L, uri = Uri.EMPTY, name = "image"), Image(id = 1L, uri = Uri.EMPTY, name = "image"))
        )
    }
}