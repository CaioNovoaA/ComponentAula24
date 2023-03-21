package br.com.caio.componentaula24.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import br.com.caio.componentaula24.R
import br.com.caio.componentaula24.databinding.CustomTitleBinding

class CustomTitle @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {
    private val binding = CustomTitleBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomTitle)
            try {
                initLayout(typedArray)
            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun initLayout(typedArray: TypedArray) {
        binding.run {
            genericTitle.text = typedArray.getString(R.styleable.CustomTitle_ct_text) ?: "texto"
            val color = typedArray.getResourceId(R.styleable.CustomTitle_ct_color, R.color.teal_700)
            genericTitle.setTextColor(getColor(color))
            arrowBack.imageTintList = ContextCompat.getColorStateList(
                context,
                color
            )
        }

    }

     fun ctSetOnClickListener(insideFunction: () -> Unit) {
        binding.arrowBack.setOnClickListener {
            binding.genericTitle.text = "Carregando"
            insideFunction()
        }
    }

    private fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }
}




