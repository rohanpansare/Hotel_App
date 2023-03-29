package com.rohan.hotels_app.navigation

import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rohan.hotels_app.composables.CitySearchBar
import com.rohan.hotels_app.composables.HotelDetailsScreen
import com.rohan.hotels_app.composables.HotelsListScreen


@Composable
fun HotelNavigation() {
    val navController = rememberNavController()
    var enteredCity by remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "schoolList") {
        composable("schoolList") {
            //SchoolList(navController = navController)
            CitySearchBar(onSearch = {city ->
                enteredCity = city
                Log.d("City", city)
                navController.navigate("hotelList/${enteredCity}")
            })

        }
        composable(
            "hotelList/{cityName}",
            arguments = listOf(navArgument("cityName") { type = NavType.StringType })
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            HotelsListScreen(navController,cityName)
        }

        composable(
            "hotelDetail/{hotelName}",
            arguments = listOf(navArgument("hotelName") { type = NavType.StringType })
        ) { backStackEntry ->
            val hotelName = backStackEntry.arguments?.getString("hotelName") ?: ""
            HotelDetailsScreen(navController,hotelName)
        }
    }
}