package com.csp.library.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csp.library.R
import com.csp.library.callback.OnSwatchColorSelectedListener
import com.csp.library.models.SwatchModel
import com.csp.library.utils.ShapeUtils
import com.csp.library.view.SwatchView
import kotlinx.android.synthetic.main.item_swatch.view.*

class SwatchAdapter(
    private var mDataset: List<SwatchModel>,
    private var mSwatchConfig: SwatchView.SwatchConfig,
    private var mSwatchColorSelectionListner: OnSwatchColorSelectedListener
) : RecyclerView.Adapter<SwatchAdapter.SwatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SwatchViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_swatch,
            parent,
            false
        )
    )

    override fun getItemCount() = mDataset.size

    override fun onBindViewHolder(holder: SwatchViewHolder, position: Int) {
        var eachSwatch: SwatchModel = mDataset[position]

        holder.swatchView.rlContainer.background =
            if (eachSwatch.selected) mSwatchConfig.drawableSelected else null

        holder.swatchView.viewSwatch.background =
            ShapeUtils.getShape(
                mSwatchConfig.shapeType,
                mSwatchConfig.colorStrokeWidth,
                eachSwatch.colorValue
            )

        holder.swatchView.setOnClickListener {
            for (data in mDataset) {
                data.selected = eachSwatch.colorName == data.colorName
            }
            notifyDataSetChanged()
            mSwatchColorSelectionListner.onSwatchColorSelected(eachSwatch)
        }
    }


    inner class SwatchViewHolder(var swatchView: View) : RecyclerView.ViewHolder(swatchView)
}