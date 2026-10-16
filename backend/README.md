# MOVIE — backend REST (Node.js, Express, MySQL)

Proyecto académico adaptado a las clases SICE (partes 3 y 4), utilizando entidades MOVIE: usuario, película, género y favorito. Incluye CRUD de usuarios y películas, eliminación lógica, registro, inicio de sesión con bcrypt/JWT, permisos ADMIN/USUARIO, favoritos y pruebas unitarias de validación.

## Instalación en Windows
1. Instalar Node.js 20+, MySQL 8, Git y Postman.
2. Ejecutar `database/movie_web.sql` en MySQL Workbench (en una base de datos local de prueba).
3. Crear un usuario MySQL con permisos sobre `movie` y colocar sus credenciales en `.env` (copiar desde `.env.example`). Generar `JWT_SECRET` con una cadena aleatoria privada de al menos 32 caracteres.
4. Ejecutar `npm install`, luego `npm test`, y `npm run dev`.
5. Abrir `http://localhost:3000/api/health` y probar endpoints con Postman.
6. Registrar usuario `POST /api/auth/register` y entrar con `POST /api/auth/login`.
7. Para habilitar el primer administrador, ejecutar en MySQL `UPDATE usuario SET rol='ADMIN' WHERE correo='correo_de_tu_usuario';` únicamente en el entorno de desarrollo. Iniciar sesión nuevamente.

## Endpoints
| Método | Ruta | Permiso |
|---|---|---|
| GET | /api/health | Público |
| POST | /api/auth/register | Público; crea rol USUARIO |
| POST | /api/auth/login | Público |
| GET | /api/auth/me | Token |
| GET | /api/movies | Público; filtro ?genero=3 |
| GET | /api/movies/:id | Público |
| POST | /api/movies | ADMIN |
| PUT | /api/movies/:id | ADMIN |
| DELETE | /api/movies/:id | ADMIN; eliminación lógica |
| GET | /api/users | ADMIN |
| GET | /api/users/:id | ADMIN |
| PUT | /api/users/:id | ADMIN |
| PATCH | /api/users/:id/status | ADMIN |
| DELETE | /api/users/:id | ADMIN; desactiva |
| GET | /api/favorites | Token |
| POST | /api/favorites/:movieId | Token |
| DELETE | /api/favorites/:movieId | Token |

## Prueba con Postman
Crear usuario: `POST /api/auth/register` JSON `{ "nombre":"Ana Pérez", "correo":"ana@example.com", "password":"ClaveSegura2026!" }`. Login: `POST /api/auth/login` con correo y password. Copiar `token` a Authorization > Bearer Token. Crear película (ADMIN): `POST /api/movies` JSON `{ "titulo":"Interestelar", "sinopsis":"Viaje espacial", "anio":2014, "duracion":169, "clasificacion":"PG-13", "id_genero":3 }`. Consultar, actualizar y eliminar mediante las rutas de la tabla.

## Advertencias
- El backend Node.js usa `mysql2` y **no** JDBC. El proyecto Java/JDBC se entrega por separado para el criterio explícito de la lista de chequeo.
- No se incluye frontend; la API se prueba con Postman.
- No se incluyen contraseñas ni secretos reales. No subir `.env` a GitHub.
- Los tests automatizados verifican validaciones; las pruebas de integración requieren MySQL funcionando.
- La publicación en GitHub debe realizarla el aprendiz desde su propia cuenta.
