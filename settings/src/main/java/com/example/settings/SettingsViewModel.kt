package com.example.settings

import androidx.lifecycle.ViewModel
import com.example.settings.model.MarketingOption
import com.example.settings.model.SettingsState
import com.example.settings.model.Theme
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel: ViewModel() {
    val uiState = MutableStateFlow(SettingsState())

    fun toggleNotificationSettings() {
        uiState.value = uiState.value.copy(notificationsEnabled = !uiState.value.notificationsEnabled)
    }

    fun toggleHintSettings(newValue: Boolean) {
        uiState.value = uiState.value.copy(hintsEnabled = newValue)
    }

    fun setMarketingSettings(option: MarketingOption) {
        uiState.value = uiState.value.copy(marketingOption = option)
    }

    fun setTheme(theme: Theme) {
        uiState.value = uiState.value.copy(theme = theme)
    }
}