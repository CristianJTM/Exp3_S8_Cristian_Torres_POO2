/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.view;


import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    private JPanel panelPrincipal;
    private CardLayout cardLayout;

    public MainFrame() {
        setTitle("Cine Magenta");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();

        JMenu menuPeliculas = new JMenu("Películas");

        JMenuItem miAgregar = new JMenuItem("Agregar Película");
        miAgregar.addActionListener(e -> cardLayout.show(panelPrincipal, "AGREGAR"));
        menuPeliculas.add(miAgregar);

        JMenuItem miListar = new JMenuItem("Listar Películas");
        miListar.addActionListener(e -> cardLayout.show(panelPrincipal, "LISTAR"));
        menuPeliculas.add(miListar);

        JMenuItem miModificar = new JMenuItem("Modificar Película");
        miModificar.addActionListener(e -> cardLayout.show(panelPrincipal, "MODIFICAR"));
        menuPeliculas.add(miModificar);

        JMenuItem miEliminar = new JMenuItem("Eliminar Película");
        miEliminar.addActionListener(e -> cardLayout.show(panelPrincipal, "ELIMINAR"));
        menuPeliculas.add(miEliminar);

        menuBar.add(menuPeliculas);
        setJMenuBar(menuBar);

        // Panel principal con CardLayout
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        panelPrincipal.add(new PanelAgregarPelicula(), "AGREGAR");
        panelPrincipal.add(new PanelListarPeliculas(), "LISTAR");
        panelPrincipal.add(new PanelModificarPelicula(), "MODIFICAR");
        panelPrincipal.add(new PanelEliminarPelicula(), "ELIMINAR");

        add(panelPrincipal);

        // Mostrar panel inicial
        cardLayout.show(panelPrincipal, "AGREGAR");
    }
}
