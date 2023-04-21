package com.example.tasktrack.ui.components

import android.content.Context
import androidx.annotation.StringRes
/*
* this is a sealed class that contains all the possible ways text can be displayed on the UI
* */

sealed class UIText {

    data class StringText(val value: String) : UIText()
    data class ResourceStringText(@StringRes val value: Int) : UIText()
}

/**
 * * this is an extension function that checks the a string if it is
 * an ordinary string or it is a string resource and returns an ordinary string
 * @param[context] gets the actual string resource from the resource value
 */

fun UIText.getString(context: Context): String {
    return when (this) {
        is UIText.StringText -> this.value
        is UIText.ResourceStringText -> context.getString(this.value)
    }
}
