# Casos de prueba – MOVIE

Prerrequisitos: MySQL iniciado, `database/movie.sql` importado y credenciales configuradas.

| ID | Caso | Entrada / pasos | Resultado esperado | Estado |
|---|---|---|---|---|
| CP-01 | Conexión JDBC | Iniciar la aplicación con MySQL disponible | Menú y consultas disponibles | Por ejecutar en entorno MySQL |
| CP-02 | Crear | Opción 1, película válida, género 1 y director 1 | Mensaje con ID generado | Por ejecutar |
| CP-03 | Consultar | Opción 2 | Lista con la película creada | Por ejecutar |
| CP-04 | Buscar | Opción 3, ID creado | Datos correctos | Por ejecutar |
| CP-05 | Actualizar | Opción 4, ID creado y nuevos datos | Mensaje «Actualizada» | Por ejecutar |
| CP-06 | Eliminar | Opción 5, ID creado | Mensaje «Eliminada» | Por ejecutar |
| CP-07 | Validación | Crear con título vacío | Mensaje de error sin inserción | Por ejecutar |
| CP-08 | FK inválida | Crear con género inexistente | Error de integridad referencial | Por ejecutar |
| CP-09 | Consulta inexistente | Buscar ID 999999 | «No encontrada» | Por ejecutar |

**Nota:** No se declara ninguna prueba de integración como ejecutada sin disponer de una instancia MySQL configurada.
