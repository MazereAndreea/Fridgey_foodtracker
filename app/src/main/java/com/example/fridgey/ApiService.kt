package com.example.fridgey
import Constants
import com.example.fridgey.models.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/food-database/v2/parser")
    fun getFood(
        @Query("label") label : String,
        @Query("appid") appid : String?
    ) : Call<Food>
}