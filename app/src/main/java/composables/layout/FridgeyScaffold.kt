package composables.layout

import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import composables.NavigationRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FridgeyScaffold(
    navController: NavHostController,
    title: String,
    showDropDown : Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(route = NavigationRoutes.main_screen.route)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "backIcon")
                    }
                },
                actions = {
                    if(showDropDown) {
                        IconButton(onClick = { menuExpanded = !menuExpanded }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "menu")
                        }
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = { menuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Enter groceries") },
                                onClick = {
                                    menuExpanded = false
                                    navController.navigate(route = NavigationRoutes.groceries_list.route)
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Modify groceries") },
                                onClick = {
                                    menuExpanded = false
                                    navController.navigate(route = NavigationRoutes.modify_groceries.route)
                                })
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = {
                                    menuExpanded = false
                                    navController.navigate(route = NavigationRoutes.settings.route)
                                })
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                content(paddingValues)
            }
        }
    )
}