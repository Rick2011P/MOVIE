const repo=require('./user.repository');const {fail,id}=require('../../utils/errors');const {validateUpdate}=require('./user.validator');
async function one(v){const item=await repo.findById(id(v));if(!item)fail(404,'Usuario no encontrado');return item;}
async function update(v,body){validateUpdate(body);const uid=id(v);await one(uid);await repo.update(uid,body.nombre.trim(),body.correo.trim().toLowerCase());return one(uid);}
async function status(v,body){if(!body||typeof body.estado!=='boolean')fail(400,'estado debe ser booleano');const uid=id(v);await one(uid);await repo.status(uid,body.estado?1:0);return one(uid);}
module.exports={one,update,status};
