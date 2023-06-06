package com.example.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.example.settings.R

@Composable
fun HintSettingsItem(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onShowHintsToggled: (checked: Boolean) -> Unit
) {
    val hintsEnabledState = if (checked) {
        stringResource(id = R.string.cd_hints_enabled)
    } else stringResource(id = R.string.cd_hints_disabled)
    SettingItem(modifier = modifier) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = checked,
                    onValueChange = onShowHintsToggled,
                    role = Role.Checkbox
                )
                .semantics {
                    stateDescription = hintsEnabledState
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, modifier = Modifier.weight(1f))
            Checkbox(checked = checked, onCheckedChange = null)
        }
    }
}