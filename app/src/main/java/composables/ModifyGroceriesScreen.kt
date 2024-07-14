package composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fridgey.getFoodList
import com.example.fridgey.showDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyGroceriesScreen(navController: NavHostController) {
    var foodInput by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }
    var apiResponse by remember { mutableStateOf("") }
    var dialogMessage by remember { mutableStateOf("") }
    var apiResponded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Grocery List")
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { navController.navigate(route = NavigationRoutes.main_screen.route) }
            ) {
                Text(text = "Go back to Main Screen")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = foodInput,
                onValueChange = { foodInput = it },
                label = { Text("Enter food item") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    isClicked  = true
                }
            ) {
                Text(text = "Insert food")
            }

            if(apiResponded) {
                showDialog(apiResponse) { apiResponded = false }
            }
        }
    }

    if (isClicked) {
        isClicked = false
        getFoodList(foodInput) { result ->
            apiResponse = result
            Log.d("TEST API RES: ", apiResponse)
            apiResponded = true
        }
    }
}
