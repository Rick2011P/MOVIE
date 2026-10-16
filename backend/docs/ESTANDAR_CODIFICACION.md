# Estándar de codificación MOVIE
- CommonJS, 2 espacios, camelCase para funciones/variables y nombres de archivos por responsabilidad.
- Separación Route → Controller → Service → Repository → MySQL en usuarios y películas; validadores independientes.
- Sentencias SQL parametrizadas `?` para evitar inyección SQL.
- Códigos HTTP 200/201/204/400/401/403/404/409/500 y mensajes JSON uniformes.
- Contraseñas bcrypt; JWT firmado; validación de rol y estado contra MySQL por solicitud.
- Eliminar lógicamente los registros, conservando integridad referencial.
- Variables sensibles en `.env` excluido por `.gitignore`.
- La ruta favoritos se implementa directamente como extensión sencilla; puede refactorizarse a capas en la siguiente iteración.
