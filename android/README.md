# MOVIE Android — módulo móvil Java

Código fuente de apoyo a la evidencia GA7-220501096-AA3-EV02. La aplicación consulta el catálogo, inicia sesión con JWT y gestiona favoritos contra la API MOVIE.

## Compatibilidad y límites
En el informe del proyecto se observa la ruta **POST /api/auth/login** con JSON `correo` y `password`, y **POST /api/favoritos/2** con autenticación Bearer. En `Config.java` la ruta `GET /api/movies` es **provisional**; comprueba el código real del backend y cámbiala si corresponde. También valida la estructura JSON de las respuestas y las rutas GET/DELETE de favoritos. No se ha compilado ni probado contra el backend del usuario.

## Abrir
- Android Studio + Android SDK Platform 35 y JDK 17. Abre esta carpeta con **Open**.
- Espera a que se sincronicen las dependencias Gradle, con Internet.
- Inicia el backend MOVIE y MySQL. El backend debe escuchar en puerto 3000.
- En el emulador `10.0.2.2:3000` apunta a `localhost:3000` de Windows. Para teléfono físico cambia por IP local del computador y permite acceso en firewall.
- Si Android Studio te solicita Gradle wrapper, permite generar/instalar Gradle 8.7 desde el IDE (este ZIP no incluye wrapper ni binarios).

## Seguridad
La app usa tráfico HTTP sin cifrar y almacena token de forma simple: únicamente para pruebas locales educativas. No emplear en producción. No incluir contraseñas ni JWT visibles en capturas.
