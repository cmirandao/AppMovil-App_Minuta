package com.example.app_minuta.data

data class Recipe(
    val id: Int,
    val name: String,
    val nutritionFact: String
)

val weeklyFoodMenu = arrayOf(
    Recipe(1, "Pollo al horno con verduras", "Alto en proteínas y bajo en grasas."),
    Recipe(2, "Lentejas guisadas", "Excelente fuente de hierro y fibra."),
    Recipe(3, "Pescado a la plancha", "Rico en Omega 3 y vitaminas."),
    Recipe(4, "Ensalada de quinoa", "Aporte de carbohidratos complejos y vitaminas."),
    Recipe(5, "Tortilla de espinacas", "Buena fuente de calcio y vitaminas.")
)