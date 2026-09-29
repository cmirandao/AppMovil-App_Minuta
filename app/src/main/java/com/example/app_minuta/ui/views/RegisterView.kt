package com.example.app_minuta.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.app_minuta.data.User
import com.example.app_minuta.data.userRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterView(
    onRegisterSuccess: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Estados para visualizar u ocultar las contraseñas
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var selectedDiet by remember { mutableStateOf("Normal") }
    var selectedActivityLevel by remember { mutableStateOf("Moderado") }

    var dietExpanded by remember { mutableStateOf(false) }
    var activityExpanded by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val dietOptions = listOf("Normal", "Vegetariana", "Sin Gluten")
    val activityOptions = listOf("Sedentario", "Ligero", "Moderado", "Intenso")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Registro de Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre Completo") },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Contraseña con botón para visualizar/ocultar
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Campo de Confirmar Contraseña con botón para visualizar/ocultar
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar contraseña") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (confirmPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Selector de Preferencia de dieta
        ExposedDropdownMenuBox(
            expanded = dietExpanded,
            onExpandedChange = { if (!isLoading) dietExpanded = !dietExpanded }
        ) {
            OutlinedTextField(
                value = selectedDiet,
                onValueChange = {},
                readOnly = true,
                label = { Text("Preferencia de dieta") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = dietExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = dietExpanded,
                onDismissRequest = { dietExpanded = false }
            ) {
                dietOptions.forEach { diet ->
                    DropdownMenuItem(
                        text = { Text(diet) },
                        onClick = {
                            selectedDiet = diet
                            dietExpanded = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        // Selector de Nivel de Actividad Física
        ExposedDropdownMenuBox(
            expanded = activityExpanded,
            onExpandedChange = { if (!isLoading) activityExpanded = !activityExpanded }
        ) {
            OutlinedTextField(
                value = selectedActivityLevel,
                onValueChange = {},
                readOnly = true,
                label = { Text("Nivel de Actividad Física") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = activityExpanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = activityExpanded,
                onDismissRequest = { activityExpanded = false }
            ) {
                activityOptions.forEach { level ->
                    DropdownMenuItem(
                        text = { Text(level) },
                        onClick = {
                            selectedActivityLevel = level
                            activityExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        } else {
            Button(
                onClick = {
                    when {
                        name.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                            errorMessage = "Por favor, completa todos los campos."
                        }
                        userRepository.isFull() -> {
                            errorMessage = "Se ha alcanzado el límite máximo de usuarios registrados (5)."
                        }
                        userRepository.userExists(username.trim()) -> {
                            errorMessage = "El nombre de usuario ya está en uso."
                        }
                        password.length < 6 || !password.any { it.isUpperCase() } || !password.any { it.isLowerCase() } || !password.any { it.isDigit() } -> {
                            errorMessage = "La contraseña debe tener mín. 6 caracteres, una mayúscula, una minúscula y un número."
                        }
                        password != confirmPassword -> {
                            errorMessage = "Las contraseñas no coinciden."
                        }
                        else -> {
                            isLoading = true
                            errorMessage = ""
                            val newUser = User(
                                name = name.trim(),
                                username = username.trim(),
                                pass = password,
                                diet = selectedDiet,
                                activityLevel = selectedActivityLevel
                            )
                            userRepository.registerUserWithFirebase(newUser) { success, message ->
                                isLoading = false
                                if (success) {
                                    Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                                    onRegisterSuccess()
                                } else {
                                    errorMessage = message
                                }
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateBack, enabled = !isLoading) {
            Text("Volver al Login")
        }
    }
}