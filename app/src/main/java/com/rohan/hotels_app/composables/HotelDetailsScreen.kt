package com.rohan.hotels_app.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rohan.hotels_app.data.Gson.GsonProvider
import com.rohan.hotels_app.data.Sr

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HotelDetailsScreen(navController: NavHostController, hotelName: String) {
    val hotel = GsonProvider.gson.fromJson(hotelName, Sr::class.java)
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
                Text(
                    text = hotel.regionNames.fullName,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Address : ${hotel.hotelAddress.street}, ${hotel.hotelAddress.city}, ${hotel.hotelAddress.province}",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}