package com.example.app_minuta.data

data class Recipe(
    val id: Int,
    val name: String,
    val nutritionFact: String,
    val instructions: String
)

val weeklyFoodMenu = arrayOf(
    Recipe(
        id = 1,
        name = "Pollo al horno con verduras",
        nutritionFact = "Alto en proteínas y bajo en grasas.",
        instructions = "Corta el pollo y verduras a elección. Condimenta con sal, orégano y un chorrito de aceite. Hornea a 200°C por 40 min."
    ),
    Recipe(
        id = 2,
        name = "Lentejas guisadas",
        nutritionFact = "Excelente fuente de hierro y fibra.",
        instructions = "Haz un sofrito básico con cebolla y zanahoria. Agrega lentejas, caldo y sal. Cocina a fuego medio por 35 min."
    ),
    Recipe(
        id = 3,
        name = "Pescado a la plancha",
        nutritionFact = "Rico en Omega 3 y vitaminas.",
        instructions = "Condimenta los filetes con sal, pimienta y limón. Calienta un sartén con aceite y cocina 4-5 min por lado."
    ),
    Recipe(
        id = 4,
        name = "Ensalada de quinoa",
        nutritionFact = "Aporte de carbohidratos complejos.",
        instructions = "Cocina la quinoa por 15 min. Deja enfriar y mezcla con tomate, choclo, cilantro, limón y aceite de oliva."
    ),
    Recipe(
        id = 5,
        name = "Tortilla de espinacas",
        nutritionFact = "Buena fuente de calcio y vitaminas.",
        instructions = "Saltea las espinacas. En un bol, bate 4 huevos, agrega la espinaca, sal y queso. Cocina 5 min por lado en un sartén."
    )
)