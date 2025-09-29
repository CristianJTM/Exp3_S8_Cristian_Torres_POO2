/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import cinemagenta.controller.PeliculaController;
import cinemagenta.model.Pelicula;
import java.sql.SQLException;
import java.util.List;
import java.awt.*;


public class PanelListarPeliculas extends JPanel{
    
    private JTable tabla;
    private PeliculaController controller;

    public PanelListarPeliculas() {
        controller = new PeliculaController();
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Listado de Películas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        btnRefrescar.addActionListener(e -> cargarPeliculas());
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnRefrescar);
        add(panelBoton, BorderLayout.SOUTH);

        cargarPeliculas();
    }

    private void cargarPeliculas() {
        try {
            List<Pelicula> peliculas = controller.listarPeliculas();

            String[] columnas = {"ID", "Título", "Director", "Año", "Duración", "Género"};
            DefaultTableModel model = new DefaultTableModel(columnas, 0);

            for (Pelicula p : peliculas) {
                Object[] fila = {
                        p.getId(),
                        p.getTitulo(),
                        p.getDirector(),
                        p.getAnio(),
                        p.getDuracion(),
                        p.getGenero()
                };
                model.addRow(fila);
            }

            tabla.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al cargar películas: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
