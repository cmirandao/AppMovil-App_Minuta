package com.example.app_minuta.data

data class Recipe(
    val id: Int,
    val dayOfWeek: String,
    val name: String,
    val ingredients: String,
    val instructions: String,
    val nutritionFact: String,
    val diet: String
)

class RecipeRepository {
    private val weeklyFoodMenu: Array<Recipe> = arrayOf(
        // ==========================================
        // DIETA NORMAL
        // ==========================================
        Recipe(
            id = 1,
            dayOfWeek = "Lunes",
            name = "Pollo asado con puré rústico",
            ingredients = "• 1 tuto de pollo entero\n• 3 papas medianas\n• 1 cucharada de mantequilla\n• Sal, orégano y ajo a gusto\n• 1/2 taza de leche",
            instructions = "1. Adoba el pollo con sal, ajo y orégano.\n2. Hornea a 200°C por 45 minutos hasta que esté dorado.\n3. Hierve las papas con piel hasta que estén blandas.\n4. Muele las papas agregando la mantequilla y la leche tibia hasta lograr la consistencia deseada.",
            nutritionFact = "Calorías: ~350 kcal\nProteínas: 25g\nCarbohidratos: 30g",
            diet = "Normal"
        ),
        Recipe(
            id = 2,
            dayOfWeek = "Martes",
            name = "Pescado a la plancha",
            ingredients = "• 1 filete de pescado blanco (merluza o reineta)\n• 1 taza de arroz\n• 1 diente de ajo\n• Jugo de medio limón\n• Aceite de oliva y sal",
            instructions = "1. Lava el arroz y sofríelo con el ajo picado. Agrega 2 tazas de agua hirviendo y cocina por 20 minutos a fuego mínimo.\n2. Aliña el filete de pescado con sal y el jugo de limón.\n3. Calienta un sartén con unas gotas de aceite de oliva y cocina el pescado por 4 minutos por lado.",
            nutritionFact = "Calorías: ~320 kcal\nProteínas: 28g\nCarbohidratos: 45g",
            diet = "Normal"
        ),
        Recipe(
            id = 3,
            dayOfWeek = "Miércoles",
            name = "Lomo de cerdo con papas",
            ingredients = "• 150g de lomo de cerdo\n• 2 papas cortadas en cubos\n• 1 cebolla picada fina\n• 1 cucharada de aceite\n• Pimienta y sal",
            instructions = "1. Saltea la cebolla en un sartén hasta que esté transparente.\n2. Agrega el cerdo cortado en tiras y cocina hasta dorar.\n3. Fríe u hornea los cubos de papa y mézclalos con la carne antes de servir.",
            nutritionFact = "Calorías: ~400 kcal\nProteínas: 30g\nCarbohidratos: 35g",
            diet = "Normal"
        ),
        Recipe(
            id = 4,
            dayOfWeek = "Jueves",
            name = "Cazuela de vacuno",
            ingredients = "• 200g de tapapecho de vacuno\n• 1 trozo de zapallo\n• 1 papa entera\n• 1 trozo de choclo\n• Un puñado de porotos verdes\n• Orégano, sal y comino",
            instructions = "1. Sella la carne en una olla con un poco de aceite y los aliños.\n2. Agrega agua hirviendo y cocina la carne por 40 minutos.\n3. Añade la papa, el zapallo y el choclo. Cocina por 15 minutos más.\n4. Incorpora los porotos verdes al final y apaga el fuego tras 5 minutos.",
            nutritionFact = "Calorías: ~380 kcal\nProteínas: 26g\nCarbohidratos: 35g",
            diet = "Normal"
        ),
        Recipe(
            id = 5,
            dayOfWeek = "Viernes",
            name = "Salmón al horno",
            ingredients = "• 1 filete de salmón\n• Eneldo fresco\n• Jugo de limón\n• 1 taza de tomates cherry\n• Aceite de oliva",
            instructions = "1. Precalienta el horno a 180°C.\n2. Coloca el salmón en una fuente, rocía con aceite de oliva, limón y espolvorea el eneldo.\n3. Añade los tomates cherry alrededor del pescado.\n4. Hornea por 15 a 20 minutos hasta que el salmón esté cocido.",
            nutritionFact = "Calorías: ~300 kcal\nProteínas: 32g\nCarbohidratos: 10g",
            diet = "Normal"
        ),

        // ==========================================
        // DIETA VEGETARIANA
        // ==========================================
        Recipe(
            id = 6,
            dayOfWeek = "Lunes",
            name = "Tortilla de espinacas",
            ingredients = "• 2 huevos grandes\n• 1 taza de hojas de espinaca lavadas\n• 1/4 cebolla picada\n• Sal y pimienta\n• 1 cucharadita de aceite",
            instructions = "1. Saltea la cebolla en el aceite hasta que dore.\n2. Agrega la espinaca y cocina por 2 minutos hasta que reduzca su tamaño.\n3. Bate los huevos en un bol, añade el sofrito, sal y pimienta.\n4. Vierte la mezcla en un sartén caliente y cocina 3 minutos por lado.",
            nutritionFact = "Calorías: ~210 kcal\nProteínas: 14g\nCarbohidratos: 5g",
            diet = "Vegetariana"
        ),
        Recipe(
            id = 7,
            dayOfWeek = "Martes",
            name = "Lentejas guisadas",
            ingredients = "• 1 taza de lentejas remojadas\n• 1/2 zanahoria rallada\n• 1/4 de cebolla y 1 diente de ajo\n• 1 trocito de zapallo\n• Pimentón rojo a gusto",
            instructions = "1. Prepara un sofrito con la cebolla, el ajo, la zanahoria y el pimentón.\n2. Agrega las lentejas y el zapallo en cubos.\n3. Cubre con agua hirviendo y cocina a fuego medio por 35-40 minutos.\n4. Sirve caliente. Opcional: acompañar con arroz.",
            nutritionFact = "Calorías: ~320 kcal\nProteínas: 18g\nCarbohidratos: 40g",
            diet = "Vegetariana"
        ),
        Recipe(
            id = 8,
            dayOfWeek = "Miércoles",
            name = "Ensalada de quinoa",
            ingredients = "• 1 taza de quinoa cocida\n• 1/2 pepino en cubos\n• 1 tomate picado\n• 1/2 palta\n• Jugo de limón, aceite de oliva y sal",
            instructions = "1. Lava bien la quinoa antes de cocerla (1 taza de quinoa por 2 de agua).\n2. Una vez fría, mézclala en un bol con el tomate y el pepino.\n3. Aliña con abundante jugo de limón, aceite y sal.\n4. Corona con la palta rebanada antes de servir.",
            nutritionFact = "Calorías: ~340 kcal\nProteínas: 12g\nCarbohidratos: 45g",
            diet = "Vegetariana"
        ),
        Recipe(
            id = 9,
            dayOfWeek = "Jueves",
            name = "Hamburguesa de garbanzos",
            ingredients = "• 1 taza de garbanzos cocidos\n• 2 cucharadas de harina de avena\n• Perejil fresco picado\n• Ajo en polvo, comino y sal\n• Pan de hamburguesa y vegetales a elección",
            instructions = "1. Procesa o muele los garbanzos con un tenedor hasta formar un puré rústico.\n2. Mezcla con el perejil, los aliños y la avena hasta obtener una masa moldeable.\n3. Forma las hamburguesas con las manos.\n4. Dóralas en un sartén con una gota de aceite por 4 minutos por lado y arma tu pan.",
            nutritionFact = "Calorías: ~280 kcal\nProteínas: 15g\nCarbohidratos: 42g",
            diet = "Vegetariana"
        ),
        Recipe(
            id = 10,
            dayOfWeek = "Viernes",
            name = "Curry de verduras",
            ingredients = "• 1 taza de flores de brócoli\n• 1 zanahoria en rodelas\n• 1/2 taza de leche de coco\n• 1 cucharada de curry en polvo\n• Arroz basmati para acompañar",
            instructions = "1. Saltea el brócoli y la zanahoria en un sartén hondo o wok.\n2. Espolvorea el curry y revuelve bien para tostar las especias por 1 minuto.\n3. Vierte la leche de coco, baja el fuego y tapa.\n4. Cocina por 12 minutos hasta que las verduras estén al dente y sirve con arroz.",
            nutritionFact = "Calorías: ~250 kcal\nProteínas: 6g\nCarbohidratos: 28g",
            diet = "Vegetariana"
        ),

        // ==========================================
        // DIETA SIN GLUTEN
        // ==========================================
        Recipe(
            id = 11,
            dayOfWeek = "Lunes",
            name = "Fideos de arroz con pollo",
            ingredients = "• 1 porción de fideos de arroz (Rice Noodles)\n• 150g de pechuga de pollo en tiras\n• Pimentón rojo y verde en juliana\n• Salsa de soya (certificada Sin Gluten)\n• Semillas de sésamo",
            instructions = "1. Hidrata los fideos de arroz en agua caliente según las instrucciones del empaque.\n2. Saltea el pollo en un wok hasta que esté dorado.\n3. Agrega los pimentones y saltea un par de minutos más.\n4. Incorpora los fideos escurridos, baña con la salsa de soya sin gluten, mezcla y decora con sésamo.",
            nutritionFact = "Calorías: ~360 kcal\nProteínas: 28g\nCarbohidratos: 50g",
            diet = "Sin Gluten"
        ),
        Recipe(
            id = 12,
            dayOfWeek = "Martes",
            name = "Risotto de champiñones",
            ingredients = "• 1/2 taza de arroz arborio (o arroz normal)\n• 1 taza de champiñones laminados\n• 1/4 cebolla blanca picada\n• Caldo de verduras natural\n• Queso parmesano rallado",
            instructions = "1. Saltea la cebolla y luego agrega los champiñones.\n2. Incorpora el arroz seco y tuéstalo por 2 minutos.\n3. Ve agregando el caldo de verduras caliente un cucharón a la vez, revolviendo constantemente hasta que el líquido se absorba.\n4. Cuando el arroz esté cremoso y al dente, apaga el fuego y mezcla con el queso parmesano.",
            nutritionFact = "Calorías: ~310 kcal\nProteínas: 10g\nCarbohidratos: 45g",
            diet = "Sin Gluten"
        ),
        Recipe(
            id = 13,
            dayOfWeek = "Miércoles",
            name = "Tacos en lechuga",
            ingredients = "• 150g de carne molida magra\n• 4 hojas de lechuga costina grandes e intactas\n• 1/2 tomate picado\n• 1/2 palta molida\n• Comino, sal y ají color",
            instructions = "1. Sofrie la carne molida aliñada con comino, sal y ají color hasta que esté bien cocida.\n2. Lava y seca muy bien las hojas de lechuga.\n3. Utiliza las hojas de lechuga como si fueran tortillas de taco.\n4. Rellena cada hoja con la carne caliente, el tomate fresco y la palta.",
            nutritionFact = "Calorías: ~290 kcal\nProteínas: 25g\nCarbohidratos: 12g",
            diet = "Sin Gluten"
        ),
        Recipe(
            id = 14,
            dayOfWeek = "Jueves",
            name = "Pastel de choclo",
            ingredients = "• 1 taza de pasta de choclo (maíz molido)\n• Pino de vacuno (carne molida y cebolla)\n• 1 trozo de pollo cocido\n• 1/4 de huevo duro y aceituna\n• Hojas de albahaca fresca",
            instructions = "1. Cocina la pasta de choclo en una olla con un poco de leche o agua y hojas de albahaca hasta que espese.\n2. En una fuente de greda o apta para horno, coloca una base de pino de carne.\n3. Agrega el pollo, el huevo duro y la aceituna.\n4. Cubre todo con la pasta de choclo y hornea a 200°C hasta dorar la superficie.",
            nutritionFact = "Calorías: ~420 kcal\nProteínas: 22g\nCarbohidratos: 55g",
            diet = "Sin Gluten"
        ),
        Recipe(
            id = 15,
            dayOfWeek = "Viernes",
            name = "Pescado con ensalada fresca",
            ingredients = "• 1 filete de pescado (reineta o salmón)\n• Mix de hojas verdes (lechuga, rúcula)\n• 1/2 taza de tomates cherry\n• 1 cucharada de nueces picadas\n• Limón y aceite de oliva",
            instructions = "1. Calienta un sartén o plancha con una gota de aceite. Cocina el pescado aliñado con sal por 4 minutos por lado.\n2. Prepara una cama de hojas verdes y tomates cherry en un plato.\n3. Coloca el pescado caliente sobre la ensalada.\n4. Aliña todo con limón, aceite de oliva y espolvorea las nueces picadas por encima.",
            nutritionFact = "Calorías: ~330 kcal\nProteínas: 30g\nCarbohidratos: 15g",
            diet = "Sin Gluten"
        )
    )

    fun getRecipeById(id: Int): Recipe? {
        return weeklyFoodMenu.find { it.id == id }
    }

    fun getRecipesByDiet(diet: String): Array<Recipe> {
        return weeklyFoodMenu.filter { it.diet == diet }.toTypedArray()
    }
}

val recipeRepository = RecipeRepository()