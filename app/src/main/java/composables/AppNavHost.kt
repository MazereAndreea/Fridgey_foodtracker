package composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fridgey.SettingsScreen
import com.example.fridgey.models.view.AppSettings

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoutes.main_screen.route) {
        composable(NavigationRoutes.groceries_list.route){
            SimpleDockedSearchBar(navController)
        }
        composable(NavigationRoutes.modify_groceries.route){
            ModifyGroceriesScreen(navController)
        }
        composable(NavigationRoutes.settings.route){
            SettingsScreen(navController)
        }
        composable(NavigationRoutes.main_screen.route){
            MainScreen(navController)
        }
    }
}