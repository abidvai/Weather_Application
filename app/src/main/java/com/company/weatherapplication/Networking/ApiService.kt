package com.company.weatherapplication.Networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // api key: a0ae7bb29c744edea41214712243105
    // v1/current.json?key=a0ae7bb29c744edea41214712243105&q=Tangail&aqi=no

    @GET("/v1/current.json")
    suspend fun GetWeather(
        @Query("key") apikey : String,
        @Query("q") city : String
    ) : Response<WeatherModel>

}