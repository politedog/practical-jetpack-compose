/*
 * Copyright 2021 Compose Academy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */package academy.compose.settings.ui

import academy.compose.settings.R
import academy.compose.settings.SettingsViewModel
import academy.compose.settings.model.MarketingOption
import academy.compose.settings.model.SettingsState
import academy.compose.settings.model.Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Settings() {
    val viewModel: SettingsViewModel = viewModel()

    MaterialTheme {
        val state = viewModel.uiState.collectAsState().value
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = state,
            toggleNotificationSetting = viewModel::toggleNotificationSetting,
            toggleHintsSetting = viewModel::toggleHintSetting,
            setMarketingOption = viewModel::setMarketingSetting,
            setSelectedTheme = viewModel::setTheme
        )
    }
}

@Composable
fun SettingsList(
    modifier: Modifier = Modifier,
    state: SettingsState,
    toggleNotificationSetting: () -> Unit,
    toggleHintsSetting: () -> Unit,
    setMarketingOption: (option: MarketingOption) -> Unit,
    setSelectedTheme: (theme: Theme) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(start = 12.dp)
        ) {
            Icon(
                tint = MaterialTheme.colors.onSurface,
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.cd_exit_settings)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                color = MaterialTheme.colors.onSurface,
                fontSize = 18.sp,
                text = stringResource(id = R.string.title_settings)
            )
        }

        NotificationSettings(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.notification_settings),
            checked = state.notificationsEnabled,
            onCheckedChanged = { toggleNotificationSetting() }
        )
        Divider()
        HintSettingsItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.hint_settings),
            checked = state.hintsEnabled,
            onCheckedChanged = { toggleHintsSetting() }
        )
        Divider()
        ManageSubscriptionSettingItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.subscription_settings),
            onSettingClicked = {
                // Handle Setting Click
            }
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        MarketingSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedOption = state.marketingOption,
            onOptionSelected = setMarketingOption
        )
        Divider()
        ThemeSettingItem(
            modifier = Modifier.fillMaxWidth(),
            selectedTheme = state.selectedTheme,
            onThemeSelected = setSelectedTheme
        )
        SectionSpacer(modifier = Modifier.fillMaxWidth())
        AppVersionSettingItem(appVersion = stringResource(id = R.string.app_version))
    }
}

@Preview
@Composable
fun Preview_SettingsList() {
    MaterialTheme {
        SettingsList(
            modifier = Modifier.fillMaxSize(),
            state = SettingsState(
                notificationsEnabled = true,
                marketingOption = MarketingOption.NOT_ALLOWED
            ),
            toggleNotificationSetting = { },
            toggleHintsSetting = { },
            setMarketingOption = { },
            setSelectedTheme = { }
        )
    }
}