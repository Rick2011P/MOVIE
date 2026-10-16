const db=require('../../config/database');
const cols='id_pelicula,titulo,sinopsis,anio,duracion,clasificacion,reparto,imagen,trailer,id_genero,estado';
const list=async (genre)=>{const q=`SELECT ${cols} FROM pelicula WHERE estado=1`+(genre?' AND id_genero=?':'')+' ORDER BY id_pelicula DESC';return (await db.execute(q,genre?[genre]:[]))[0];};
const one=async id=>(await db.execute(`SELECT ${cols} FROM pelicula WHERE id_pelicula=? AND estado=1`,[id]))[0][0];
const create=async x=>(await db.execute('INSERT INTO pelicula(titulo,sinopsis,anio,duracion,clasificacion,reparto,imagen,trailer,id_genero) VALUES (?,?,?,?,?,?,?,?,?)',[x.titulo,x.sinopsis,x.anio,x.duracion,x.clasificacion,x.reparto,x.imagen,x.trailer,x.id_genero]))[0].insertId;
const update=async (id,x)=>(await db.execute('UPDATE pelicula SET titulo=?,sinopsis=?,anio=?,duracion=?,clasificacion=?,reparto=?,imagen=?,trailer=?,id_genero=? WHERE id_pelicula=? AND estado=1',[x.titulo,x.sinopsis,x.anio,x.duracion,x.clasificacion,x.reparto,x.imagen,x.trailer,x.id_genero,id]))[0].affectedRows;
const remove=async id=>(await db.execute('UPDATE pelicula SET estado=0 WHERE id_pelicula=? AND estado=1',[id]))[0].affectedRows;
module.exports={list,one,create,update,remove};
