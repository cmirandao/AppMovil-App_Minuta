package com.example.app_minuta.ui.views

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_minuta.data.Recipe
import com.example.app_minuta.data.recipeRepository
import com.example.app_minuta.data.userRepository

@Composable
fun WeeklyFoodView(
    recetas: Array<Recipe>,
    userName: String,
    userDiet: String,
    onNavigateBack: () -> Unit,
    onRecipeClick: (Int) -> Unit,
    usernameAccount: String = ""
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val gridColumns = if (configuration.screenWidthDp >= 600) {
        GridCells.Fixed(2)
    } else {
        GridCells.Fixed(1)
    }

    // Estado reactivo para reflejar el cambio de dieta
    var selectedDiet by remember(userDiet) { mutableStateOf(userDiet) }
    val currentRecipes = remember(selectedDiet, recetas) {
        recipeRepository.getRecipesByDiet(selectedDiet)
    }
    var showDeleteDialog by remember { mutableStateOf(false) }

    val dietOptions = listOf("Normal", "Vegetariana", "Sin Gluten")
    val targetUsername = usernameAccount.ifBlank { userName }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp, top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Minuta Semanal",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Usuario: $userName\nDieta: $selectedDiet",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                OutlinedButton(
                    onClick = {
                        userRepository.signOut()
                        onNavigateBack()
                    }
                ) {
                    Text("Cerrar Sesión")
                }
                TextButton(
                    onClick = { showDeleteDialog = true },
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Eliminar Cuenta")
                }
            }
        }

        // Update sincronizado con Firebase
        Text(
            text = "Cambiar tipo de dieta:",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            dietOptions.forEach { diet ->
                FilterChip(
                    selected = selectedDiet == diet,
                    onClick = {
                        selectedDiet = diet
                        userRepository.updateUserDiet(targetUsername, diet) { success ->
                            val msg = if (success) "Dieta actualizada a $diet" else "Dieta cambiada localmente"
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        }
                    },
                    label = { Text(diet) }
                )
            }
        }

        LazyVerticalGrid(
            columns = gridColumns,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(currentRecipes.toList()) { receta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRecipeClick(receta.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = receta.dayOfWeek,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = receta.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { onRecipeClick(receta.id) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Ver Receta")
                        }
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar cuenta") },
            text = { Text("¿Estás seguro de que deseas eliminar tu cuenta de forma permanente?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDeleteDialog = false
                        userRepository.deleteUser(targetUsername) {
                            Toast.makeText(context, "Cuenta eliminada", Toast.LENGTH_SHORT).show()
                            onNavigateBack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}