package com.example.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.settings.R
import com.example.settings.model.MarketingOption
import kotlinx.coroutines.selects.select

@Composable
fun MarketingSettingItem (
    modifier: Modifier = Modifier,
    selectedOption: MarketingOption,
    onOptionSelected: (option: MarketingOption) -> Unit
) {
    val options = stringArrayResource(id =
    R.array.setting_options_marketing_choice)
    SettingItem(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(
                id = R.string.setting_option_marketing))
            Spacer(modifier = Modifier.height(8.dp))
            options.forEachIndexed { index, option ->
                Row(modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
                    .selectable (selected = selectedOption.id == index,
                        role = Role.RadioButton) {
                        val marketingOption = if (index ==
                            MarketingOption.ALLOWED.id
                        ) {
                            MarketingOption.ALLOWED
                        } else MarketingOption.NOT_ALLOWED
                        onOptionSelected(marketingOption)
                    }
                ) {
                    RadioButton(
                        selected = selectedOption.id == index,
                        onClick = null
                    )
                    Text (modifier = Modifier.padding(start = 18.dp),
                        text = option)
                }
            }
        }
    }
}

@Preview
@Composable
fun MarketingSettingItemPreview() {
    MarketingSettingItem(Modifier, MarketingOption.ALLOWED) { }
}