
const express = require('express');
const cors = require('cors');
const error = require('./middlewares/error.middleware');

const app = express();

// Configuración general
app.disable('x-powered-by');

// Habilitar CORS para el frontend React
app.use(cors({
    origin: [
        'http://localhost:5173',
        'http://127.0.0.1:5173'
    ],
    methods: ['GET', 'POST', 'PUT', 'PATCH', 'DELETE'],
    allowedHeaders: ['Content-Type', 'Authorization']
}));

app.use(express.json({ limit: '32kb' }));

// Ruta de comprobación del servidor
app.get('/api/health', (req, res) => {
    res.json({
        status: 'ok',
        service: 'MOVIE API'
    });
});

// Rutas de los módulos MOVIE
app.use('/api/auth', require('./modules/auth/auth.routes'));

app.use('/api/users', require('./modules/users/user.routes'));

app.use('/api/movies', require('./modules/movies/movie.routes'));

app.use('/api/favorites', require('./modules/favorites/favorite.routes'));

// Manejo de rutas inexistentes
app.use((req, res) => {
    res.status(404).json({
        error: 'Ruta no encontrada'
    });
});

// Manejo general de errores
app.use(error);

// Exportar la aplicación Express
module.exports = app;
