package com.olejnikov.testovoe.mylibrary.presentation.global

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.ui.navigation.Screens

fun <T : Any> T.tag() = this.javaClass.toString()

fun View.clickListener(onClick:() -> Unit) {
    this.setOnClickListener {
        onClick.invoke()
    }
}

fun ((Screens) -> Unit).toScreen(s: Screens): () -> Unit {
    return { this.invoke(s) }
}

@SuppressLint("NewApi")
fun TextView.changeColorAndAddActionForChapterOf(
    textId: Int? = null,
    textString: String = "",
    //typeFace: Int = R.font.roboto_bold,
    color: Int = R.color.white,
    onClick: ((text: String) -> Unit)? = null
): TextView {

    val s = SpannableStringBuilder(textId?.let { resources?.getString(it) } ?:textString)
    if (s.isEmpty()) return this

    //val fontMedium = resources?.getFont(typeFace) ?: return this

    var countOfRemoveSymbol = 0  //отражает смещение индексов после удаление на N символов

    val listWithIndexes= s.indices//индексы всех вхождений
        .filter { s.startsWith("###", it) }
        .toMutableList()

    listWithIndexes.forEachIndexed { i, value ->//удаляем ###
        s.delete(value - countOfRemoveSymbol, value + 3 - countOfRemoveSymbol)
        if (countOfRemoveSymbol != 0)
            listWithIndexes[i] = listWithIndexes[i] - countOfRemoveSymbol
        countOfRemoveSymbol += 3//3 - количество ###
    }

    for (i in 0 until listWithIndexes.size step 2) {
        val indSt = listWithIndexes[i]
        val indEn = listWithIndexes[i + 1]

        s.setSpan(
            object : ClickableSpan() {
                override fun updateDrawState(ds: TextPaint) {//TODO -> Action
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }

                override fun onClick(p0: View) {
                    onClick?.invoke(
                        (p0 as AppCompatTextView).text.toString().substring(indSt, indEn).trim()
                    )
                }

            }, indSt, indEn, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        s.setSpan(//TODO->Color
            ForegroundColorSpan(context.getColor(color)),
            indSt,
            indEn,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

//        s.setSpan(//TODO->Font
//            TypefaceSpan(fontMedium),
//            indSt,
//            indEn,
//            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
//        )
    }
    highlightColor = Color.TRANSPARENT
    text = s
    movementMethod = LinkMovementMethod.getInstance()
    return this
}

fun Context.openUrl(url: Uri) {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = url }
    startActivity(this, intent, null)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.visibleIf(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.invisibleIf(invisible: Boolean) {
    this.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

fun String.changeDateToAppFormat(): String {
    //2024-01-20
    //22 марта 2024
    val array = this.split("-")

    val month = when(array[1]) {
        "01" -> "января"
        "02" -> "февраля"
        "03" -> "марта"
        "04" -> "апреля"
        "05" -> "мая"
        "06" -> "июня"
        "07" -> "июля"
        "08" -> "августа"
        "09" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> "что-то пошло не так"
    }

    return "${array[2]} $month ${array[0]}"
}