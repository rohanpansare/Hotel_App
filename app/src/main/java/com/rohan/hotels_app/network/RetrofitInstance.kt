package com.rohan.hotels_app.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohan.hotels_app.data.Hotel
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://hotels4.p.rapidapi.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader(
                            "x-rapidapi-key",
                            "eb07c2ad91msh79b8bfcf83611f4p13a2f5jsn39e78106a121"
                        )
                        .addHeader("x-rapidapi-host", "hotels4.p.rapidapi.com")
                        .build()
                    chain.proceed(request)
                }.build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val hotelApi = retrofit.create(HotelApi::class.java)

    suspend fun searchHotels(city: String): Hotel {
        return hotelApi.searchHotels(query = city)
    }

}