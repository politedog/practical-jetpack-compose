package academy.compose.gallery

import academy.compose.gallery.ui.Gallery
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import androidx.compose.foundation.ExperimentalFoundationApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PracticalJetpackComposeTheme {
                Gallery()
            }
        }
    }
}