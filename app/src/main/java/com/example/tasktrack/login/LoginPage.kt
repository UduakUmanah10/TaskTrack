package com.example.tasktrack.login
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktrack.R
import com.example.tasktrack.ui.components.LoginAnimation
import com.example.tasktrack.ui.components.PrimaryButton
import com.example.tasktrack.ui.components.TrackAppTextField
import com.example.tasktrack.ui.core.VerticalSpacer
import com.example.tasktrack.ui.theme.TaskTrackTheme

/**
 * This is a composable that maintains the the entire login Screen.
 * @param[viewState] the current state of what your screen renders
 * @param[onLoginClicked] a call back that is invoked when the user clicks the login screen
 * @param[onUserNameChanged]a call back that is invoked when the text field of user name changes
 * @param[onSignupClicked] a call back that is invoked when the the sign ip button is clicked
 * @param[onPasswordChanged] a call back that is invoked when there is changes in the pass word text field
 * */

@Composable
fun LoginPage(
    viewState: LoginViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit

) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(dimensionResource(id = R.dimen.screen_padding))
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))

            LoginAnimation()

            Spacer(modifier = Modifier.weight(1F))

            Email(
                text = viewState.userName,
                onEmailTextChanged = onUserNameChanged

            )

            VerticalSpacer(height = 12.dp)

            Password(
                viewState.password,
                onPasswordTextChanged = onPasswordChanged
            )

            VerticalSpacer(height = 48.dp)

            LoginButton(onClick = onLoginClicked)

            VerticalSpacer(height = 12.dp)

            SignUpButton(onSignupClicked = onSignupClicked)
        }
    }
}

@Composable
private fun Password(
    text: String,
    onPasswordTextChanged: (String) -> Unit
) {
    TrackAppTextField(
        text = text,
        onTextChanged = onPasswordTextChanged,
        labelText = stringResource(id = R.string.Password)
    )
}

@Composable
private fun SignUpButton(onSignupClicked: () -> Unit) {
    PrimaryButton(
        text = stringResource(id = R.string.sign_up),
        Onclick = onSignupClicked
    )
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(
        text = stringResource(id = R.string.Log_in),
        Onclick = onClick
    )
}

@Composable
private fun Email(
    text: String,
    onEmailTextChanged: (String) -> Unit
) {
    TrackAppTextField(
        text = text,
        onTextChanged = onEmailTextChanged,
        labelText = stringResource(id = R.string.Email)
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Suppress("UnusedPrivateMember")
@Composable
private fun LoginPreview() {
    val viewState = LoginViewState("", "")
    TaskTrackTheme {
        LoginPage(
            viewState = viewState,
            {}, {}, {},
            {}
        )
    }
}
