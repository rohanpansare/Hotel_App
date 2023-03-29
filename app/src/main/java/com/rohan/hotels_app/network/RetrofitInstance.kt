import android.util.Log
import com.rohan.hotels_app.data.Hotel
import com.rohan.hotels_app.data.HotelSummaryD.HotelSummary
import com.rohan.hotels_app.network.HotelApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
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


    suspend fun getHotelSummary(hotelId: String): HotelSummary {
        val mediaType = "application/json".toMediaTypeOrNull()
        val body = "{\"propertyId\": \"$hotelId\"}".toRequestBody(mediaType)
        val hotelSummary = hotelApi.getHotelSummary(body)
        Log.d("Hotel TagLine ", hotelSummary.data.propertyInfo.summary.tagline)
        return hotelSummary
    }

}
