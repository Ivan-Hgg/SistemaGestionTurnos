/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Vista.InterfazAdminConfig;
import java.time.DateTimeException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ControladorInterfazAdminConfig {
    static private InterfazAdminConfig e= new InterfazAdminConfig();
    BD b = new BD();
    
    public static void iniciarIAc(){
        e.setVisible(true);
    }
    
    public static void cerrarIacAbrirIa2(){
        e.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    public static void mensajeConfirmacionInt() {
    try {
        String dDiaStr = e.getDesdeDia().getText().trim();
        String dMesStr = e.getDesdeMes().getText().trim();
        String dAñoStr = e.getDesdeAño().getText().trim();

        String hDiaStr = e.getHastaDia().getText().trim();
        String hMesStr = e.getHastaMes().getText().trim();
        String hAñoStr = e.getHastaAño().getText().trim();

        // Validar que no haya campos vacíos
        if (dDiaStr.isEmpty() || dMesStr.isEmpty() || dAñoStr.isEmpty()
                || hDiaStr.isEmpty() || hMesStr.isEmpty() || hAñoStr.isEmpty()) {
            JOptionPane.showMessageDialog(e, "Todos los campos de la fecha deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dDia = Integer.parseInt(dDiaStr);
        int dMes = Integer.parseInt(dMesStr);
        int dAño = Integer.parseInt(dAñoStr);

        int hDia = Integer.parseInt(hDiaStr);
        int hMes = Integer.parseInt(hMesStr);
        int hAño = Integer.parseInt(hAñoStr);

        // Validar rangos
        if (dDia < 1 || dDia > 31 || hDia < 1 || hDia > 31) {
            JOptionPane.showMessageDialog(e, "El día debe estar entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dMes < 1 || dMes > 12 || hMes < 1 || hMes > 12) {
            JOptionPane.showMessageDialog(e, "El mes debe estar entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (dAño != 2025 || hAño != 2025) {
            JOptionPane.showMessageDialog(e, "El año debe ser 2025.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar fechas reales (por ejemplo, que no exista 31/02/2025)
        LocalDate fechaDesde = LocalDate.of(dAño, dMes, dDia);
        LocalDate fechaHasta = LocalDate.of(hAño, hMes, hDia);

        // Validar que fecha de inicio no sea posterior a la final
        if (fechaDesde.isAfter(fechaHasta)) {
            JOptionPane.showMessageDialog(e, "La fecha de inicio no puede ser posterior a la fecha de fin.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si todo está bien
        JOptionPane.showMessageDialog(e, "Fechas ingresadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(e, "Todos los campos deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (DateTimeException ex) {
        JOptionPane.showMessageDialog(e, "Una de las fechas ingresadas no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
}
