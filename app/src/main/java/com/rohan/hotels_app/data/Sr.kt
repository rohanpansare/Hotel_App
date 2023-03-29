package com.rohan.hotels_app.data

data class Sr(
    val cityId: String,
    val coordinates: Coordinates,
    val gaiaId: String,
    val hotelAddress: HotelAddress,
    val hotelId: String,
    val index: String,
    val isMinorAirport: String,
    val regionNames: RegionNames,
    val subtype: String,
    val type: String
)