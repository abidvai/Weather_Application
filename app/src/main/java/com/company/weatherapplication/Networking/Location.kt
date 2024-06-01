package com.company.weatherapplication.Networking

data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: String,
    val lon: String,
    val name: String,
    val region: String,
    val tz_id: String
)