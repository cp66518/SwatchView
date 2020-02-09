package com.csp.library.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.csp.library.R
import com.csp.library.adapter.SwatchAdapter
import com.csp.library.callback.OnSwatchColorSelectedListener
import com.csp.library.models.SwatchModel
import com.csp.library.utils.PixelUtils
import com.csp.library.utils.ShapeUtils
import kotlinx.android.synthetic.main.layout_swatch.view.*


const val DEFAULT_DRAWABLE_SELECTED_WIDTH = 1
const val DEFAULT_DRAWABLE_WIDTH = 1
const val DEFAULT_BG_COLOR = android.R.color.white
const val DEFAULT_COLOR_LABEL_ALIGNMENT = 0

class SwatchView @JvmOverloads constructor(
    private var mContext: Context,
    private var mAttributeSet: AttributeSet? = null,
    private var mDefStyle: Int? = 0
) : LinearLayout(mContext, mAttributeSet) {

    private var mSwatchConfig: SwatchConfig = SwatchConfig()

    private lateinit var onSwatchColorSelectedListener: OnSwatchColorSelectedListener


    init {
        initView()
    }


    private fun initView() {
        View.inflate(mContext, R.layout.layout_swatch, this)
        var attrSet =
            context.obtainStyledAttributes(mAttributeSet, R.styleable.SwatchView, mDefStyle!!, 0)
        for (i in 0..attrSet.indexCount) {
            val index = attrSet.getIndex(i)
            when (index) {
                R.styleable.SwatchView_shape -> {
                    var shapeType = attrSet.getInt(index, GradientDrawable.RECTANGLE)
                    mSwatchConfig.shapeType = when (shapeType) {
                        0 -> GradientDrawable.RECTANGLE
                        1 -> GradientDrawable.OVAL
                        else -> {
                            GradientDrawable.RECTANGLE
                        }
                    }
                }
                R.styleable.SwatchView_selected_stroke_color -> {
                    mSwatchConfig.selectedStrokeColor = attrSet.getColor(index, 0)
                }
                R.styleable.SwatchView_selected_stroke_width -> {
                    mSwatchConfig.selectedStrokeWidth =
                        attrSet.getInt(index, DEFAULT_DRAWABLE_SELECTED_WIDTH)
                }
                R.styleable.SwatchView_color_stroke_width -> {
                    mSwatchConfig.colorStrokeWidth =
                        attrSet.getInt(index, DEFAULT_DRAWABLE_WIDTH)
                }
                R.styleable.SwatchView_swatch_background_color -> {
                    mSwatchConfig.backgroundColor =
                        attrSet.getInt(index, DEFAULT_BG_COLOR)
                }
                R.styleable.SwatchView_label_alignment -> {
                    mSwatchConfig.colorLabelAlignment =
                        attrSet.getInt(index, DEFAULT_COLOR_LABEL_ALIGNMENT)
                }
                R.styleable.SwatchView_label_color_text_size -> {
                    var textSize = attrSet.getDimensionPixelSize(index, 0).toFloat()
                    tvTitle.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        PixelUtils.convertPixelToSP(context, textSize)
                    )
                }
                R.styleable.SwatchView_label_color_text_color -> {
                    tvTitle.setTextColor(attrSet.getInt(index, Color.BLACK))
                }
                R.styleable.SwatchView_label_selected_color_text_color -> {
                    tvSelectedColorName.setTextColor(attrSet.getInt(index, Color.BLACK))
                }
                R.styleable.SwatchView_label_selected_color_text_size -> {
                    var textSizePx = attrSet.getDimensionPixelSize(index, 0).toFloat()
                    tvSelectedColorName.setTextSize(
                        TypedValue.COMPLEX_UNIT_SP,
                        PixelUtils.convertPixelToSP(context, textSizePx)
                    )
                }
                R.styleable.SwatchView_no_color_placeholder -> {
                    var noSlectionPlaceholder = attrSet.getString(index)
                    tvSelectedColorName.text = noSlectionPlaceholder
                }
                R.styleable.SwatchView_label_color_text -> {
                    var text = attrSet.getString(index)
                    tvTitle.text = text ?: "Color:"
                }
            }
        }
        attrSet.recycle()

    }


    @SuppressWarnings("all")
    fun loadSwatchData(dataset: List<SwatchModel>) {
        if (dataset.isNotEmpty()) {
            llSWatchContainer.setBackgroundColor(mSwatchConfig.backgroundColor)
            setSwatchLabelAlignment(mSwatchConfig.colorLabelAlignment)

            var llm = LinearLayoutManager(context)
                .apply { orientation = LinearLayoutManager.HORIZONTAL }
            rvSWatch.layoutManager = llm
            rvSWatch.adapter =
                SwatchAdapter(dataset, mSwatchConfig, object :
                    OnSwatchColorSelectedListener {
                    override fun onSwatchColorSelected(selectedColor: SwatchModel) {
                        if (::onSwatchColorSelectedListener.isInitialized) {
                            onSwatchColorSelectedListener.onSwatchColorSelected(selectedColor)
                        }
                        tvSelectedColorName.text = selectedColor.colorName
                    }
                })
            mSwatchConfig.drawableSelected =
                ShapeUtils.getShape(
                    mSwatchConfig.shapeType,
                    mSwatchConfig.selectedStrokeWidth,
                    "#00000000",
                    mSwatchConfig.selectedStrokeColor
                )
        }
    }

    private fun setSwatchLabelAlignment(alignment: Int) {
        var alignmentRule = when (alignment) {
            0 -> {
                RelativeLayout.ALIGN_PARENT_LEFT
            }
            1 -> {
                RelativeLayout.CENTER_HORIZONTAL
            }
            2 -> {
                RelativeLayout.ALIGN_PARENT_RIGHT
            }
            else -> {
                RelativeLayout.ALIGN_PARENT_LEFT
            }
        }
        val params = llColorLabel.layoutParams as RelativeLayout.LayoutParams
        params.addRule(alignmentRule)
        llColorLabel.layoutParams = params
    }

    fun setColorLabelFont(typeface: Typeface) {
        tvTitle.typeface = typeface
    }

    fun setSelectedColorLabelFont(typeface: Typeface) {
        tvSelectedColorName.typeface = typeface
    }


    data class SwatchConfig(
        var shapeType: Int = GradientDrawable.RECTANGLE,
        var drawableSelected: Drawable? = null,
        var selectedStrokeColor: Int = 0,
        var selectedStrokeWidth: Int = 0,
        var colorStrokeWidth: Int = 0,
        var backgroundColor: Int = 0,
        var colorLabelAlignment: Int = 0
    )

    fun setOnSwatchColorSelectedListener(listener: OnSwatchColorSelectedListener) {
        onSwatchColorSelectedListener = listener
    }

}