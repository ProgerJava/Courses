package com.olejnikov.testovoe.mylibrary.presentation.ui.view.signIn

import android.content.Context
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ViewEditableFormBinding
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomBindingView

class EditableFormView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CustomBindingView<ViewEditableFormBinding>(context, attrs, defStyleAttr) {


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ViewEditableFormBinding.inflate(i, p, true)


    override fun applyAttrs(attrs: AttributeSet?) {
        super.applyAttrs(attrs)
        context.theme.obtainStyledAttributes(attrs, R.styleable.EditableFormView, 0, 0).apply {
            setTitleText(getString(R.styleable.EditableFormView_titleText))
            setHintText(getString(R.styleable.EditableFormView_hintText))
        }
    }

    fun setUpOnlyEmail() = with(binding) {
        val allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._-"
        field.apply {
            inputType = InputType.TYPE_CLASS_TEXT or
                    InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS or
                    InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
            keyListener = object : DigitsKeyListener() {
                override fun getAcceptedChars(): CharArray = allowedChars.toCharArray()
                override fun getInputType(): Int = InputType.TYPE_CLASS_TEXT
            }
        }
    }

    fun getText() = binding.field.text.toString()

    fun setTitleText(text: String?) {
        binding.nameOfForm.text = text
    }

    fun setHintText(text: String?) {
        binding.field.hint = text
    }

    fun doAfterTextChange(onChange:(text: String) -> Unit) {
        binding.field.doAfterTextChanged {
            onChange(it.toString())
        }
    }

    fun doAfterTextChange(onChange:() -> Unit) {
        binding.field.doAfterTextChanged {
            onChange()
        }
    }



}