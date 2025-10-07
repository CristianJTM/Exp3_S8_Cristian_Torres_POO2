/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinemagenta.util;

/**
 *
 * @author Cristian
 */
import javax.swing.*;

public class Validador {
     public static boolean validarNoVacio(String valor, String nombreCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validarEntero(int valor, int min, int max, String nombreCampo) {
        if (valor < min || valor > max) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe estar entre " + min + " y " + max, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
