package com.csp.library.models

data class SwatchModel(
    val colorName: String = "defaultColor",
    val colorValue: String = "#000000",
    var selected: Boolean = false
)