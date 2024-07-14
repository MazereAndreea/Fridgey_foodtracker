package com.example.fridgey
import Constants
import com.example.fridgey.models.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceParser {
    @GET(Constants.BASE_URL_PARSER)
    fun getFood(
        @Query("app_id") appid : String,
        @Query("app_key") label : String,
        @Query("ingr") ingr : String
    ) : Call<Food>
}