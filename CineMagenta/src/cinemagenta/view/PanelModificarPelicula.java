/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.view;

import javax.swing.JPanel;
import javax.swing.*;
import cinemagenta.controller.PeliculaController;
import cinemagenta.model.Pelicula;
import java.awt.*;
import java.sql.SQLException;
/**
 *
 * @author Cristian
 */
public class PanelModificarPelicula extends JPanel{
    
    private JTextField txtId, txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cbGenero;
    private PeliculaController controller;

    public PanelModificarPelicula() {
        controller = new PeliculaController();
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Modificar Película", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        formPanel.add(new JLabel("ID:"));
        txtId = new JTextField();
        formPanel.add(txtId);

        formPanel.add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        txtTitulo.setEnabled(false);
        formPanel.add(txtTitulo);

        formPanel.add(new JLabel("Director:"));
        txtDirector = new JTextField();
        txtDirector.setEnabled(false);
        formPanel.add(txtDirector);

        formPanel.add(new JLabel("Año:"));
        txtAnio = new JTextField();
        txtAnio.setEnabled(false);
        formPanel.add(txtAnio);

        formPanel.add(new JLabel("Duración (min):"));
        txtDuracion = new JTextField();
        txtDuracion.setEnabled(false);
        formPanel.add(txtDuracion);

        formPanel.add(new JLabel("Género:"));
        cbGenero = new JComboBox<>(new String[]{"Comedia","Drama","Accion","Terror","Ciencia Ficcion","Romance"});
        cbGenero.setEnabled(false);
        formPanel.add(cbGenero);

        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarPelicula());
        btnPanel.add(btnBuscar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(e -> modificarPelicula());
        btnPanel.add(btnModificar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnPanel.add(btnLimpiar);

        add(btnPanel, BorderLayout.SOUTH);
    }

    private void buscarPelicula() {
        String idStr = txtId.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ID de la película a buscar.");
            return;
        }
        try {
            int id = Integer.parseInt(idStr);
            Pelicula p = controller.buscarPeliculaPorId(id);
            if (p != null) {
                txtTitulo.setText(p.getTitulo());
                txtDirector.setText(p.getDirector());
                txtAnio.setText(String.valueOf(p.getAnio()));
                txtDuracion.setText(String.valueOf(p.getDuracion()));
                cbGenero.setSelectedItem(p.getGenero());

                // Habilitar campos para modificar
                txtTitulo.setEnabled(true);
                txtDirector.setEnabled(true);
                txtAnio.setEnabled(true);
                txtDuracion.setEnabled(true);
                cbGenero.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la película con ID: " + id);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID debe ser un número entero.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar la película:\n" + e.getMessage());
        }
    }

    private void modificarPelicula() {
        if (txtTitulo.getText().trim().isEmpty() ||
            txtDirector.getText().trim().isEmpty() ||
            txtAnio.getText().trim().isEmpty() ||
            txtDuracion.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.");
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText().trim());
            int anio = Integer.parseInt(txtAnio.getText().trim());
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            String titulo = txtTitulo.getText().trim();
            String director = txtDirector.getText().trim();
            String genero = (String) cbGenero.getSelectedItem();

            Pelicula p = new Pelicula(id, titulo, director, anio, duracion, genero);
            boolean exito = controller.actualizarPelicula(p);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Película modificada correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la película. Verifique el ID.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Año y Duración deben ser números enteros.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar la película:\n" + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtDuracion.setText("");
        cbGenero.setSelectedIndex(0);

        txtTitulo.setEnabled(false);
        txtDirector.setEnabled(false);
        txtAnio.setEnabled(false);
        txtDuracion.setEnabled(false);
        cbGenero.setEnabled(false);
    }
}
