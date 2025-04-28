@file:Suppress("unused")

package com.olejnikov.testovoe.data.store

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type
import androidx.core.content.edit
import com.google.gson.reflect.TypeToken

open class SimpleDataStore(val name: String, context: Context, private val gson: Gson) {
    protected val sp: SharedPreferences =
        context.getSharedPreferences("data_store_$name", Context.MODE_PRIVATE)

    protected fun put(name: String, data: Any) {
        sp.edit {
            putString(name, gson.toJson(data))
        }   // todo io suspend
    }

    protected fun <T> get(name: String, typeOfT: Type): T? {
        val json = sp.getString(name, null) ?: return null
        return gson.fromJson<T>(json, typeOfT)
    }

    protected fun <T> get(name: String, clazz: Class<T>): T? {
        val json = sp.getString(name, null) ?: return null
        return gson.fromJson(json, clazz)
    }

    protected fun <T> getList(name: String, clazz: Class<T>): List<T>? {
        val json = sp.getString(name, null) ?: return null
        val typeOfT = TypeToken.getParameterized(MutableList::class.java, clazz).type
        return gson.fromJson(json, typeOfT)
    }

    protected fun clear() {
        //todo удаляю не все данные
        with(sp.edit()) {
            remove(UserDataStoreImpl.Companion.NAME)
            remove(UserDataStoreImpl.Companion.FAVORITES_COURSES)
            remove(UserDataStoreImpl.Companion.FLAG_FIRST_ENTER)


            apply()
        }
        // todo io suspend
    }

}
