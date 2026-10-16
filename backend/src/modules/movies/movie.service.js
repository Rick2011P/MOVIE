const repo=require('./movie.repository');const validate=require('./movie.validator');const {fail,id}=require('../../utils/errors');
async function one(v){const movie=await repo.one(id(v));if(!movie)fail(404,'Película no encontrada');return movie;}
async function list(g){return repo.list(g? id(g):undefined);}
async function create(body){const mid=await repo.create(validate(body));return one(mid);}
async function update(v,body){const mid=id(v);await one(mid);await repo.update(mid,validate(body));return one(mid);}
async function remove(v){const mid=id(v);await one(mid);await repo.remove(mid);}
module.exports={one,list,create,update,remove};
