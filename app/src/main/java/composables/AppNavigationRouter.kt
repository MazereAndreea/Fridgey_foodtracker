package composables

enum class Screens {
    GROCERIES_LIST,
    SETTINGS,
    MAIN_SCREEN
}

sealed class NavigationRoutes(val route: String){
    object groceries_list : NavigationRoutes(Screens.GROCERIES_LIST.name)
    object settings : NavigationRoutes(Screens.SETTINGS.name)
    object main_screen : NavigationRoutes(Screens.MAIN_SCREEN.name)
}
