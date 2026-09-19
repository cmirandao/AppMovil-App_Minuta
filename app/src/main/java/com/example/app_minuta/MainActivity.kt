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

// Librerías de Navegación
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Repositorios y Tema
import com.example.app_minuta.data.recipeRepository
import com.example.app_minuta.data.userRepository
import com.example.app_minuta.ui.theme.App_MinutaTheme

// Vistas
import com.example.app_minuta.ui.views.LoginView
import com.example.app_minuta.ui.views.RegisterView
import com.example.app_minuta.ui.views.PassRecoveryView
import com.example.app_minuta.ui.views.WeeklyFoodView
import com.example.app_minuta.ui.views.RecetaView

// 1. Jerarquía de Rutas Tipadas (POO)
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Recovery : Screen("recovery")

    object FoodMenu : Screen("food_menu/{username}") {
        fun createRoute(username: String) = "food_menu/$username"
    }

    object RecipeDetail : Screen("recipe_detail/{recipeId}") {
        fun createRoute(recipeId: Int) = "recipe_detail/$recipeId"
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

        // --- AUTENTICACIÓN ---
        composable(Screen.Login.route) {
            LoginView(
                onLoginSuccess = { username ->
                    navController.navigate(Screen.FoodMenu.createRoute(username)) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = { navController.navigate(Screen.Register.route) },
                onNavigateToRecovery = { navController.navigate(Screen.Recovery.route) }
            )
        }

        composable(Screen.Register.route) {
            RegisterView(
                onRegisterSuccess = { navController.popBackStack() },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Recovery.route) {
            PassRecoveryView(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // --- HOMEMENÚ (MINUTA) ---
        composable(
            route = Screen.FoodMenu.route,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            val currentUser = userRepository.getUser(username)
            val userDiet = currentUser?.diet ?: "Normal"
            val userName = currentUser?.name ?: "Invitado"
            val filteredRecipes = recipeRepository.getRecipesByDiet(userDiet)

            WeeklyFoodView(
                recetas = filteredRecipes,
                userName = userName,
                userDiet = userDiet,
                onNavigateBack = {
                    navController.navigate(Screen.Login.route) { popUpTo(0) }
                },
                onRecipeClick = { recipeId ->
                    navController.navigate(Screen.RecipeDetail.createRoute(recipeId))
                }
            )
        }

        // --- DETALLE DE RECETA ---
        composable(
            route = Screen.RecipeDetail.route,
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId") ?: 0

            val recipe = recipeRepository.getRecipeById(recipeId)

            RecetaView(
                receta = recipe,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}