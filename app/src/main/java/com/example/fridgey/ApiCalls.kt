package com.example.fridgey.ApiHelper

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fridgey.models.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.fridgey.utils.isNetworkAvailable

@Composable
fun getFoodList(ingr: String, callback: (String) -> Unit) {
    var dialogMessage by remember { mutableStateOf("") }
    var requestedUrl by remember { mutableStateOf("") }

    if (LocalContext.current.isNetworkAvailable()) {
        val retrofit: ApiServiceParser = RetrofitClient.create(Constants.BASE_URL_PARSER)

        val response: Call<FoodResponse> = retrofit.getFood(Constants.APP_ID, Constants.APP_KEY, ingr)

        requestedUrl = response.request().url.toString()

        Log.d("TESTE 3: ", requestedUrl)

        response.enqueue(object : Callback<FoodResponse> {
            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                if (response.isSuccessful) {
                    val foodResponse = response.body()
                    val food = foodResponse?.parsed?.get(0)?.food
                    if (food != null) {
                        dialogMessage = "${food.knownAs} (${food.category}) \nNutrients(${food.nutrients.FAT} ${food.nutrients.ENERC_KCAL})"
                    }
                    callback(dialogMessage)
                    Log.d("TESTE: ", dialogMessage)
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    dialogMessage = "Calling the API failed: $errorBody"
                    callback(dialogMessage)

                    Log.d("TESTE 1: ", dialogMessage)
                }
            }

            override fun onFailure(p0: Call<FoodResponse>, t: Throwable) {
                TODO("Not yet implemented")
                dialogMessage = "API call failed: ${t.message}"
                callback(dialogMessage)

                Log.d("TESTE 2: ", dialogMessage)
            }
        })
    }
}

@Composable
fun getFoodAutoCompletition(query: String, limit: Int, callback: ((List<String>)) -> Unit) {
    var dialogMessage by remember { mutableStateOf("") }
    var requestedUrl by remember { mutableStateOf("") }

    if (LocalContext.current.isNetworkAvailable()) {
        val retrofit: ApiServiceAutoComplete = RetrofitClient.create(Constants.BASE_URL)

        val response: Call<List<String>> = retrofit.getAutoComplete(Constants.APP_ID, Constants.APP_KEY, query, limit)

        requestedUrl = response.request().url.toString()

        Log.d("TESTE 3: ", requestedUrl)

        response.enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val suggestions = response.body() ?: emptyList()
                    callback(suggestions)
                    Log.d("TESTE: ", suggestions.joinToString(", "))
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    dialogMessage = "Calling the API failed: $errorBody"
                    callback(emptyList())
                }
            }

            override fun onFailure(p0: Call<List<String>>, t: Throwable) {
                TODO("Not yet implemented")
                dialogMessage = "API call failed: ${t.message}"
                callback(emptyList())
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDialog(dialogMessage: String, onDismiss: () -> Unit) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
        modifier = Modifier.fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.wrapContentWidth().wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(dialogMessage)
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("OK")
                }
            }
        }
    }
}