# MOVIE — Frontend web en React (SENA ADSO)
**Aprendiz:** Santiago Felipe Muñoz Camues  
**Evidencia:** GA7-220501096-AA4-EV03

## Objetivo
Frontend React con Vite que consume el backend MOVIE existente (Express/MySQL). No sustituye la aplicación Android ni modifica la base de datos directamente.

## Instalación
1. Copiar esta carpeta dentro del repositorio original: `MOVIE/frontend`.
2. Abrir terminal en `MOVIE/frontend`.
3. Ejecutar `npm install`.
4. Copiar `.env.example` como `.env` (configuración local, **no publicarla**).
5. Iniciar el backend original MOVIE en puerto 3000 y MySQL.
6. Habilitar CORS en `backend/src/app.js` antes de registrar rutas, limitado a `http://localhost:5173`.
7. Ejecutar `npm run dev` y visitar `http://localhost:5173`.
8. Ejecutar `npm run build` como validación de compilación.

### CORS (backend)
Instalar `cors` desde `MOVIE/backend` con `npm install cors`. Luego, tras `const app = express();`, antes de las rutas:
```js
const cors = require('cors');
app.use(cors({origin:'http://localhost:5173',methods:['GET','POST','PUT','PATCH','DELETE'],allowedHeaders:['Content-Type','Authorization']}));
```
No reemplazar el archivo `app.js` ni eliminar rutas existentes.

## Endpoints usados
- `GET /api/movies` — catálogo público (confirmado en el proyecto anterior).
- `POST /api/auth/login` — recibe `{correo,password}` y entrega JWT (confirmado).
- `GET /api/favorites` — consulta autenticada (confirmado).
- `POST /api/favorites/:movieId` — agrega favorito (confirmado).
- `DELETE /api/favorites/:movieId` — retira favorito (confirmado).
- `GET /api/users/me` — perfil (verificar disponibilidad y formato en backend real).
- CRUD de `GET/POST/PUT/PATCH/DELETE /api/users` — código de integración preparado; **verificar rutas, campos, estado y permisos** leyendo `backend/src/modules/users/user.routes.js` y validadores antes de declarar estas acciones operativas.

El diseño incorpora hooks `useState` y `useEffect`, componentes con props, renderizado de listas, estados carga/error/vacío, navegación con React Router, `AuthContext`, rutas protegidas y servicios HTTP separados.

## Seguridad
JWT en `sessionStorage` (enfoque de práctica de la Guía 02, no recomendado como diseño definitivo de seguridad productiva). Las pantallas protegidas NO sustituyen la autorización obligatoria en Express. No subir claves, tokens ni contraseñas a GitHub.

## Pruebas y evidencia pendiente
1. Capturar `npm run dev` y catálogo web, con API encendida.
2. Capturar `GET /api/movies` en Postman, `Network` con HTTP 200 y SQL en MySQL.
3. Capturar login, favoritos, cierre de sesión y acceso denegado.
4. Verificar CRUD usuarios **solo con cuenta autorizada** y datos de prueba, de acuerdo al contrato real.
5. Capturar `npm run build`, `git status`, commit y carpeta `frontend` en GitHub.
6. Comparar la interfaz con los prototipos anteriores del proyecto; los prototipos no fueron suministrados aquí.

## Compromisos con la guía
Adaptación al proyecto MOVIE de las Guías 01 y 02 de React del SENA. Los ejemplos de SICE son orientadores; las rutas y campos del proyecto MOVIE no deben reemplazarse por los de SICE.
