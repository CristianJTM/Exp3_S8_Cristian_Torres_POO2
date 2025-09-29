/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.persistence;

/**
 *
 * @author Cristian
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cinemagenta.model.Pelicula;

public class PeliculaDAO {

    public void agregar(Pelicula pelicula) throws SQLException {
        String sql = "INSERT INTO Cartelera (titulo, director, anio, duracion, genero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAnio());
            stmt.setInt(4, pelicula.getDuracion());
            stmt.setString(5, pelicula.getGenero());
            stmt.executeUpdate();
        }
    }

    public List<Pelicula> listar() throws SQLException {
        List<Pelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cartelera";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pelicula p = new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("director"),
                        rs.getInt("anio"),
                        rs.getInt("duracion"),
                        rs.getString("genero")
                );
                lista.add(p);
            }
        }
        return lista;
    }
    
    public Pelicula buscarPeliculaPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Cartelera WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pelicula(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("director"),
                            rs.getInt("anio"),
                            rs.getInt("duracion"),
                            rs.getString("genero")
                    );
                } else {
                    return null; // No existe
                }
            }
        }
    }

    public boolean actualizarPelicula(Pelicula p) throws SQLException {
        String sql = "UPDATE Cartelera SET titulo = ?, director = ?, anio = ?, duracion = ?, genero = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false); 

            ps.setString(1, p.getTitulo());
            ps.setString(2, p.getDirector());
            ps.setInt(3, p.getAnio());
            ps.setInt(4, p.getDuracion());
            ps.setString(5, p.getGenero());
            ps.setInt(6, p.getId());

            int filas = ps.executeUpdate();
            conn.commit();
            return filas > 0;
        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean eliminarPelicula(int id) throws SQLException {
        String sql = "DELETE FROM Cartelera WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            conn.commit();
            return filas > 0;
        } catch (SQLException e) {
            throw e;
        }
    }
}
