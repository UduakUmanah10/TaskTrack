package com.example.tasktrack.signup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tasktrack.R
import com.example.tasktrack.ui.components.BoldTExtField
import com.example.tasktrack.ui.components.NormalTextField
import com.example.tasktrack.ui.components.TrackAppTextField

@Composable
fun signUpPage(){
    Surface( modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)
        .padding(dimensionResource(id = R.dimen.surface_padding)),
    ){
        Column(modifier = Modifier) {
            NormalTextField(textValue = stringResource(id = R.string.Helo))
            BoldTExtField(textValue = stringResource(id = R.string.create_account))
            TrackAppTextField(text = "ssss", onTextChanged ={} , labelText ="" )


          }
    }
    
    
}
@Preview
@Composable
fun sugnupPreview(){
    signUpPage()
}