package com.example.tasktrack.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasktrack.R
import com.example.tasktrack.ui.theme.OutlinedTextFieldShape
import com.example.tasktrack.ui.theme.TaskTrackTheme
import java.util.*

/** this is a Custom text field implementation to ensure that it  implements
 * the track-app styling that we Expect. */

@Composable
fun TrackAppTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String

) {
    OutlinedTextField(

        value = text.toUpperCase(Locale.current),
        onValueChange = onTextChanged,
        label = { Text(text = labelText.toUpperCase(Locale.current)) },
        modifier = modifier
            .heightIn(dimensionResource(id = R.dimen.textField_Height))
            .fillMaxWidth(),
        shape = OutlinedTextFieldShape

    )
}

@Preview(
    name = "Night Mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day mode - Filled",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)

@Composable
@Suppress("UnusedPrivateMember ")
private fun FilledCustomTextField() {
    TaskTrackTheme {
        Surface() {
            TrackAppTextField(
                text = "Toa Text fiels",
                onTextChanged = {},
                labelText = "user Name"
            )
        }
    }
}

@Preview(
    name = "Night Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)

@Composable
@Suppress("UnusedPrivateMember ")
private fun EmptyFilledCustomTextField() {
    TaskTrackTheme {
        Surface() {
            TrackAppTextField(
                text = "",
                onTextChanged = {},
                labelText = "user Name"
            )
        }
    }
}
