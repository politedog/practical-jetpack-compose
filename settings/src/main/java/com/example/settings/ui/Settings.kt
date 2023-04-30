package com.example.settings.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.settings.SettingsViewModel

@Composable
fun Settings () {
    val viewModel: SettingsViewModel = viewModel()
    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = state)
    }
}

