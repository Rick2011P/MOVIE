# Estándar de codificación de MOVIE

- Java 25 (LTS), archivos UTF-8, paquetes en minúsculas `com.movie.*`.
- Clases `PascalCase`; variables y métodos `camelCase`; constantes `UPPER_SNAKE_CASE`.
- Separación de responsabilidades: `app`, `service`, `repository`, `model`, `config`.
- Acceso a SQL únicamente desde Repository; `PreparedStatement` con parámetros.
- `try-with-resources` para cerrar conexiones, consultas y resultados.
- Validaciones de negocio en Service y mensajes de error legibles.
- Credenciales externas por variables de entorno, nunca en commits.
- Commits de Git con verbos claros y archivos generados ignorados.
