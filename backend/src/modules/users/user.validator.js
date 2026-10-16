const {fail}=require('../../utils/errors');
const emailOk=s=>typeof s==='string'&&/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(s)&&s.length<=150;
function validateNew(x){if(!x||typeof x.nombre!=='string'||x.nombre.trim().length<2||x.nombre.length>120||!emailOk(x.correo)||typeof x.password!=='string'||x.password.length<8||x.password.length>72)fail(400,'Nombre, correo o contraseña inválidos (mínimo 8 caracteres)');}
function validateUpdate(x){if(!x||typeof x.nombre!=='string'||x.nombre.trim().length<2||x.nombre.length>120||!emailOk(x.correo))fail(400,'Nombre o correo inválidos');}
module.exports={validateNew,validateUpdate};
