package com.company.weatherapplication.Networking

import com.company.weatherapplication.Networking.Constant.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    var ProviderApi:ApiService = Retrofit.Builder()
        .client(OkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(ApiService::class.java)
}