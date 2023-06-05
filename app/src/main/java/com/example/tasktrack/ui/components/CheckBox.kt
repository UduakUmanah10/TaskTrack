package com.example.tasktrack.ui.components
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.example.tasktrack.R

@Composable
fun CheckBox(action:()->Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(dimensionResource(id = R.dimen.checkboxHeight))
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val initialText = "By clicking the Check Box You Accept Our "
        val privatePolicy = "Privacy Policy"
        val and = " And "
        val termsAndCondition = " Terms of Use "


        val annotatedString= buildAnnotatedString {
            append(initialText)
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)){
                pushStringAnnotation(tag = privatePolicy, annotation = privatePolicy)
                append(privatePolicy)
            }
            append(and)
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)){
                pushStringAnnotation(tag = termsAndCondition, annotation = termsAndCondition)
                append(termsAndCondition)
            }

        }

        Checkbox(checked = false, onCheckedChange ={ } )
        ClickableText(text =  annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if(span.item == privatePolicy ){
                        action()
                    }

                }

        })

    }
    

}
@Composable
fun SignupAnnotatedString(action:()->Unit){
    val initialText = stringResource(id = R.string.dont_have_an_account)
    val privatePolicy = stringResource(id = R.string.Click_toCreate_account)


    val annotatedString= buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)){
            pushStringAnnotation(tag = privatePolicy, annotation = privatePolicy)
            append(privatePolicy)
        }

    }
    ClickableText(text =  annotatedString, onClick = { offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also { span ->
                if(span.item == privatePolicy ){
                    action()
                }

            }

    })

}