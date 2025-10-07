CREATE DATABASE IF NOT EXISTS Cine_DB
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE Cine_DB;

DROP TABLE IF EXISTS Cartelera;

CREATE TABLE Cartelera (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    director VARCHAR(50) NOT NULL,
    anio INT NOT NULL,
    duracion INT NOT NULL,
    genero ENUM('Comedia','Drama','Accion','Terror','Ciencia Ficcion','Romance') NOT NULL
);
-- Ejemplos
INSERT INTO Cartelera (titulo, director, anio, duracion, genero)
VALUES 
('Inception', 'Christopher Nolan', 2010, 148, 'Ciencia Ficcion'),
('Titanic', 'James Cameron', 1997, 195, 'Drama'),
('The Dark Knight', 'Christopher Nolan', 2008, 152, 'Accion'),
('Avengers: Endgame', 'Anthony y Joe Russo', 2019, 181, 'Accion'),
('Forrest Gump', 'Robert Zemeckis', 1994, 142, 'Drama'),
('The Conjuring', 'James Wan', 2013, 112, 'Terror'),
('La La Land', 'Damien Chazelle', 2016, 128, 'Romance'),
('The Hangover', 'Todd Phillips', 2009, 100, 'Comedia'),
('Interstellar', 'Christopher Nolan', 2014, 169, 'Ciencia Ficcion'),
('Joker', 'Todd Phillips', 2019, 122, 'Drama');

SELECT * FROM Cartelera;
