package com.example.myrcipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp (navController : NavHostController ) {
    val recipeViewModel : MainViewModel = viewModel()
    val viewState by recipeViewModel.categoryState

    NavHost(navController = navController, startDestination = Screens.RepiceScreen.route){
        composable(route = Screens.RepiceScreen.route) {
            RecipeScreen(
                viewState = viewState,
                navigateToDetails = {
                    // this part is responsible for sending data from one screen to another scree
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it);
                    navController.navigate(Screens.DetailScreen.route)
                }
            )
        }

        composable( route = Screens.DetailScreen.route) {
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                    get<Category>("cat") ?: Category("","","","")
            
            CategoryDetailScreen(category = category)
        }
    }


}