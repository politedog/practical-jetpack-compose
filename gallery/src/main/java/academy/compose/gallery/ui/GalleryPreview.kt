package academy.compose.gallery.ui

import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GalleryPreview(
    modifier: Modifier = Modifier,
    image: Uri,
    onDismiss: () -> Unit
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    onDismiss()
                }
            )
    ) {
        GalleryImage(
            modifier = Modifier.fillMaxSize(),
            uri = image,
            scaleType = ContentScale.None
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_GalleryPreview() {
    PracticalJetpackComposeTheme {
        GalleryPreview(
            modifier = Modifier.fillMaxSize(),
            image = Uri.EMPTY,
            onDismiss = { }
        )
    }
}