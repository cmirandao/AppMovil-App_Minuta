package com.example.app_minuta.ui.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_minuta.data.Recipe

@Composable
fun WeeklyFoodView(
    recetas: Array<Recipe>,
    userName: String,
    userDiet: String,
    onNavigateBack: () -> Unit,
    onRecipeClick: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val gridColumns = if (configuration.screenWidthDp >= 600) {
        GridCells.Fixed(2)
    } else {
        GridCells.Fixed(1)
    }

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
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Usuario: $userName\nDieta: $userDiet",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            OutlinedButton(onClick = onNavigateBack) {
                Text("Cerrar Sesión")
            }
        }

        LazyVerticalGrid(
            columns = gridColumns,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(recetas.toList()) { receta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRecipeClick(receta.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        // Relación Día -> Receta explícita
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
}