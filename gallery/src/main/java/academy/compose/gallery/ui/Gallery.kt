package academy.compose.gallery.ui

import academy.compose.gallery.model.Image
import academy.compose.gallery.retrieveMedia
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@ExperimentalFoundationApi
@ExperimentalPermissionsApi
@Composable
fun Gallery() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var retrievedMedia by remember { mutableStateOf<List<Image>?>(null) }
    val permissionState = if (
        android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU
    ) {
        rememberPermissionState(Manifest.permission.READ_MEDIA_IMAGES)
    } else {
        rememberPermissionState(
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
    LaunchedEffect(key1 = permissionState.status) {
        if (permissionState.status == PermissionStatus.Granted) {
            scope.launch(Dispatchers.IO) {
                val retrieveMedia = retrieveMedia(context)
                withContext(Dispatchers.Main) {
                    retrievedMedia = retrieveMedia
                }
            }
        }
    }
    MaterialTheme {
        GalleryContent(
            modifier = Modifier.fillMaxSize(),
            media = retrievedMedia,
            permissionState = permissionState,
            openSettings = {
                context.startActivity(
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:${context.packageName}")
                    )
                )
            }
        )
    }
}

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@Preview
@Composable
fun Preview_Gallery() {
    PracticalJetpackComposeTheme {
        Gallery()
    }
}
