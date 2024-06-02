package com.company.weatherapplication.Networking

import androidx.core.app.NotificationCompat.MessagingStyle.Message

sealed class NetworkResponse<T>(val data: T? = null,
    message: String? = null
) {

    class Success<T>(data: T?) : NetworkResponse<T>(data = data)
    class Loading<T>(message: String): NetworkResponse<T>()
    class Error<T>(message: String?) : NetworkResponse<T>(message = message)


}