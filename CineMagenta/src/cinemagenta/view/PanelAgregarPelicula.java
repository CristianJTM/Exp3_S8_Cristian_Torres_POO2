/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.view;

import javax.swing.*;
import cinemagenta.controller.PeliculaController;
import cinemagenta.model.Pelicula;
import cinemagenta.util.Validador;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class PanelAgregarPelicula extends JPanel {

    private JTextField txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cbGenero;
    private PeliculaController controller;

    public PanelAgregarPelicula() {
        controller = new PeliculaController();
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Director:"));
        txtDirector = new JTextField();
        add(txtDirector);

        add(new JLabel("Año:"));
        txtAnio = new JTextField();
        add(txtAnio);

        add(new JLabel("Duración (min):"));
        txtDuracion = new JTextField();
        add(txtDuracion);

        add(new JLabel("Género:"));
        cbGenero = new JComboBox<>(new String[]{"Comedia", "Drama", "Accion", "Terror", "Ciencia Ficcion", "Romance"});
        add(cbGenero);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiar());
        add(btnLimpiar);
    }

    private void guardar() {
        String titulo = txtTitulo.getText();
        String director = txtDirector.getText();
        String anioStr = txtAnio.getText();
        String duracionStr = txtDuracion.getText();
        String genero = (String) cbGenero.getSelectedItem();

        if (!Validador.validarNoVacio(titulo, "Título")
                || !Validador.validarNoVacio(director, "Director")
                || !Validador.validarNoVacio(anioStr, "Año")
                || !Validador.validarNoVacio(duracionStr, "Duración")) {
            return;
        }

        try {
            int anio = Integer.parseInt(anioStr);
            int duracion = Integer.parseInt(duracionStr);

            if (!Validador.validarEntero(anio, 1900, 2100, "Año")
                    || !Validador.validarEntero(duracion, 1, 1000, "Duración")) {
                return;
            }

            Pelicula p = new Pelicula(titulo, director, anio, duracion, genero);
            controller.agregarPelicula(p);
            JOptionPane.showMessageDialog(this, "Película agregada correctamente.");
            limpiar();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Año y Duración deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar en BD: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiar() {
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtDuracion.setText("");
        cbGenero.setSelectedIndex(0);
    }
}
