package com.example.fridgey.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest

private var isNetworkAvailable = false

fun Context.isNetworkAvailable(): Boolean {
    registerNetworkCallback()
    return isNetworkAvailable
}

private fun Context.registerNetworkCallback() {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val builder = NetworkRequest.Builder()

    connectivityManager.registerNetworkCallback(
        builder.build(),
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isNetworkAvailable = true
            }

            override fun onLost(network: Network) {
                isNetworkAvailable = false
            }
        }
    )
}