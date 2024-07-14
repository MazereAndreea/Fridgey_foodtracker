package com.example.fridgey

import Constants
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.fridgey.models.Food
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import composables.AppNavHost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()

            var dialogMessage by remember { mutableStateOf("") }
            var showDialog by remember { mutableStateOf(false) }

            //function to get database of foods from edamam api
            fun getFoodList(ingr: String) {
                if (Constants.isNetworkAvailable(this)) {
                    val retrofit: ApiServiceParser = RetrofitClient.create(Constants.BASE_URL_PARSER)

                    val response: Call<Food> = retrofit.getFood(Constants.APP_ID, Constants.APP_KEY, ingr)

                    response.enqueue(object : Callback<Food> {
                        override fun onResponse(call: Call<Food>, response: Response<Food>) {
                            if (response.isSuccessful) {
                                val food = response.body()
                                if (food != null) {
                                    dialogMessage = "${food.knownAs} ${food.category}"
                                    showDialog = true
                                }

                            } else {
                                dialogMessage = "Calling the API failed: ${response.errorBody()}"
                                showDialog = true

                            }
                        }

                        override fun onFailure(call: Call<Food>, t: Throwable) {
                            TODO("Not yet implemented")
                            dialogMessage = "API call failed: ${t.message}"
                            showDialog = true
                        }
                    })
                }
            }

            LaunchedEffect(Unit) {
                getFoodList("apple")
            }

            if (showDialog) {
                ShowDialog(dialogMessage) { showDialog = false }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDialog(dialogMessage: String, onDismiss: () -> Unit) {
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