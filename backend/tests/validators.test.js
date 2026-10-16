const test=require('node:test');const assert=require('node:assert/strict');const validateMovie=require('../src/modules/movies/movie.validator');const {validateNew}=require('../src/modules/users/user.validator');const {id}=require('../src/utils/errors');
test('película válida',()=>assert.equal(validateMovie({titulo:'Interestelar',anio:2014,duracion:169,id_genero:3}).titulo,'Interestelar'));
test('rechaza duración inválida',()=>assert.throws(()=>validateMovie({titulo:'A',anio:2020,duracion:-1,id_genero:1}),/inválidos/));
test('rechaza usuario sin contraseña segura',()=>assert.throws(()=>validateNew({nombre:'Ana',correo:'ana@example.com',password:'123'}),/inválidos/));
test('identificadores válidos',()=>{assert.equal(id('5'),5);assert.throws(()=>id('1 OR 1=1'),/inválido/);});
