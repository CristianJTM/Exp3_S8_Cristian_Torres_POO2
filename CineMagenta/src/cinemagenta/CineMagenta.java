/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cinemagenta;

/**
 *
 * @author Cristian
 */
import cinemagenta.persistence.ConexionDB;
import cinemagenta.view.MainFrame;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class CineMagenta {

    public static void main(String[] args) throws java.io.UnsupportedEncodingException{
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        try (Connection conn = ConexionDB.getConnection()) {
            
            if (conn != null) {
                JOptionPane.showMessageDialog(
                    null,
                    "Conexión a la base de datos establecida correctamente.",
                    "Conexión exitosa",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Error al conectar con la base de datos:\n" + e.getMessage(),
                "Error de conexión",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }
        SwingUtilities.invokeLater(() -> {
            MainFrame ventana = new MainFrame();
            ventana.setVisible(true);
        });
    }
    
}
