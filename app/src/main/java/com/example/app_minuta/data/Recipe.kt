package com.example.app_minuta.data

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val nutritionFact: String,
    val diet: String
)

val weeklyFoodMenu = listOf(
    // ==========================================
    // DIETA NORMAL
    // ==========================================
    Recipe(1, "Pollo asado con puré rústico",
        "• 1 tuto de pollo\n• 2 papas medianas\n• 1 cucharadita de mantequilla\n• Sal y orégano",
        "1. Condimenta el pollo y hornea a 200°C por 40 min.\n2. Hierve las papas con piel hasta que ablanden.\n3. Muele las papas rústicamente agregando la mantequilla y sal.",
        "Calorías: ~350 kcal\nProteínas: 25g\nGrasas: 12g\nCarbohidratos: 30g", "Normal"),
    Recipe(2, "Pescado a la plancha con arroz",
        "• 1 filete de pescado (reineta o merluza)\n• 1/2 taza de arroz\n• Limón y sal",
        "1. Cocina el arroz con 1 taza de agua hirviendo por 20 min.\n2. Aliña el pescado con sal y limón.\n3. Cocina a la plancha 4 min por lado.",
        "Calorías: ~320 kcal\nProteínas: 22g\nGrasas: 5g\nCarbohidratos: 45g", "Normal"),
    Recipe(3, "Lomo de cerdo con papas doradas",
        "• 150g de lomo de cerdo\n• 2 papas cortadas en cubos\n• 1 cucharada de aceite\n• Ajo y sal",
        "1. Saltea los cubos de papa en aceite hasta dorar.\n2. En otro sartén, sella el lomo de cerdo con ajo hasta cocer bien por el centro.",
        "Calorías: ~400 kcal\nProteínas: 28g\nGrasas: 15g\nCarbohidratos: 35g", "Normal"),
    Recipe(4, "Cazuela de vacuno",
        "• 1 trozo de tapapecho\n• 1 trozo de zapallo\n• 1 papa\n• 1/2 choclo\n• Verduras surtidas",
        "1. En una olla a presión, cuece la carne por 30 min.\n2. Agrega la papa, el zapallo y el choclo. Cocina por 15 min más hasta que todo esté blando.",
        "Calorías: ~380 kcal\nProteínas: 24g\nGrasas: 10g\nCarbohidratos: 40g", "Normal"),
    Recipe(5, "Salmón al horno con ensalada",
        "• 1 porción de salmón\n• Hojas verdes mixtas\n• 1/2 tomate\n• Aceite de oliva",
        "1. Hornea el salmón a 180°C por 15 minutos.\n2. Prepara la ensalada fresca y aliña con aceite de oliva y sal.",
        "Calorías: ~300 kcal\nProteínas: 26g\nGrasas: 18g\nCarbohidratos: 5g", "Normal"),

    // ==========================================
    // DIETA VEGETARIANA
    // ==========================================
    Recipe(6, "Tortilla de espinacas y champiñones",
        "• 2 huevos\n• 1 taza de espinacas frescas\n• 1/2 taza de champiñones laminados\n• 1 cdta de aceite de oliva\n• Sal a gusto",
        "1. Saltea los champiñones y espinacas en aceite por 2 min.\n2. Bate los huevos con sal y vierte sobre los vegetales.\n3. Cocina a fuego medio por 3 min, voltea y dora el otro lado.",
        "Calorías: ~210 kcal\nProteínas: 14g\nGrasas saludables: 15g\nCarbohidratos: 4g\nAporta hierro y vitamina D.", "Vegetariana"),
    Recipe(7, "Lentejas guisadas con zapallo",
        "• 1 taza de lentejas remojadas\n• 1 trozo de zapallo en cubos\n• 1/2 cebolla picada\n• Zanahoria rallada",
        "1. Haz un sofrito con cebolla y zanahoria.\n2. Agrega las lentejas, el zapallo y cubre con agua.\n3. Cocina a fuego medio por 35 minutos.",
        "Calorías: ~320 kcal\nProteínas: 18g\nGrasas: 2g\nCarbohidratos: 55g\nExcelente fuente de fibra.", "Vegetariana"),
    Recipe(8, "Ensalada de quinoa con palta y choclo",
        "• 1/2 taza de quinoa cocida\n• 1/2 palta picada\n• 1/3 taza de choclo\n• Limón y cilantro",
        "1. Mezcla la quinoa fría con el choclo y la palta.\n2. Aliña con abundante jugo de limón, sal y cilantro fresco.",
        "Calorías: ~340 kcal\nProteínas: 8g\nGrasas: 14g\nCarbohidratos: 45g", "Vegetariana"),
    Recipe(9, "Hamburguesa de garbanzos",
        "• 1 taza de garbanzos cocidos\n• 1/4 cebolla picada\n• 2 cdas de avena\n• Sal y comino",
        "1. Procesa o muele los garbanzos con un tenedor.\n2. Mezcla con la cebolla, avena y condimentos.\n3. Forma la hamburguesa y dora en un sartén con una gota de aceite.",
        "Calorías: ~280 kcal\nProteínas: 12g\nGrasas: 5g\nCarbohidratos: 42g", "Vegetariana"),
    Recipe(10, "Curry de verduras con leche de coco",
        "• Brócoli, zanahoria y coliflor\n• 1/2 taza de leche de coco\n• 1 cdta de curry",
        "1. Saltea las verduras al dente.\n2. Agrega el curry y la leche de coco. Deja reducir por 10 minutos a fuego lento.",
        "Calorías: ~250 kcal\nProteínas: 5g\nGrasas: 18g\nCarbohidratos: 15g", "Vegetariana"),

    // ==========================================
    // DIETA SIN GLUTEN
    // ==========================================
    Recipe(11, "Fideos de arroz con pollo y verduras",
        "• 100g de fideos de arroz\n• 100g de pechuga de pollo\n• Zapallito italiano en juliana\n• Salsa de soya sin gluten",
        "1. Hidrata los fideos de arroz en agua caliente.\n2. Saltea el pollo con el zapallito.\n3. Mezcla todo con la soya sin gluten.",
        "Calorías: ~360 kcal\nProteínas: 25g\nGrasas: 4g\nCarbohidratos: 55g", "Sin Gluten"),
    Recipe(12, "Risotto de champiñones",
        "• 1/2 taza de arroz arborio (o normal)\n• 1 taza de champiñones\n• Caldo de verduras natural",
        "1. Saltea los champiñones.\n2. Agrega el arroz y vierte el caldo poco a poco revolviendo constantemente hasta que esté cremoso.",
        "Calorías: ~310 kcal\nProteínas: 6g\nGrasas: 5g\nCarbohidratos: 58g", "Sin Gluten"),
    Recipe(13, "Tacos en hojas de lechuga",
        "• 150g de carne molida magra\n• Hojas de lechuga costina (como tortilla)\n• Tomate en cubitos\n• 1/4 de palta",
        "1. Cocina la carne molida con sal y orégano.\n2. Sirve la carne sobre las hojas de lechuga. Agrega tomate y palta.",
        "Calorías: ~290 kcal\nProteínas: 28g\nGrasas: 16g\nCarbohidratos: 8g", "Sin Gluten"),
    Recipe(14, "Pastel de choclo tradicional",
        "• Pera de pollo cocida\n• Pino de carne (sin harina)\n• Pasta de choclo molido",
        "1. En una fuente de greda pon el pino y el pollo.\n2. Cubre con la pasta de choclo.\n3. Hornea a 200°C hasta que dore la cubierta.",
        "Calorías: ~420 kcal\nProteínas: 25g\nGrasas: 12g\nCarbohidratos: 50g", "Sin Gluten"),
    Recipe(15, "Pescado a la plancha con quinoa",
        "• 1 filete de pescado\n• 1/2 taza de quinoa\n• Sal, pimienta y limón",
        "1. Cocina la quinoa en 1 taza de agua hirviendo.\n2. Cocina el pescado a la plancha 4 min por lado.",
        "Calorías: ~330 kcal\nProteínas: 26g\nGrasas: 6g\nCarbohidratos: 40g", "Sin Gluten")
)