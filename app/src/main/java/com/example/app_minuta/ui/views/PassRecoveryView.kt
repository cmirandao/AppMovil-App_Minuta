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
import com.example.app_minuta.data.userRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.database

@Composable
fun PassRecoveryView(
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Estados para mostrar/ocultar las contraseñas
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var statusMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Recuperar Contraseña", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Ingresa tu usuario y define tu nueva contraseña para actualizar tus credenciales.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de usuario") },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Nueva Contraseña con icono para mostrar/ocultar
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva contraseña") },
            visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (newPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (newPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            enabled = !isLoading,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Confirmar Nueva Contraseña con icono para mostrar/ocultar
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar nueva contraseña") },
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
        Spacer(modifier = Modifier.height(16.dp))

        if (statusMessage.isNotEmpty()) {
            Text(
                text = statusMessage,
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(8.dp))
        } else {
            Button(
                onClick = {
                    val cleanUser = username.trim()
                    val foundUser = userRepository.findUser(cleanUser)

                    when {
                        cleanUser.isBlank() || newPassword.isBlank() || confirmPassword.isBlank() -> {
                            isError = true
                            statusMessage = "Por favor, completa todos los campos."
                        }
                        foundUser == null -> {
                            isError = true
                            statusMessage = "El usuario no existe."
                        }
                        newPassword.length < 6 || !newPassword.any { it.isUpperCase() } || !newPassword.any { it.isLowerCase() } || !newPassword.any { it.isDigit() } -> {
                            isError = true
                            statusMessage = "La contraseña debe tener mín. 6 caracteres, una mayúscula, una minúscula y un número."
                        }
                        newPassword != confirmPassword -> {
                            isError = true
                            statusMessage = "Las contraseñas no coinciden."
                        }
                        else -> {
                            isLoading = true
                            val sanitizedKey = cleanUser.replace(".", "_")
                                .replace("#", "_")
                                .replace("$", "_")
                                .replace("[", "_")
                                .replace("]", "_")

                            Firebase.database.getReference("users")
                                .child(sanitizedKey)
                                .child("pass")
                                .setValue(newPassword)
                                .addOnSuccessListener {
                                    if (cleanUser.contains("@")) {
                                        Firebase.auth.sendPasswordResetEmail(cleanUser)
                                    }
                                    isLoading = false
                                    isError = false
                                    statusMessage = "Hola ${foundUser.name}, tu contraseña ha sido actualizada con éxito."
                                    Toast.makeText(context, "Contraseña actualizada", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    isLoading = false
                                    isError = true
                                    statusMessage = "Error al actualizar: ${e.localizedMessage}"
                                }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar instrucciones y actualizar")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateBack, enabled = !isLoading) {
            Text("Volver al Login")
        }
    }
}