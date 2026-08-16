package com.example.app_minuta.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_minuta.data.Recipe

@OptIn(ExperimentalMaterial3Api::class) // Requerido para usar ExposedDropdownMenuBox en Material 3
@Composable
fun WeeklyFoodView(recetas: Array<Recipe>) {
    // Estado para controlar si el menú está abierto o cerrado
    var expanded by remember { mutableStateOf(false) }

    // Estado para guardar la receta seleccionada. Por defecto, la posición 0 (Día 1)
    var selectedRecipe by remember { mutableStateOf(recetas[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Minuta Semanal",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Combo Box (DropdownMenu) para seleccionar el día
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                // Mostramos el nombre del día y la receta seleccionada en el input
                value = "Día ${selectedRecipe.id}: ${selectedRecipe.name}",
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona un día") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth()
            )

            // Opciones desplegables basadas en tu array
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                recetas.forEach { receta ->
                    DropdownMenuItem(
                        text = { Text("Día ${receta.id}") },
                        onClick = {
                            selectedRecipe = receta // Actualizamos la receta a mostrar
                            expanded = false        // Cerramos el menú
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta que muestra SOLO la receta seleccionada
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Receta: ${selectedRecipe.name}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tip Nutricional:",
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = selectedRecipe.nutritionFact,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}