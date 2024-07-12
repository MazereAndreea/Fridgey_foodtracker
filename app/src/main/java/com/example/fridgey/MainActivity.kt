package com.example.fridgey

import Constants
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.fridgey.models.Food
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composables.AppNavHost
import composables.MainScreen
import composables.SimpleDockedSearchBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }

        //function to get database of foods from edamam api
        fun getFoodList(label: String) {
            if (Constants.isNetworkAvailable(this)) {
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) //convert the data in json format
                    .build()

                //using this service to make the call
                val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

                val listCall: Call<Food> = service.getFood(
                    label, Constants.APP_ID
                )

                listCall.enqueue(object: Callback<Food>{
                    override fun onResponse(call: Call<Food>, response: Response<Food>) {
                        if(response.isSuccessful){
                            val foodList = response.body()
                            //function to use in other activites if you need the foodlist
//                            fun getFoodList(): Food? {
//
//                            }
                        }
                        else{
                            print("Calling the API failed")
                        }
                    }
                    override fun onFailure(call: Call<Food>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    }
}

