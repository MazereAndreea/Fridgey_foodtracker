package com.example.fridgey

import composables.SimpleDockedSearchBar
import Constants
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.fridgey.models.Food
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import composables.MainScreen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
/*
            val foodList = listOf(
                Food(
                    category = "Fruit",
                    categoryLabel = "Fresh Fruit",
                    foodId = 1,
                    image = "apple_test_url",
                    knownAs = "Apple",
                    label = "Apple",
                    nutrients = Nutrients(
                        CHOCDF = 14.0,
                        ENERC_KCAL = 52.0,
                        FAT = 0.2,
                        FIBTG = 2.4,
                        PROCNT = 0.3
                    )
                ),
                Food(
                    category = "Fruit",
                    categoryLabel = "Fresh Fruit",
                    foodId = 2,
                    image = "banana_test_url",
                    knownAs = "Banana",
                    label = "Banana",
                    nutrients = Nutrients(
                        CHOCDF = 27.0,
                        ENERC_KCAL = 96.0,
                        FAT = 0.3,
                        FIBTG = 2.6,
                        PROCNT = 1.3
                    )
                ),
                Food(
                    category = "Fruit",
                    categoryLabel = "Fresh Fruit",
                    foodId = 3,
                    image = "cherry_test_url",
                    knownAs = "Cherry",
                    label = "Cherry",
                    nutrients = Nutrients(
                        CHOCDF = 12.0,
                        ENERC_KCAL = 50.0,
                        FAT = 0.3,
                        FIBTG = 1.6,
                        PROCNT = 1.0
                    )
                )
            )

            SimpleDockedSearchBar(foodList)
*/
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


        // below I'm still working on it.
//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://food-nutrition-information.p.rapidapi.com/foods/search?query=cheese&pageSize=1&pageNumber=1&brandOwner=Kar%20Nut%20Products%20Company")
//            .get()
//            .addHeader("X-RapidAPI-Key", "a9ef7f104fmsh25f987557455206p11dd40jsnd44f8878aca9")
//            .addHeader("X-RapidAPI-Host", "food-nutrition-information.p.rapidapi.com")
//            .build()
    }

}