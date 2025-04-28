package com.olejnikov.testovoe.mylibrary.presentation.ui.view.recyclerItem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.olejnikov.testovoe.domain.model.response.Course
import com.olejnikov.testovoe.domain.store.UserDataStore
import com.olejnikov.testovoe.mylibrary.presentation.R
import com.olejnikov.testovoe.mylibrary.presentation.databinding.ItemCoursesBinding
import com.olejnikov.testovoe.mylibrary.presentation.global.changeDateToAppFormat
import com.olejnikov.testovoe.mylibrary.presentation.ui.view.CustomListView
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

class ViewItemCourse @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : CustomListView<Course, ItemCoursesBinding>(context, attrs) {

    private var readMoreListener:(id: Int) -> Unit = {}
    private var addFavouriteListener:(id: Int) -> Unit = {}
    private val fav get() = object: KoinComponent {val s: UserDataStore by inject()}.s.getFavourites()

    init {
        setMatchWrap()
        setDefault()
    }


    override fun inflateBinding(i: LayoutInflater, p: ViewGroup) = ItemCoursesBinding.inflate(i, p, true)

    override fun setData(model: Course) = with(binding) {
        nameOfCourse.text = model.title
        descOfCourse.text = model.text
        price.text = model.price
        mark.text = model.rate
        date.text = model.publishDate.toString().changeDateToAppFormat()

        when(model.hasLike == true || isFavourite(model.id)) {
            true -> rightImage.setImageResource(R.drawable.ic_bookmark_fill)
            else -> rightImage.setImageResource(R.drawable.ic_favourite)
        }

        //Listeners
        checkMore.setOnClickListener {
            readMoreListener.invoke(model.id)
        }
        mainLinear.setOnClickListener {
            readMoreListener.invoke(model.id)
        }
        linearWithRightImage.setOnClickListener {
            when(isFavourite(model.id)) {
                true -> rightImage.setImageResource(R.drawable.ic_favourite)
                else -> rightImage.setImageResource(R.drawable.ic_bookmark_fill)
            }
            addFavouriteListener.invoke(model.id)
        }
    }

    fun isFavourite(id: Int) = fav != null && fav!!.contains(id as Integer)

    fun setUpListenerReadMore(onClick:(id: Int) -> Unit) {
        readMoreListener = onClick
    }

    fun setUpListenerAddFavourite(onClick:(id: Int) -> Unit) {
        addFavouriteListener = onClick
    }

    private fun setDefault() = with(binding) {
        nameOfCourse.text = ""
        descOfCourse.text = ""
        price.text = ""
        mark.text = ""
        date.text = ""
        rightImage.setImageResource(R.drawable.ic_favourite)
    }






}