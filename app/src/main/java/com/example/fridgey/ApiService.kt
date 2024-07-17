package com.example.fridgey

import com.example.fridgey.models.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceParser {
    @GET("parser")
    fun getFood(
        @Query("app_id") appid : String,
        @Query("app_key") label : String,
        @Query("ingr") ingr : String
    ) : Call<FoodResponse>
}

interface ApiServiceAutoComplete {
    @GET("auto-complete")
    fun getAutoComplete(
        @Query("app_id") appid : String,
        @Query("app_key") label : String,
        @Query("q") query : String,
        @Query("limit") limit : Int
    ) : Call<List<String>>
}