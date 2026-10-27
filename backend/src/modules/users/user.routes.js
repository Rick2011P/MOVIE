
const r = require('express').Router();

const c = require('./user.controller');

const {
    authenticate,
    authorize
} = require('../../middlewares/auth.middleware');

// Perfil del usuario autenticado
r.get('/me', authenticate, (req, res) => {
    res.json(req.user);
});

// Todas las rutas siguientes requieren ADMIN
r.use(authenticate, authorize('ADMIN'));

// Crear usuario
r.post('/', c.create);

// Listar usuarios
r.get('/', c.list);

// Consultar usuario por ID
r.get('/:id', c.one);

// Actualizar usuario
r.put('/:id', c.update);

// Activar o desactivar usuario
r.patch('/:id/status', c.status);

// Eliminar usuario de forma lógica
r.delete('/:id', c.remove);

module.exports = r;
