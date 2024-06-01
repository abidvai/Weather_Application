package com.company.weatherapplication.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.company.weatherapplication.Networking.NetworkResponse
import com.company.weatherapplication.R
import com.company.weatherapplication.ui.theme.CloudyGrayBackground
import com.company.weatherapplication.ui.theme.CoolBlueGrayBackground
import com.company.weatherapplication.ui.theme.RainyBlueBackground
import com.company.weatherapplication.ui.theme.SunnyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(viewModel: HomePageViewModel) {
    var cityName by remember {
        mutableStateOf("")
    }

    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    BgColor()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .background(RainyBlueBackground)
            .fillMaxWidth()
            .padding(10.dp),

            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = cityName,
                onValueChange = {
                    cityName = it
                },
                placeholder = {
                              Text(text = "City Name")
                },
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.LightGray
                )
            )
            IconButton(onClick = {
                if (keyboardController != null) {
                    keyboardController.hide()
                }
                viewModel.getData(cityName)
            }) {
                Image(painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search icon")
            }
        }

        when(val result = weatherResult.value){
            is NetworkResponse.Error -> {
                DataNotFoundPage()
            }
            is NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success ->{
                result.data?.let { WeatherDetails(data = it) }
            }
            null -> {}
        }

    }
}
@Composable
fun BgColor(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(SunnyBlue)) {

    }
}

@Composable
fun DataNotFoundPage() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
        Text(text = "Opps...Weather not Found!!!!!")
        Text(text = "Try again....")
    }
}