package com.maradroid.dummyresponsegenerator.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPerfRepo(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(DUMMY_SHARED_PERF, Context.MODE_PRIVATE)

    fun useDummyResponse(): Boolean = sharedPreferences.getBoolean(DUMMY_KEY, false)

    fun setDummyResponse(useDummyResponse: Boolean) = sharedPreferences.edit().putBoolean(DUMMY_KEY, useDummyResponse).apply()
}