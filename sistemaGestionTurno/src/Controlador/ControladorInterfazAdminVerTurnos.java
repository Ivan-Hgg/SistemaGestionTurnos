/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Turno;
import Vista.InterfazAdminVerTurnos;
import java.time.DateTimeException;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ControladorInterfazAdminVerTurnos {
    static private InterfazAdminVerTurnos ventana= new InterfazAdminVerTurnos();
    static BD b = new BD();
    
    public static void iniciarIavt(){
        ventana.setVisible(true);
    }
    
    public static void cerrarIavtAbrirIa2(){
        ventana.dispose();
        ControladorInterfazAdmin2.iniciarIa2() ;
    }
    
    public static void validarFechaSeleccionada() {
        try {
            String diaStr = ventana.getSDia().getText().trim();
            String mesStr = ventana.getSMes().getText().trim();
            String añoStr = ventana.getSAño().getText().trim();

            // Validar que no haya campos vacíos
            if (diaStr.isEmpty() || mesStr.isEmpty() || añoStr.isEmpty()) {
                JOptionPane.showMessageDialog(ventana, "Todos los campos de la fecha deben estar completos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int dia = Integer.parseInt(diaStr);
            int mes = Integer.parseInt(mesStr);
            int año = Integer.parseInt(añoStr);

            // Validar rangos
            if (dia < 1 || dia > 31) {
                JOptionPane.showMessageDialog(ventana, "El día debe estar entre 1 y 31.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mes < 1 || mes > 12) {
                JOptionPane.showMessageDialog(ventana, "El mes debe estar entre 1 y 12.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (año != 2025) {
                JOptionPane.showMessageDialog(ventana, "El año debe ser 2025.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que la fecha exista
            LocalDate fecha = LocalDate.of(año, mes, dia) ;

            // Si todo está bien
            JOptionPane.showMessageDialog(ventana, "Fecha ingresada correctamente: " + fecha.toString(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            int diaTurno=0;
            int mesTurno=0;
            int añoTurno=0;




            if(dia==diaTurno && mes==mesTurno && año==añoTurno){
                llenarJTable();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventana, "Todos los campos deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(ventana, "La fecha ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void llenarJTable() {
        DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
        datos.setNumRows(0);

        String diaStr = ventana.getSDia().getText().trim();
        String mesStr = ventana.getSMes().getText().trim();
        String anioStr = ventana.getSAño().getText().trim();

        boolean filtrarPorFecha = !diaStr.isEmpty() && !mesStr.isEmpty() && !anioStr.isEmpty();

        for (Turno turno : b.obtenerTurno()) {
            //Fecha fecha = turno.getFechaTurno();

            if (filtrarPorFecha) {
                try {
                    int dia = Integer.parseInt(diaStr);
                    int mes = Integer.parseInt(mesStr);
                    int anio = Integer.parseInt(anioStr);

                   /* if (fecha.getDia() != dia || fecha.getMes() != mes || fecha.getAnio() != anio) {
                        continue; // no coincide la fecha
                    }*/
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(ventana, "⚠️ Fecha inválida. Asegúrese de que día, mes y año sean números.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Object[] fila = {
                turno.getCodigoSeg(),
                turno.getIdDoc(),
                //turno.getAlum().getLegajo(),
                //turno.getAlum().getApeNom(),
            };

            datos.addRow(fila);
        }
    }
}
