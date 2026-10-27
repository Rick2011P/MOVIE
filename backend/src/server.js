
require('dotenv').config();

const app = require('./app');
const db = require('./config/database');

const port = Number(process.env.PORT || 3000);

if (!process.env.JWT_SECRET || process.env.JWT_SECRET.length < 32) {
    console.error('Configura JWT_SECRET de al menos 32 caracteres en .env');
    process.exit(1);
}

db.query('SELECT 1')
    .then(() =>
        app.listen(port, () =>
            console.log(`MOVIE API http://localhost:${port}`)
        )
    )
    .catch(err => {
        console.error('No se pudo conectar a MySQL:', err.message);
        process.exit(1);
    });
