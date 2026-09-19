package com.example.app_minuta.ui.views

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.example.app_minuta.R
import com.example.app_minuta.data.Recipe
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap

@Composable
fun RecetaView(
    receta: Recipe?,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    var vibrantColor by remember { mutableStateOf(Color(0xFF4CAF50)) }
    var darkVibrantColor by remember { mutableStateOf(Color(0xFF2E7D32)) }
    val bitmap = remember(context) {
        try {
            ContextCompat.getDrawable(context, R.drawable.plato_comida)?.toBitmap()
        } catch (e: Exception) {
            null
        }
    }

    LaunchedEffect(bitmap) {
        bitmap?.let {
            Palette.from(it).generate { palette ->
                palette?.vibrantSwatch?.let { swatch ->
                    vibrantColor = Color(swatch.rgb)
                }
                palette?.darkVibrantSwatch?.let { swatch ->
                    darkVibrantColor = Color(swatch.rgb)
                }
            }
        }
    }

    if (receta == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: Receta no encontrada")
            Button(onClick = onNavigateBack) { Text("Volver") }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier.padding(bottom = 16.dp, top = 24.dp)
        ) {
            Text("⬅ Volver a la Minuta")
        }

        Text(
            text = receta.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = darkVibrantColor,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "${receta.dayOfWeek} | Dieta: ${receta.diet}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Imagen de ${receta.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(bottom = 16.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(vibrantColor.copy(alpha = 0.2f))
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Sube una imagen 'plato_comida' a res/drawable", color = darkVibrantColor)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = vibrantColor.copy(alpha = 0.1f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Ingredientes:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = darkVibrantColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = receta.ingredients, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Preparación:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = receta.instructions, style = MaterialTheme.typography.bodyLarge)
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Aporte Nutricional:",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = receta.nutritionFact, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}