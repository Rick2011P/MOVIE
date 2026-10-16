const db=require('../../config/database');
const publicCols='id_usuario,nombre,correo,rol,estado,fecha_registro';
const findByEmail=async email=>(await db.execute('SELECT * FROM usuario WHERE correo=?',[email]))[0][0];
const findById=async id=>(await db.execute(`SELECT ${publicCols} FROM usuario WHERE id_usuario=?`,[id]))[0][0];
const list=async ()=>(await db.execute(`SELECT ${publicCols} FROM usuario ORDER BY id_usuario`))[0];
const create=async (nombre,correo,hash,rol='USUARIO')=>(await db.execute('INSERT INTO usuario(nombre,correo,password_hash,rol) VALUES (?,?,?,?)',[nombre,correo,hash,rol]))[0].insertId;
const update=async (id,nombre,correo)=>(await db.execute('UPDATE usuario SET nombre=?,correo=? WHERE id_usuario=?',[nombre,correo,id]))[0].affectedRows;
const status=async (id,estado)=>(await db.execute('UPDATE usuario SET estado=? WHERE id_usuario=?',[estado,id]))[0].affectedRows;
module.exports={findByEmail,findById,list,create,update,status};
