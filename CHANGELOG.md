# Changelog
Todos los cambios notables de la aplicación "Minuta Nutricional" serán documentados en este archivo.

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

