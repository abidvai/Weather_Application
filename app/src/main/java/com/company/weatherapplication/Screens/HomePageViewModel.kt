package com.company.weatherapplication.Screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.weatherapplication.Networking.ApiProvider
import com.company.weatherapplication.Networking.Constant.apiKey
import com.company.weatherapplication.Networking.NetworkResponse
import com.company.weatherapplication.Networking.WeatherModel
import kotlinx.coroutines.launch

class HomePageViewModel: ViewModel() {

    private val weather = ApiProvider.ProviderApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
     val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult
    fun getData(city: String){
        _weatherResult.value = NetworkResponse.Loading("Laoding")
        viewModelScope.launch {
            try {
                val response = weather.GetWeather(apiKey, city)
                if(response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to Load Data")
                }
            }catch (e: Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to Load Data")
            }
        }
    }

}