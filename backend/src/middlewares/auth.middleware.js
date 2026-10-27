
const jwt = require('jsonwebtoken');
const db = require('../config/database');
const { fail } = require('../utils/errors');

async function authenticate(req, res, next) {
    try {
        const h = req.headers.authorization || '';

        if (!h.startsWith('Bearer ')) {
            fail(401, 'Autenticación requerida');
        }

        if (!process.env.JWT_SECRET ||
            process.env.JWT_SECRET.length < 32) {
            fail(500, 'JWT_SECRET no configurado');
        }

        let payload;

        try {
            payload = jwt.verify(
                h.slice(7),
                process.env.JWT_SECRET,
                { algorithms: ['HS256'] }
            );
        } catch {
            fail(401, 'Token inválido o vencido');
        }

        const [rows] = await db.execute(
            `SELECT id_usuario, nombre, correo, rol, estado
             FROM usuario
             WHERE id_usuario = ? AND estado = 1`,
            [payload.sub]
        );

        if (!rows.length) {
            fail(401, 'Usuario inactivo o inexistente');
        }

        req.user = rows[0];

        next();

    } catch (e) {
        next(e);
    }
}

const authorize = (...roles) => (req, res, next) => {
    if (roles.includes(req.user?.rol)) {
        return next();
    }

    return next(
        Object.assign(
            new Error('No autorizado'),
            { status: 403 }
        )
    );
};

module.exports = {
    authenticate,
    authorize
};
