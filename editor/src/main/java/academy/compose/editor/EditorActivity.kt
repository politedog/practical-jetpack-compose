package academy.compose.editor

import academy.compose.editor.ui.Stories
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
class EditorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Stories()
        }
    }
}
