package com.example.runningtracker.util

import com.google.android.material.textfield.TextInputEditText

fun TextInputEditText.isEmptyField() = text.toString().isEmpty()