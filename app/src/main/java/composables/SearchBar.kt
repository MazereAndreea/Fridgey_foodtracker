package composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.fridgey.models.Food

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDockedSearchBar(foodList: Food) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val listToSeeFunctionality = mutableListOf<String>("Cartof dulce", "ceapa", "masline", "magiun", "mamaliga")

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
                        LazyColumn {
                                for (item in listToSeeFunctionality) {
                                    print(item)
                                }
//                              foodList.label.forEach { label->
//                                  val foodLabel = foodList.label
                            }
                        }
                    }
                )
    ) { paddingValues ->
        // You can put additional content here that goes below the SearchBar
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}
