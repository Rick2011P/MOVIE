const express=require('express');const app=express();const error=require('./middlewares/error.middleware');
app.disable('x-powered-by');app.use(express.json({limit:'32kb'}));
app.get('/api/health',(req,res)=>res.json({status:'ok',service:'MOVIE API'}));
app.use('/api/auth',require('./modules/auth/auth.routes'));app.use('/api/users',require('./modules/users/user.routes'));app.use('/api/movies',require('./modules/movies/movie.routes'));app.use('/api/favorites',require('./modules/favorites/favorite.routes'));
app.use((req,res)=>res.status(404).json({error:'Ruta no encontrada'}));app.use(error);module.exports=app;
