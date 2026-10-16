# MOVIE – Evidencia GA7-220501096-AA2-EV01

Proyecto formativo de gestión y consulta de películas. Implementa el módulo **Gestión de Películas** con Java 25 (LTS), JDBC, MySQL y CRUD completo.

## Archivos principales
- `pom.xml`: dependencias MySQL Connector/J y ejecución Maven.
- `database/movie.sql`: esquema de base de datos, relaciones y catálogos de ejemplo.
- `src/main/java/com/movie/`: aplicación por capas.
- `docs/`: estándar, matriz de trazabilidad, casos de prueba y evidencia Git.

## Instalación
1. Instalar JDK 25 (LTS), Maven y MySQL 8.
2. Ejecutar `database/movie.sql` desde MySQL Workbench o consola: `mysql -u root -p < database/movie.sql`.
3. Configurar `MOVIE_DB_URL`, `MOVIE_DB_USER`, `MOVIE_DB_PASSWORD` según el equipo.
4. En la raíz ejecutar `mvn clean compile exec:java`.
5. Utilizar el menú 1 crear, 2 listar, 3 buscar, 4 actualizar, 5 eliminar, 0 salir.

## Configuración ejemplo (PowerShell)
```powershell
$env:MOVIE_DB_URL='jdbc:mysql://localhost:3306/movie?serverTimezone=UTC'
$env:MOVIE_DB_USER='root'
$env:MOVIE_DB_PASSWORD='TU_CONTRASENA_LOCAL'
mvn clean compile exec:java
```

## Observaciones
- Los géneros iniciales son 1 Acción, 2 Drama, 3 Ciencia ficción; los directores 1 Christopher Nolan y 2 Greta Gerwig.
- No incluye un servidor MySQL ni credenciales; se ejecuta localmente después de preparar el entorno.
- La verificación de integración se documenta en `docs/PRUEBAS_MANUALES.md`; debe ejecutarse y registrarse en el computador de entrega.
- El profesor usa SICE como referencia de arquitectura con Node.js. La rúbrica adjunta pide JDBC, razón por la que este módulo se implementa con Java.
