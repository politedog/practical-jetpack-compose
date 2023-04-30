package com.example.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.settings.R
import com.example.settings.model.SettingsState

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        TopAppBar() {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_go_back)
            )

        }
    }

}