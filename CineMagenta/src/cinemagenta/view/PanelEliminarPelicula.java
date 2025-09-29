/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.view;

import javax.swing.*;
import cinemagenta.controller.PeliculaController;
import cinemagenta.model.Pelicula;
import java.awt.*;
import java.sql.SQLException;
/**
 *
 * @author Cristian
 */
public class PanelEliminarPelicula extends JPanel{
   
    private JTextField txtId, txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cbGenero;
    private JButton btnEliminar;
    private PeliculaController controller;

    public PanelEliminarPelicula() {
        controller = new PeliculaController();
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Eliminar Película", SwingConstants.CENTER);
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

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(e -> eliminarPelicula());
        btnPanel.add(btnEliminar);

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

                btnEliminar.setEnabled(true);
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

    private void eliminarPelicula() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea eliminar la película con ID: " + txtId.getText().trim() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                boolean exito = controller.eliminarPelicula(id);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Película eliminada correctamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar la película. Verifique el ID.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al eliminar la película:\n" + e.getMessage());
            }
        }
    }

    private void limpiarCampos() {
        txtId.setText("");
        txtTitulo.setText("");
        txtDirector.setText("");
        txtAnio.setText("");
        txtDuracion.setText("");
        cbGenero.setSelectedIndex(0);
        btnEliminar.setEnabled(false);
    }
}
