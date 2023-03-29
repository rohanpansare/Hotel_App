package com.rohan.hotels_app.network

import com.rohan.hotels_app.data.Hotel
import com.rohan.hotels_app.data.HotelSummaryD.HotelSummary
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HotelApi {
    @GET("locations/v3/search")
    suspend fun searchHotels(
        @Query("q") query: String,
        @Query("locale") locale: String = "en_US"
    ): Hotel

    @POST("properties/v2/get-summary")
    suspend fun getHotelSummary(@Body body: RequestBody): HotelSummary

}
