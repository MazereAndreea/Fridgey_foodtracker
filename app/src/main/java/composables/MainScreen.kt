package composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController


/*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.fridgey.models.Food
import com.example.fridgey.models.Nutrients
*/

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title={
                    Text(text = "Welcome to Fridgey") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
/*fun SimpleDockedSearchBar(foodList: List<Food>) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold (
        topBar = (
                {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        query = text,
                        onQueryChange = { text = it },
                        onSearch = { active = false },
                        active = active,
                        onActiveChange = { active = it }
                    ) {
                        LazyColumn (
                            contentPadding = PaddingValues(16.dp)
                        )
                        {
                            items(foodList) { food ->
                                FoodCard(food.knownAs, food.category, food.image)
                            }
                        }*/
                    }
                }
            )}
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                   navController.navigate(route = NavigationRoutes.groceries_list.route)
                //A problem for tomorrow -> navigate between screens
//                val navigate = Intent(context, SearchBarActivity::class.java)
//                launcher.launch(navigate)
                }
            ) {
                Text(text = "Enter groceries")
            }
            Button(
                onClick = {
                    navController.navigate(route = NavigationRoutes.modify_groceries.route)
                }
            ) {
                Text(text = "Modify groceries")
            }
            Button(
                onClick = {
                   navController.navigate(route = NavigationRoutes.settings.route)
                }) {
                Text(text = "Settings")
                }
        }
    }
}
