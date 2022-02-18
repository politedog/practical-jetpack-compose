package academy.compose.gallery.ui

import academy.compose.gallery.R
import academy.compose.gallery.Tags
import academy.compose.gallery.ui.theme.PracticalJetpackComposeTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DeniedPermission(
    modifier: Modifier = Modifier,
    handleLaunchSettings: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.permission_message),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = Modifier.testTag(Tags.TAG_PERMISSIONS_BUTTON),
            onClick = { handleLaunchSettings() }
        ) {
            Text(
                text = stringResource(id = R.string.launch_settings)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_DeniedPermission() {
    PracticalJetpackComposeTheme {
        DeniedPermission(
            modifier = Modifier.fillMaxWidth(),
            handleLaunchSettings = { }
        )
    }
}