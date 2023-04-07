package com.example.tasktrack.ui.loginPage
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktrack.ui.components.LoginAnimation
import com.example.tasktrack.ui.components.PrimaryButton
import com.example.tasktrack.ui.components.TrackAppTextField
import com.example.tasktrack.ui.theme.TaskTrackTheme

/**
 * This is a composable that maintains the the entire login Screen.
 * @param[viewState] the current state of what your screen renders
 * @param[]
 */

@Composable
fun LoginPage(
    viewState: LoginViewState
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1F))
            LoginAnimation()
            Spacer(modifier = Modifier.weight(1F))

            TrackAppTextField(text = "", onTextChanged = {}, labelText = "Email")
            Spacer(modifier = Modifier.height(12.dp))
            TrackAppTextField(text = "", onTextChanged = {}, labelText = "PassWord")
            Spacer(modifier = Modifier.height(48.dp))
            PrimaryButton(text = "Log In", Onclick = { /*TODO*/ })
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryButton(text = "Sign Up", Onclick = { /*TODO*/ })
        }
    }
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
        LoginPage(viewState = viewState)
    }
}
