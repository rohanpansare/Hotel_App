package com.rohan.hotels_app.network

import com.rohan.hotels_app.data.Hotel
import retrofit2.http.GET
import retrofit2.http.Query

interface HotelApi {
    @GET("locations/v3/search")
    suspend fun searchHotels(
        @Query("q") query: String,
        @Query("locale") locale: String = "en_US"
    ): Hotel
}
