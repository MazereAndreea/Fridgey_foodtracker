package composables

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.History
import androidx.navigation.NavHostController
import com.example.fridgey.ApiHelper.getFoodAutoCompletition
import com.example.fridgey.ApiHelper.getFoodList
import com.example.fridgey.ApiHelper.showDialog
import composables.layout.FridgeyScaffold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDockedSearchBar(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf("") }
    var apiResponse by remember { mutableStateOf<List<String>>(emptyList()) }
    var apiResponded by remember { mutableStateOf(false) }
    var apiFoodResponse by remember { mutableStateOf("") }
    var isClicked by remember { mutableStateOf(false) }
    var apiFoodResponded by remember { mutableStateOf(false) }

    FridgeyScaffold(navController = navController, title = "Search your item", true) {
        paddingValues ->

        Text(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            text = "Welcome to SearchBarActivity"
        )
        
        Column(
            modifier = Modifier.padding(vertical = 65.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SearchBar(
                modifier = Modifier.fillMaxWidth(),
                query = text,
                onQueryChange = {
                    text = it
                },
                onSearch = {
                    searchHistory.add(text)
                    active = false
                    isClicked = true
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(text = "Enter something")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
                },
                trailingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close icon"
                        )
                    }
                }
            ) {
                if (text.isNotEmpty()) {
                    getFoodAutoCompletition(text, 5) { result ->
                        apiResponse = result
                        apiResponded = true
                    }

                    if(apiResponded)
                    {
                        apiResponse.forEach { suggestion ->
                            Row(
                                modifier = Modifier
                                    .padding(all = 14.dp)
                                    .clickable {
                                        text = suggestion
                                        searchHistory.add(suggestion)
                                        isClicked = true
                                        Log.d("TESTE ITEM SEARCH ", suggestion)
                                    }
                            )
                            {
                                Icon(imageVector = Icons.Default.Lightbulb, contentDescription = null)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = suggestion)
                            }
                        }
                    }
                }

                searchHistory.forEach {
                    if (it.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .padding(all = 14.dp)
                                .clickable() {
                                    text = it
                                    isClicked = true
                                }
                        ) {
                            Icon(imageVector = Icons.Default.History, contentDescription = null)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = it)
                        }
                    }
                }

                Text(
                    modifier = Modifier
                        .padding(all = 14.dp)
                        .fillMaxWidth()
                        .clickable {
                            searchHistory.clear()
                        },
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    text = "clear all history"
                )
            }
        }

        if(apiFoodResponded) {
            showDialog(apiFoodResponse) { apiFoodResponded = false }
        }
    }

    if(isClicked)
    {
        isClicked = false
        Log.d("TESTE API CLICK BOOL: ", "true")
        getFoodList(text) { result ->
            apiFoodResponse = result
            apiFoodResponded = true
        }
    }
}
