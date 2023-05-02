package com.example.tasktrack.ui.components // ktlint-disable filename

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktrack.R
import com.example.tasktrack.ui.theme.OutlinedTextFieldShape
import com.example.tasktrack.ui.theme.TaskTrackTheme

/** this is a Custom text field implementation to ensure that it  implements
 * the track-app styling that we Expect. */

@Composable
fun TrackAppTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    errorMessage: String? = null,
    leadingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None


) {
    Column {
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.secondary,
                unfocusedBorderColor = MaterialTheme.colors.secondary,
                textColor = MaterialTheme.colors.secondary,
                focusedLabelColor = MaterialTheme.colors.secondary,
                unfocusedLabelColor = MaterialTheme.colors.secondary,
                cursorColor = MaterialTheme.colors.secondary
            ),
            value = text.toUpperCase(Locale.current),
            onValueChange = onTextChanged,
            label = { Text(text = labelText.toUpperCase(Locale.current)) },
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.textField_Height))
                .fillMaxWidth(),
            shape = OutlinedTextFieldShape,
            isError = (errorMessage != null),
            leadingIcon = leadingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)


        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 16.dp
                )
            )
        }
//
    }
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
                labelText = "user Name",
                leadingIcon = {}
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
        TrackAppTextField(
            text = "",
            onTextChanged = {},
            labelText = "user Name",
            leadingIcon = {}
        )
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
private fun ErrorFilledCustomTextField() {
    TaskTrackTheme {
        TrackAppTextField(
            text = "",
            onTextChanged = {},
            labelText = "user Name",
            errorMessage = "please Enter this",
            leadingIcon = {}
        )
    }
}
