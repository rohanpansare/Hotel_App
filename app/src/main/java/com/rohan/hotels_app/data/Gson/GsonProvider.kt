package com.rohan.hotels_app.data.Gson

import com.google.gson.Gson

object GsonProvider {
    val gson: Gson by lazy { Gson() }
}
