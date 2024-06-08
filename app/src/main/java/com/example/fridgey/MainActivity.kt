package com.example.fridgey

import Constants
import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import com.example.fridgey.models.Food
import com.example.fridgey.models.FoodListResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page)

        val searchview = findViewById<SearchView>(R.id.searchview)
        val listview = findViewById<ListView>(R.id.listview)

        val alimente = arrayOf("rosii", "ceapa", "mure")
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alimente)
        listview.adapter = arrayAdapter

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                arrayAdapter.filter.filter(s)
                listview.adapter = arrayAdapter
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                arrayAdapter.filter.filter(s)
                return false
            }
        })

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
                        }
                        else{

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