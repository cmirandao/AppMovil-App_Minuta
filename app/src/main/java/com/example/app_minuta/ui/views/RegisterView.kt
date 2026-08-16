package com.example.app_minuta.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(onRegisterSuccess: () -> Unit) {
    // Estados para los campos de texto
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Estado para el Check list
    var disclaimer by remember { mutableStateOf(false) }

    // Variables para el Radio Button
    val dietOptions = listOf("Normal", "Vegetariana", "Sin Gluten")
    var selectedDiet by remember { mutableStateOf(dietOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear Cuenta", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Input: Nombre completo
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Input: Nombre de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Input: Contraseña (con visualización oculta)
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Input: Confirmar Contraseña (con visualización oculta)
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Radio Buttons: Preferencia de dieta
        Text("Preferencia de dieta:", style = MaterialTheme.typography.bodyLarge)
        Column(Modifier.selectableGroup()) {
            dietOptions.forEach { diet ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (diet == selectedDiet),
                        onClick = { selectedDiet = diet }
                    )
                    Text(text = diet)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Check List: Términos y condiciones
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = disclaimer,
                onCheckedChange = { disclaimer = it }
            )
            Text("Acepto los términos y condiciones")
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Botón de registro
        Button(
            onClick = {
                /* Lógica para validar que las contraseñas coincidan y guardar en BBDD futura */
                onRegisterSuccess()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarme")
        }
    }
}