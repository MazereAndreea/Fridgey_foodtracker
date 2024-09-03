package composables

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDockedSearchBar(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val listToSeeFunctionality = remember { mutableListOf<String>("Cartof dulce", "ceapa", "masline", "magiun", "mamaliga") }

    Scaffold(
        modifier = Modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                active = active,
                onActiveChange = {active = it},
                onSearch = {active = false},
                content = {},
                placeholder = {
                    Text(text = "Enter or edit groceries")
                }
            )
        },
        bottomBar = {
            Text(text = "Bottom Bar")
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(text = "Search Bar")
                    Button(onClick = { navController.navigate(route = NavigationRoutes.main_screen.route) }) {
                        Text(text = "Back to Main Page")
                    }
                }
            )
        }
    )
}
