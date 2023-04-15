package com.example.tasktrack.login
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.tasktrack.R
import com.example.tasktrack.login.domain.Credentials
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
    viewState: LogInViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit

) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Box {
            LogoInputColum(
                viewState,
                onUserNameChanged,
                onPasswordChanged,
                onLoginClicked,
                onSignupClicked
            )
            // CircularProgressIndicator(
            // modifier = Modifier
            //   .wrapContentSize()
            //     .align(Alignment.Center),
            // color = MaterialTheme.colors.secondary
            // )
        }
    }
}

@Composable
private fun LogoInputColum(
    viewState: LogInViewState,
    onUserNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.screen_padding))
            .fillMaxSize(),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        LoginAnimation()
        Spacer(modifier = Modifier.weight(1F))
        CircularProgressIndicator(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 10.dp, bottom = 10.dp)
                .align(Alignment.CenterHorizontally),
            color = MaterialTheme.colors.secondary
        )

        Email(
            text = viewState.Credentials.email.email,
            onEmailTextChanged = onUserNameChanged,
            errorMessage = (viewState as? LogInViewState.InputError)?.emailInputErrorMessage,
            leadingIcon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = stringResource(R.string.Email),
                    tint = MaterialTheme.colors.secondary
                )
            }

        )

        VerticalSpacer(height = 12.dp)

        Password(
            viewState.Credentials.password.password,
            onPasswordTextChanged = onPasswordChanged,
            errorMessage = (viewState as? LogInViewState.InputError)?.passwordInputErrorMessage,
            leadingIcon = {
                Icon(
                    Icons.Default.Lock,
                    contentDescription = stringResource(R.string.Password),
                    tint = MaterialTheme.colors.secondary
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        if (viewState is LogInViewState.SubmissionError) {
            Text(
                text = viewState.errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        VerticalSpacer(height = 48.dp)

        LoginButton(onClick = onLoginClicked)

        VerticalSpacer(height = 12.dp)

        SignUpButton(onSignupClicked = onSignupClicked)
    }
}

@Composable
private fun Password(
    text: String,
    onPasswordTextChanged: (String) -> Unit,
    errorMessage: String?,
    leadingIcon: (@Composable () -> Unit)? = null,
    visualTransformation: VisualTransformation
) {
    TrackAppTextField(
        text = text,
        onTextChanged = onPasswordTextChanged,
        labelText = stringResource(id = R.string.Password),
        errorMessage = errorMessage,
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation
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
    onEmailTextChanged: (String) -> Unit,
    errorMessage: String?,
    leadingIcon: (@Composable () -> Unit)? = null
) {
    TrackAppTextField(
        text = text,
        onTextChanged = onEmailTextChanged,
        labelText = stringResource(id = R.string.Email),
        errorMessage = errorMessage,
        leadingIcon = leadingIcon
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
private fun LoginPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    logInViewState: LogInViewState
) {
    val viewState = logInViewState
    TaskTrackTheme {
        LoginPage(
            viewState = viewState,
            {},
            {},
            {},
            {}
        )
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LogInViewState> {

    override val values: Sequence<LogInViewState>
        get() {
            val activeCredentials = Credentials(
                com.example.tasktrack.login.domain.Email("uduakumanah19@gmail.com"),
                com.example.tasktrack.login.domain.Password("12345")
            )
            return sequenceOf(
                LogInViewState.InitialLoginState,
                LogInViewState.Active(activeCredentials),
                LogInViewState.Submitting(activeCredentials),
                LogInViewState.SubmissionError(activeCredentials, "Something is wrong"),
                LogInViewState.InputError(
                    activeCredentials,
                    "Please Enter a valid Email",
                    "please enter a valid password"
                )

            )
        }
}
