package com.example.app_minuta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import com.example.app_minuta.data.weeklyFoodMenu

import com.example.app_minuta.ui.theme.App_MinutaTheme
import com.example.app_minuta.ui.views.LoginView
import com.example.app_minuta.ui.views.RegisterView
import com.example.app_minuta.ui.views.WeeklyFoodView
import com.example.app_minuta.ui.views.PassRecoveryView
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Muestra toda la pantalla
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
    var actualView by remember { mutableStateOf("LOGIN") }
    var sessionUser by remember { mutableStateOf<String?>(null) }

    when (actualView) {
        "LOGIN" -> {
            LoginView(
                onLoginClick = { user ->
                    sessionUser = user
                    actualView = "FOODMENU"
                },
                onRegisterClick = { actualView = "REGISTER" },
                onRecoverClick = { actualView = "RECOVERY" }
            )
        }
        "REGISTER" -> {
            RegisterView(
                onRegisterSuccess = {
                    actualView = "LOGIN"
                }
            )
        }
        "RECOVERY" -> {
            PassRecoveryView()
        }
        "FOODMENU" -> {
            WeeklyFoodView(recetas = weeklyFoodMenu)
        }
    }
}