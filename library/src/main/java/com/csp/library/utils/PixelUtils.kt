package com.csp.library.utils

import android.content.Context

class PixelUtils {
    companion object {

        @JvmStatic
        fun convertPixelToSP(context: Context, pixel: Float): Float {
            val sp: Float = pixel / context.resources.displayMetrics.scaledDensity
            return sp
        }
    }
}