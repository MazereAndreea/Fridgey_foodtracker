package com.example.fridgey

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface location_of_food {
    @GET("/api/food-database/v2/parser")
    suspend fun getAliments(@Query("89bc0cec8d60a28154aa7f3910365022")key: String): Response<List<Food>>
}