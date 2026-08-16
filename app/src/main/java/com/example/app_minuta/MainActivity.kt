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
import com.example.app_minuta.data.Recipe
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
    // 1. ESTADO DE NAVEGACIÓN: Controla qué pantalla se está mostrando
    var actualView by remember { mutableStateOf("LOGIN") }

    // 2. SESIÓN LOCAL (Simulada): Guarda el nombre del usuario logueado
    var sessionUser by remember { mutableStateOf<String?>(null) }

    // 3. ARRAY DE DATOS: Instanciamos la minuta para la vista final
    val weeklyFood = arrayOf<Recipe>(
        Recipe(1, "Pollo al horno", "Alto en proteínas y bajo en grasas."),
        Recipe(2, "Lentejas guisadas", "Excelente fuente de hierro y fibra."),
        Recipe(3, "Pescado a la plancha", "Rico en Omega 3 y vitaminas."),
        Recipe(4, "Ensalada de quinoa", "Aporte de carbohidratos complejos."),
        Recipe(5, "Tortilla de espinacas", "Buena fuente de calcio.")
    )

    // Un switch (when) que dibuja la pantalla correspondiente
    when (actualView) {
        "LOGIN" -> {
            LoginView(
                onLoginClick = { user ->
                    sessionUser = user // Guardamos el usuario en "sesión"
                    actualView = "FOODMENU" // Cambiamos de pantalla
                },
                onRegisterClick = { actualView = "REGISTER" },
                onRecoverClick = { actualView = "RECOVERY" }
            )
        }
        "REGISTER" -> {
            RegisterView(
                onRegisterSuccess = {
                    actualView = "LOGIN" // Tras registrar, volvemos al login
                }
            )
        }
        "RECOVERY" -> {
            // Si le pusiste un parámetro a PassRecoveryView similar a onBackClick, ponlo aquí
            PassRecoveryView()
        }
        "FOODMENU" -> {
            // Aquí ya estamos "logueados". Podrías usar la variable sessionUser
            // si quieres mostrar un mensaje de bienvenida en la vista de la minuta.
            WeeklyFoodView(recetas = weeklyFood)
        }
    }
}