package com.example.tasktrack.signup.ui

import TaskTrackTheme
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tasktrack.R
import com.example.tasktrack.login.Email
import com.example.tasktrack.login.Password
import com.example.tasktrack.login.SignUpButton
import com.example.tasktrack.ui.components.BoldTExtField
import com.example.tasktrack.ui.components.NormalTextField
import com.example.tasktrack.ui.components.TrackAppTextField
import com.example.tasktrack.ui.components.CheckBox

@Composable
fun signUpPage(){
    Surface( modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(dimensionResource(id = R.dimen.surface_padding)),
    ){
        Column(modifier = Modifier) {
            NormalTextField(textValue = stringResource(id = R.string.Helo))
            BoldTExtField(textValue = stringResource(id = R.string.create_account))
            TrackAppTextField(
                text = "",
                onTextChanged ={}
                , labelText = stringResource(id = R.string.firstname),
                leadingIcon = { Icon(Icons.Rounded.Person, stringResource(id = R.string.profile)) }
            )
            TrackAppTextField(
                text = "",
                onTextChanged ={}
                , labelText = stringResource(id = R.string.lastName),
                leadingIcon = { Icon(Icons.Rounded.Person, stringResource(id = R.string.profile)) }
            )
            Email(
                enabled = true,
                text = "",
                onEmailTextChanged = {},
                errorMessage = null,
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Email,
                        contentDescription = stringResource(R.string.Email),
                    )
                }
            )
            Password(
                text = "",
                enabled = true,
                onPasswordTextChanged = {},
                errorMessage = null,
                leadingIcon = {
                    Icon(
                        Icons.Rounded.Lock,
                        contentDescription = stringResource(R.string.Password),
                        tint = androidx.compose.material.MaterialTheme.colors.secondary
                    )
                },
                visualTransformation = PasswordVisualTransformation('*')
            )
            CheckBox(){

            }
            Spacer(modifier = Modifier.padding(20.dp))

            SignUpButton(onSignupClicked = {})
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.already_have_an_account))
                
            }


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
@Composable
fun SugnupPreview(){
    TaskTrackTheme() {
    signUpPage()
    }
}