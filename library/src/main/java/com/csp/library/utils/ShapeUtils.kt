package com.csp.library.utils

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable


class ShapeUtils {
    companion object {

        fun getShape(
            bgShape: Int,
            strokeWidth: Int,
            color: String,
            strokeColor: String? = null
        ): Drawable {
            val shape = GradientDrawable()
            shape.shape = bgShape
            shape.setColor(Color.parseColor(color))
            strokeColor?.let { shape.setStroke(strokeWidth, Color.parseColor(strokeColor)) }
            return shape
        }

        fun getShape(bgShape: Int, strokeWidth: Int, color: String, strokeColor: Int): Drawable {
            val shape = GradientDrawable()
            shape.shape = bgShape
            shape.setColor(Color.parseColor(color))
            shape.setStroke(strokeWidth, strokeColor)
            return shape
        }
    }
}