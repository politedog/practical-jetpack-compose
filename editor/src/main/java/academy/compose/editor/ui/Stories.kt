package academy.compose.editor.ui

import academy.compose.editor.EditorViewModel
import academy.compose.editor.StoryCreator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Stories() {
    val viewModel = viewModel<EditorViewModel>()
    MaterialTheme {
        StoryCreator(
            state = viewModel.uiState.collectAsState().value,
            handleEvent = viewModel::handleEvent
        )
    }
}