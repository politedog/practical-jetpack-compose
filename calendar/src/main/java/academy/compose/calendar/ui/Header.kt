package academy.compose.calendar.ui

import academy.compose.calendar.R
import academy.compose.calendar.Tags.TAG_MONTH_HEADER
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    previousMonth: () -> Unit,
    nextMonth: () -> Unit
) {
    Row(modifier = modifier.padding(16.dp)) {
        Icon(
            modifier = Modifier.clickable {
                previousMonth()
            },
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.cd_previous_month)
        )
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .testTag(TAG_MONTH_HEADER),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Icon(
            modifier = Modifier.clickable {
                nextMonth()
            },
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.cd_next_month)
        )
    }
}