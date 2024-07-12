package composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDockedSearchBar(navigateToMainScreen : () -> Unit, /*foodList: MutableList<String>*/) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val listToSeeFunctionality = mutableListOf<String>("Cartof dulce", "ceapa", "masline", "magiun", "mamaliga")

    Scaffold(
        topBar = {
            SearchBar(
                query = "Enter something",
                onQueryChange = { text = it },
                colors = SearchBarDefaults.colors(
                    containerColor = androidx.compose.ui.graphics.Color.White,
                    //focusedContainerColor = androidx.compose.ui.graphics.Color.White
                ),
                modifier = Modifier
                    .onActiveChange()
                    .padding(24.dp)
                    .border(BorderStroke(2.dp, androidx.compose.ui.graphics.Color.Cyan), shape = CircleShape)
            )
        },

        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = navigateToMainScreen) {
                    Text(text = "Welcome to SearchBarActivity")
                }
            }
        }
    )
}

