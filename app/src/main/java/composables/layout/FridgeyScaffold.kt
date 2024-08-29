package composables.layout

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fridgey.models.NavItems
import composables.NavigationRoutes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FridgeyScaffold(
    navController: NavHostController,
    title: String,
    isMainMenu : Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navigationItems = listOf(
        NavItems(
            label = "Enter groceries",
            icon = Icons.AutoMirrored.Filled.List,
            route = NavigationRoutes.groceries_list.route
        ),
        NavItems(
            label = "Modify groceries",
            icon = Icons.Filled.Edit,
            route = NavigationRoutes.modify_groceries.route
        ),
        NavItems(
            label = "Settings",
            icon = Icons.Filled.Settings,
            route = NavigationRoutes.settings.route
        )
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationRail {
                navigationItems.forEach { item ->
                    NavigationRailItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        modifier = Modifier.padding(14.dp),
                        selected = false,
                        onClick = {
                            navController.navigate(route = item.route)
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    navigationIcon = {
                        if(!isMainMenu)
                        {
                            IconButton(onClick = {
                                navController.navigate(route = NavigationRoutes.main_screen.route)
                            }) {
                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "backIcon")
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Open Sidebar")
                        }
                    }
                )
            },
            content = { paddingValues ->
                content(paddingValues)
            }
        )
    }
}