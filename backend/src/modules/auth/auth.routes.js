const r=require('express').Router();const c=require('./auth.controller');const {authenticate}=require('../../middlewares/auth.middleware');
r.post('/register',c.register);r.post('/login',c.login);r.get('/me',authenticate,c.profile);module.exports=r;
