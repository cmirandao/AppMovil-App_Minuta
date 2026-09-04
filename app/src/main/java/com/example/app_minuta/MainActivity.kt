package com.example.app_minuta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.app_minuta.data.recipeRepository
import com.example.app_minuta.data.userRepository
import com.example.app_minuta.ui.theme.App_MinutaTheme

import com.example.app_minuta.ui.views.LoginView
import com.example.app_minuta.ui.views.RegisterView
import com.example.app_minuta.ui.views.PassRecoveryView
import com.example.app_minuta.ui.views.WeeklyFoodView

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Recovery : Screen("recovery")
    object FoodMenu : Screen("food_menu/{username}") {
        fun createRoute(username: String) = "food_menu/$username"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_MinutaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation()
                    }
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Login.route) {

        // Ruta de Login
        composable(Screen.Login.route) {
            LoginView(
                onLoginClick = { username ->
                    navController.navigate(Screen.FoodMenu.createRoute(username)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Screen.Register.route) },
                onRecoverClick = { navController.navigate(Screen.Recovery.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterView(
                onRegisterSuccess = { navController.navigateUp() },
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(Screen.Recovery.route) {
            PassRecoveryView(
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = Screen.FoodMenu.route,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            val currentUser = userRepository.findUser(username)
            val userDiet = currentUser?.diet ?: "Normal"
            val userName = currentUser?.name ?: "Invitado"
            val filteredRecipes = recipeRepository.getRecipesByDiet(userDiet)

            WeeklyFoodView(
                recetas = filteredRecipes,
                userName = userName,
                userDiet = userDiet,
                onNavigateBack = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}