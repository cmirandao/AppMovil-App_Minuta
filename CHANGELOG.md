# Changelog
Todos los cambios notables de la aplicación "Minuta Nutricional" serán documentados en este archivo.

## [2.0.0] - 2026-09-18

### Añadido
- **Extensiones KTX (Palette):** Integración de la librería `androidx.palette:palette-ktx` en la nueva `RecetaView` para extraer los colores dominantes y vibrantes de la imagen del plato, aplicándolos dinámicamente a la UI (Palette Texts y Palette Widgets).
- **Vistas Independientes:** Creación de una vista dedicada para el detalle de la receta (`RecetaView`), separando la lógica del listado y mejorando el enfoque en la lectura.

### Modificado
- **Estructura Estricta de Datos:** Refactorización de la colección de recetas en `RecipeRepository`. Se reemplazó `List` por un `Array<Recipe>` de tamaño fijo (`arrayOf`), dando cumplimiento literal al requerimiento de la estructura de datos.
- **Usabilidad y Accesibilidad:** Transformación de `WeeklyFoodView` en un "HomeMenú" limpio tipo índice. Se hizo explícita la relación "Día de la semana -> Receta" en cada tarjeta y se agregaron botones de acción directa ("Ver Receta") para facilitar el uso a personas de baja habilidad informática.
- **Limpieza del Back Stack:** Optimización del enrutador (`NavHost`) implementando `popUpTo` en la transición de Login a HomeMenú, y al Cerrar Sesión, evitando retornos erróneos en el historial de navegación.
- **Refinamiento de UI y Contenido:** Alineación adaptativa de botones en las tarjetas de la minuta y redacción completa de las 15 recetas (ingredientes, instrucciones y nutrición) para su correcta visualización en la nueva vista detallada.

## [1.2.0] - 2026-09-03

### Añadido
- **Navigation Compose:** Implementación de navegación profesional mediante `NavHost` y `NavController`.
- **Rutas Tipadas (Sealed Classes):** Creación de la jerarquía de clases `Screen` para definir rutas seguras, eliminando el uso de `Strings` duros propensos a errores y permitiendo el paso de argumentos (`username`).
- **Nivel de Actividad Física:** Integración del atributo `activityLevel` al modelo `User`, capturándolo en el registro y almacenándolo en la colección.

### Modificado
- **Arquitectura POO (Repositorios):** Separación de la capa de datos y la capa visual. Se crearon las clases `UserRepository` y `RecipeRepository` para encapsular las colecciones (`private val`) y gestionar la lógica de negocio mediante métodos (`addUser`, `validateCredentials`, `getRecipesByDiet`).
- **Grilla Adaptativa Dinámica:** Refactorización de `WeeklyFoodView` utilizando `LocalConfiguration.current` para calcular el ancho de la pantalla y variar automáticamente las columnas de `LazyVerticalGrid` (1 columna en vertical, 2 columnas en horizontal/tablets).
- **Lógica de Estado:** Eliminación de la gestión manual de vistas (`actualView`) en favor del enrutador nativo de Jetpack Compose.

## [1.1.0] - 2026-08-29

### Arreglado
- **Recuperación de Contraseña:** Se implementó la validación estricta utilizando la función `.any {}` de Kotlin para verificar si el usuario existe en la colección `registeredUsers` antes de simular el envío de instrucciones. Se integraron mensajes de retroalimentación dinámicos (éxito/error) según el resultado de la búsqueda.

### Añadido
- **Visibilidad de Contraseñas:** Integración de íconos interactivos (`Visibility`, `VisibilityOff`) en las vistas de Login y Registro para mostrar u ocultar la contraseña.
- **Navegación Mejorada (UX):** Incorporación de botones "Volver" en las vistas de Recuperación de Contraseña y Minuta Semanal para evitar flujos sin salida.
- **Validaciones Estrictas (Kotlin):** Implementación de reglas de negocio en el Registro utilizando `when` y `.any {}` para exigir un mínimo de 6 caracteres, mayúsculas, minúsculas y números.
- **Control de Límite de Usuarios:** Lógica de validación para restringir el registro a un máximo de 5 usuarios almacenados en la colección (`mutableListOf`), bloqueando además la duplicidad de cuentas.

### Modificado
- **Interfaz de Minuta Semanal:** Rediseño completo reemplazando el `DropdownMenu` por una grilla adaptativa (`LazyVerticalGrid`), organizando la información nutricional de forma mucho más accesible.
- **Adaptabilidad Responsiva:** Aplicación del modificador `verticalScroll` en las vistas de Login y Registro para prevenir el corte de información al desplegar el teclado en pantallas pequeñas.
- **Lógica de Login:** Refactorización de la validación de credenciales para comprobar coincidencias directamente contra la colección `registeredUsers` usando funciones de Kotlin.

## [0.1.0] - 2026-08-16
### Agregado
- Inicialización del proyecto Android con Kotlin y Jetpack Compose.
- Arquitectura base implementando separación de problemas (capas `data` y `ui`).
- Vista de inicio de sesión (`LoginView`) con `PasswordVisualTransformation`.
- Vista de registro (`RegisterView`) con inputs, radio buttons y checkboxes.
- Integración de ComboBox (`ExposedDropdownMenuBox`) en la vista de minuta para filtrar recetas por día.
- `Recipe.kt`: Clase de datos y arreglo con 5 recetas saludables incluyendo tips nutricionales e instrucciones.

### Cambiado
- Refactorización de `MainActivity.kt` para orquestar la navegación y simular la sesión de usuario mediante *State Hoisting*.

