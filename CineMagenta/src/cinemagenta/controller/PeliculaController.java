/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.controller;

import cinemagenta.model.Pelicula;
import cinemagenta.persistence.PeliculaDAO;
import java.sql.SQLException;
import java.util.List;

public class PeliculaController {

    private PeliculaDAO dao;

    public PeliculaController() {
        dao = new PeliculaDAO();
    }

    public void agregarPelicula(Pelicula pelicula) throws SQLException {
        dao.agregar(pelicula);
    }

    public List<Pelicula> listarPeliculas() throws SQLException {
        return dao.listar();
    }

    public Pelicula buscarPeliculaPorId(int id) throws SQLException {
        return dao.buscarPeliculaPorId(id);
    }

    public boolean actualizarPelicula(Pelicula p) throws SQLException {
        return dao.actualizarPelicula(p);
    }

    public boolean eliminarPelicula(int id) throws SQLException {
        return dao.eliminarPelicula(id);
    }

    public boolean modificarPelicula(Pelicula pelicula) throws SQLException {
        return dao.actualizarPelicula(pelicula);
    }

    public List<Pelicula> buscarPeliculas(String genero, int desde, int hasta) throws SQLException {
        return dao.buscarPorGeneroYAnio(genero, desde, hasta);
    }

    public List<Pelicula> buscarPeliculasPorTitulo(String titulo) throws SQLException {
        return dao.buscarPorTitulo(titulo);
    }
}
