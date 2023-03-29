package com.rohan.hotels_app.composables

import RetrofitInstance
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rohan.hotels_app.data.Gson.GsonProvider
import com.rohan.hotels_app.data.HotelSummaryD.*
import com.rohan.hotels_app.data.Sr

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HotelDetailsScreen(navController: NavHostController, hotelName: String) {
    val hotel = GsonProvider.gson.fromJson(hotelName, Sr::class.java)
    var isLoading by remember { mutableStateOf(false) }
    var hotelSummary: HotelSummary
    var tagLine by remember {
        mutableStateOf("")
    }
    var rating by remember {
        mutableStateOf(0.0)
    }

    LaunchedEffect(hotel.hotelId) {
        isLoading = true
        hotelSummary = RetrofitInstance.getHotelSummary(hotel.hotelId)
        Log.d("Hotel Summary: ", hotelSummary.toString())
        tagLine = hotelSummary.data.propertyInfo.summary.tagline
        rating = hotelSummary.data.propertyInfo.summary.overview.propertyRating.rating
        isLoading = false
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Hotel Details") },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(Icons.Filled.ArrowBack, "Back")
                            }
                        },
                        backgroundColor = MaterialTheme.colors.primary
                    )
                }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = hotel.regionNames.fullName,
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Address : ${hotel.hotelAddress.street}, ${hotel.hotelAddress.city}, ${hotel.hotelAddress.province}",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "TagLine : $tagLine",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Rating : $rating",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}