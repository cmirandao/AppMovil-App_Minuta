package com.example.app_minuta.ui.views

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_minuta.data.Recipe

@Composable
fun WeeklyFoodView(
    recetas: List<Recipe>,
    userName: String,
    userDiet: String,
    onNavigateBack: () -> Unit
) {
    var expandedRecipeId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Minuta Semanal",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Usuario: $userName\nDieta: $userDiet",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            OutlinedButton(onClick = onNavigateBack) {
                Text("Volver")
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 300.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(recetas) { receta ->
                val isExpanded = expandedRecipeId == receta.id

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            expandedRecipeId = if (isExpanded) null else receta.id
                        }
                        .animateContentSize(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = receta.name,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )

                        if (isExpanded) {
                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Ingredientes:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyLarge)
                            Text(text = receta.ingredients, style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Preparación:", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyLarge)
                            Text(text = receta.instructions, style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(12.dp))

                            Text("Aporte Nutricional (por porción):", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyLarge)
                            Text(text = receta.nutritionFact, style = MaterialTheme.typography.bodyMedium)
                        } else {
                            Text(
                                text = "Toca para ver la receta...",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}