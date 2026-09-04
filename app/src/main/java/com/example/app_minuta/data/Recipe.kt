package com.example.app_minuta.data

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val nutritionFact: String,
    val diet: String
)

class RecipeRepository {
    private val weeklyFoodMenu = listOf(
        // ==========================================
        // DIETA NORMAL
        // ==========================================
        Recipe(
            1,
            "Pollo asado con puré rústico",
            "• 1 tuto de pollo\n• 2 papas medianas\n• Sal y orégano",
            "Hornea a 200°C por 40 min. Muele las papas.",
            "Calorías: ~350 kcal",
            "Normal"
        ),
        Recipe(
            2,
            "Pescado a la plancha",
            "• 1 filete de pescado\n• 1/2 taza de arroz\n• Limón",
            "Cocina el arroz. Aliña el pescado y cocina a la plancha.",
            "Calorías: ~320 kcal",
            "Normal"
        ),
        Recipe(
            3,
            "Lomo de cerdo con papas",
            "• 150g lomo de cerdo\n• 2 papas",
            "Saltea las papas. Sella el lomo.",
            "Calorías: ~400 kcal",
            "Normal"
        ),
        Recipe(
            4,
            "Cazuela de vacuno",
            "• Tapapecho\n• Zapallo\n• Papa",
            "Cuece la carne. Agrega verduras.",
            "Calorías: ~380 kcal",
            "Normal"
        ),
        Recipe(
            5,
            "Salmón al horno",
            "• Salmón\n• Hojas verdes",
            "Hornea el salmón a 180°C. Sirve con ensalada.",
            "Calorías: ~300 kcal",
            "Normal"
        ),
        // ==========================================
        // DIETA VEGETARIANA
        // ==========================================
        Recipe(
            6,
            "Tortilla de espinacas",
            "• 2 huevos\n• Espinacas\n• Champiñones",
            "Saltea vegetales. Mezcla con huevos y cocina.",
            "Calorías: ~210 kcal",
            "Vegetariana"
        ),
        Recipe(
            7,
            "Lentejas guisadas",
            "• 1 taza lentejas\n• Zapallo\n• Cebolla",
            "Haz sofrito, agrega lentejas y cocina 35 min.",
            "Calorías: ~320 kcal",
            "Vegetariana"
        ),
        Recipe(
            8,
            "Ensalada de quinoa",
            "• Quinoa cocida\n• Palta\n• Choclo",
            "Mezcla quinoa fría con palta y choclo. Aliña.",
            "Calorías: ~340 kcal",
            "Vegetariana"
        ),
        Recipe(
            9,
            "Hamburguesa de garbanzos",
            "• Garbanzos\n• Avena",
            "Muele garbanzos, mezcla con avena, forma y dora.",
            "Calorías: ~280 kcal",
            "Vegetariana"
        ),
        Recipe(
            10,
            "Curry de verduras",
            "• Brócoli\n• Leche de coco\n• Curry",
            "Saltea verduras, agrega leche de coco y curry.",
            "Calorías: ~250 kcal",
            "Vegetariana"
        ),
        // ==========================================
        // DIETA SIN GLUTEN
        // ==========================================
        Recipe(
            11,
            "Fideos de arroz con pollo",
            "• Fideos de arroz\n• Pollo\n• Zapallito",
            "Hidrata fideos. Saltea pollo y mezcla.",
            "Calorías: ~360 kcal",
            "Sin Gluten"
        ),
        Recipe(
            12,
            "Risotto de champiñones",
            "• Arroz arborio\n• Champiñones\n• Caldo",
            "Saltea champiñones, agrega arroz y caldo poco a poco.",
            "Calorías: ~310 kcal",
            "Sin Gluten"
        ),
        Recipe(
            13,
            "Tacos en hojas de lechuga",
            "• Carne molida\n• Lechuga costina",
            "Cocina carne. Sirve en hojas de lechuga.",
            "Calorías: ~290 kcal",
            "Sin Gluten"
        ),
        Recipe(
            14,
            "Pastel de choclo",
            "• Pollo\n• Pino de carne\n• Pasta de choclo",
            "Arma pastel en greda y hornea a 200°C.",
            "Calorías: ~420 kcal",
            "Sin Gluten"
        ),
        Recipe(
            15,
            "Pescado con quinoa",
            "• Pescado\n• Quinoa",
            "Cocina quinoa. Pescado a la plancha.",
            "Calorías: ~330 kcal",
            "Sin Gluten"
        )
    )

    fun getRecipesByDiet(diet: String): List<Recipe> {
        return weeklyFoodMenu.filter { it.diet == diet }
    }
}

val recipeRepository = RecipeRepository()