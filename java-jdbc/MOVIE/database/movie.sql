CREATE DATABASE IF NOT EXISTS movie CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE movie;

CREATE TABLE IF NOT EXISTS genero (
  id_genero INT AUTO_INCREMENT PRIMARY KEY,
  nombre_genero VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS director (
  id_director INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS pelicula (
  id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  descripcion TEXT NULL,
  duracion INT NOT NULL,
  fecha_estreno DATE NOT NULL,
  clasificacion VARCHAR(10) NOT NULL,
  id_genero INT NOT NULL,
  id_director INT NOT NULL,
  CONSTRAINT fk_pelicula_genero FOREIGN KEY (id_genero) REFERENCES genero(id_genero),
  CONSTRAINT fk_pelicula_director FOREIGN KEY (id_director) REFERENCES director(id_director)
);

INSERT IGNORE INTO genero(id_genero,nombre_genero) VALUES (1,'Acción'),(2,'Drama'),(3,'Ciencia ficción');
INSERT IGNORE INTO director(id_director,nombre) VALUES (1,'Christopher Nolan'),(2,'Greta Gerwig');
