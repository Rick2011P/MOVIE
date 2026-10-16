CREATE DATABASE IF NOT EXISTS movie_api
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE movie_api;
CREATE TABLE IF NOT EXISTS genero (id_genero INT AUTO_INCREMENT PRIMARY KEY,nombre VARCHAR(70) NOT NULL UNIQUE,descripcion VARCHAR(255));
CREATE TABLE IF NOT EXISTS usuario (id_usuario INT AUTO_INCREMENT PRIMARY KEY,nombre VARCHAR(120) NOT NULL,correo VARCHAR(150) NOT NULL UNIQUE,password_hash VARCHAR(255) NOT NULL,rol ENUM('ADMIN','USUARIO') NOT NULL DEFAULT 'USUARIO',estado TINYINT(1) NOT NULL DEFAULT 1,fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
CREATE TABLE IF NOT EXISTS pelicula (id_pelicula INT AUTO_INCREMENT PRIMARY KEY,titulo VARCHAR(150) NOT NULL,sinopsis TEXT,anio SMALLINT UNSIGNED NOT NULL,duracion SMALLINT UNSIGNED NOT NULL,clasificacion VARCHAR(20),reparto TEXT,imagen VARCHAR(500),trailer VARCHAR(500),id_genero INT NOT NULL,estado TINYINT(1) NOT NULL DEFAULT 1,CONSTRAINT fk_movie_genre FOREIGN KEY (id_genero) REFERENCES genero(id_genero));
CREATE TABLE IF NOT EXISTS favorito (id_usuario INT NOT NULL,id_pelicula INT NOT NULL,fecha_agregado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,PRIMARY KEY(id_usuario,id_pelicula),CONSTRAINT fk_fav_user FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),CONSTRAINT fk_fav_movie FOREIGN KEY (id_pelicula) REFERENCES pelicula(id_pelicula));
INSERT IGNORE INTO genero(id_genero,nombre,descripcion) VALUES (1,'Acción','Películas de acción'),(2,'Drama','Películas dramáticas'),(3,'Ciencia ficción','Ficción científica');
-- El primer ADMIN se configura manualmente después de registrar una cuenta local de confianza.
-- UPDATE usuario SET rol='ADMIN' WHERE correo='administrador@ejemplo.com';
