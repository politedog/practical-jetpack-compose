package com.example.settings.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Surface
import androidx.compose.material.Switch
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
fun NotificationSettings(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChanged: (checked: Boolean) -> Unit
) {
    val notificationsEnabledState = if (checked) {
        stringResource(id = R.string.cd_notifications_enabled)
    } else {
        stringResource(id = R.string.cd_notifications_disabled)
    }
    SettingItem( modifier = modifier ){
        Row(modifier = Modifier
            .toggleable(
                value = checked,
                onValueChange = onCheckedChanged,
                role = Role.Switch
            )
            .semantics { stateDescription = notificationsEnabledState }
            .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text( text = title, modifier = Modifier.weight(1f) )
            Switch(checked = checked, onCheckedChange = null)
        }

    }
}