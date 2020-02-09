package com.csp.library.callback

import com.csp.library.models.SwatchModel

interface OnSwatchColorSelectedListener {
    fun onSwatchColorSelected(selectedColor: SwatchModel)
}