package composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDockedSearchBar(foodList: String) {
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
                        LazyColumn {
                            items(foodList) { food_item -> Text(
                                text = food_item,
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    top = 4.dp,
                                    end = 8.dp,
                                    bottom = 4.dp)
                            )

                            }
                        }
                    }
                })
    ) { paddingValues ->
        // You can put additional content here that goes below the SearchBar
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}
