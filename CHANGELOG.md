# Changelog
Todos los cambios notables de la aplicación "Minuta Nutricional" serán documentados en este archivo.

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

