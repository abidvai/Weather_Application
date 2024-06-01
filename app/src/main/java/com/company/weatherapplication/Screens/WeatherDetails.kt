package com.company.weatherapplication.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.company.weatherapplication.Networking.WeatherModel
import com.company.weatherapplication.ui.theme.CoolBlueGrayBackground

@Composable
fun WeatherDetails(data: WeatherModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.size(40.dp),
            )
            Text(text = data.location.name, fontSize = 30.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country, fontSize = 20.sp,
                color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))


        val temp = data.current.temp_c
        if(temp > 30.toString()){
            Text(text = "$temp° C",
            fontSize = 56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
                color = Color.Red
           )
        }else if (temp < 0.toString()){
            Text(text = "$temp° C",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }else{
            Text(text = "$temp° C",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
        
        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https:${data.current.condition.icon}".replace("64x64","128x128"),
            contentDescription = "Condition Icon",
           )
        Text(text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray
            ),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue(key = "Humidity", data.current.humidity)
                    WeatherKeyValue(key = "Wind Speed", data.current.wind_kph + " Km/h")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue(key = "UV", data.current.uv)
                    WeatherKeyValue(key = "Participation", data.current.precip_mm + " mm")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue(key = "Local Time",
                        data.location.localtime.split(" ")[1])
                    WeatherKeyValue(key = "local Date",
                        data.location.localtime.split(" ")[0])
                }
            }
        }
    }
}

@Composable
fun WeatherKeyValue(
    key: String,
    value: String
) {
    Column(modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
        Text(text = key, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}