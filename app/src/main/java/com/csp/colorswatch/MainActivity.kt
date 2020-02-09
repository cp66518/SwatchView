package com.csp.colorswatch

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.csp.library.callback.OnSwatchColorSelectedListener
import com.csp.library.models.SwatchModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var swatchData = ArrayList<SwatchModel>()

        swatchData.add(SwatchModel("color 1", "#f44336"))
        swatchData.add(SwatchModel("color 2", "#e91e63"))
        swatchData.add(SwatchModel("color 3", "#9c27b0"))
        swatchData.add(SwatchModel("color 4", "#673ab7"))
        swatchData.add(SwatchModel("color 5", "#3f51b5"))
        swatchData.add(SwatchModel("color 6", "#2196f3"))
        swatchData.add(SwatchModel("color 7", "#03a9f4"))
        swatchData.add(SwatchModel("color 8", "#00bcd4"))
        swatchData.add(SwatchModel("color 9", "#009688"))
        swatchData.add(SwatchModel("color 10", "#4caf50"))
        swatchData.add(SwatchModel("color 11", "#8bc34a"))
        swatchData.add(SwatchModel("color 12", "#cddc39"))
        swatchData.add(SwatchModel("color 13", "#ffeb3b"))
        swatchData.add(SwatchModel("color 14", "#ffc107"))
        swatchData.add(SwatchModel("color 15", "#ff9800"))
        swatchView.loadSwatchData(swatchData)

        // setting up the custom font
        var typeface = Typeface.createFromAsset(assets, "fonts/roboto_slab.ttf")
        swatchView.setColorLabelFont(typeface)

        var selectedColorTypeface = Typeface.createFromAsset(assets, "fonts/roboto_bold.ttf")
        swatchView.setSelectedColorLabelFont(selectedColorTypeface)

        // registering listener
        swatchView.setOnSwatchColorSelectedListener(object :
            OnSwatchColorSelectedListener {
            override fun onSwatchColorSelected(selectedColor: SwatchModel) {
                // todo do extra
            }
        })

        swatchView2.loadSwatchData(swatchData)
        swatchView3.loadSwatchData(swatchData)
    }
}
