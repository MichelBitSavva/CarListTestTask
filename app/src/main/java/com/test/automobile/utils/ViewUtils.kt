package com.test.automobile.utils

import android.text.Editable
import android.view.View
import android.widget.*


fun ProgressBar.show() {
    visibility = View.VISIBLE
}

fun ProgressBar.hide() {
    visibility = View.INVISIBLE
}

fun ProgressBar.gone() {
    visibility = View.GONE
}

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)






