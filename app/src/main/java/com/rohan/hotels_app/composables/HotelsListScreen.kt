package com.rohan.hotels_app.composables

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rohan.hotels_app.data.Hotel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HotelsListScreen(navController: NavHostController, enteredCity: String) {
    var hotels by remember {
        mutableStateOf(
            Hotel(
                q = "",
                rc = "",
                rid = "",
                sr = emptyList()
            )
        )
    }
    val context = LocalContext.current
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(enteredCity) {
        isLoading = true
        hotels = RetrofitInstance.searchHotels(enteredCity)
        isLoading = false
        Log.d("Hotel JSON ", hotels.toString())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hotel List") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else {
                    if (hotels.sr.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Hotels for $enteredCity not found",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(hotels.sr) { hotel ->
                                if (hotel.type == "HOTEL") {
                                    HotelItem(navController, hotel = hotel)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}