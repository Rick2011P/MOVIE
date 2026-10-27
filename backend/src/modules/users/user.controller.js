
const repo = require('./user.repository');
const svc = require('./user.service');
const authService = require('../auth/auth.service');

// Crear usuario desde el panel ADMIN
exports.create = async (req, res) => {
    const usuario = await authService.register({
        nombre: req.body.nombre,
        correo: req.body.correo,
        password: req.body.password
    });

    res.status(201).json(usuario);
};

// Listar usuarios
exports.list = async (req, res) => {
    res.json(await repo.list());
};

// Consultar usuario
exports.one = async (req, res) => {
    res.json(await svc.one(req.params.id));
};

// Editar usuario
exports.update = async (req, res) => {
    res.json(await svc.update(req.params.id, req.body));
};

// Cambiar estado
exports.status = async (req, res) => {
    res.json(await svc.status(req.params.id, req.body));
};

// Eliminación lógica
exports.remove = async (req, res) => {
    await svc.status(req.params.id, { estado: false });
    res.status(204).end();
};
