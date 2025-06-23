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
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        ControladorInterfazAdmin2.iniciarIa2();
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
            LocalDate fecha = LocalDate.of(año, mes, dia);

            // Si todo está bien
            JOptionPane.showMessageDialog(ventana, "Fecha ingresada correctamente: " + fecha.toString(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            int diaTurno=0;
            int mesTurno=0;
            int añoTurno=0;




            //if(dia==diaTurno && mes==mesTurno && año==añoTurno){
                llenarJTable();
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventana, "Todos los campos deben ser números válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(ventana, "La fecha ingresada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
      public static Turno obtenerTurnoActual() {
    LocalDateTime ahora = LocalDateTime.now();
    Turno turnoActual = null;

    for (Turno turno : b.obtenerTurno()) {
        // Si la fecha y hora del turno es igual a la actual (con margen de minutos)
        if (turno.getFechaTurno().toLocalDate().equals(ahora.toLocalDate())) {
            if (turno.getFechaTurno().getHour() == ahora.getHour() &&
                Math.abs(turno.getFechaTurno().getMinute() - ahora.getMinute()) <= 5) {
                turnoActual = turno;
                break; // solo el primer turno que coincida
            }
        }
    }

    return turnoActual;
}
    
      
    public static void llenarJTable() {
       Turno turnoActual = ControladorInterfazAdminVerTurnos.obtenerTurnoActual();

        if (turnoActual != null) {
    DefaultTableModel modelo = (DefaultTableModel) ventana.getjTable2().getModel();
    modelo.setRowCount(0); // limpia la tabla antes de mostrar el actual (opcional)

    modelo.addRow(new Object[]{
        turnoActual.getCodigoSeg(),  // si tenés un getter para esto
        turnoActual.getFechaTurno().toLocalDate().toString(),
        String.format("%02d:%02d", turnoActual.getFechaTurno().getHour(), turnoActual.getFechaTurno().getMinute()),
        turnoActual.getIdDoc(),
        //turnoActual.getIdDocente(),
       // turnoActual.getIdAlumno()
    });
} else {
    JOptionPane.showMessageDialog(null, "⏱ No hay un turno activo en este momento.", "Sin turno actual", JOptionPane.INFORMATION_MESSAGE);
}

        
        DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
datos.setRowCount(0); // limpia la tabla

String diaStr = ventana.getSDia().getText().trim();
String mesStr = ventana.getSMes().getText().trim();
String anioStr = ventana.getSAño().getText().trim();

boolean filtrarPorFecha = !diaStr.isEmpty() && !mesStr.isEmpty() && !anioStr.isEmpty();

for (Turno turno : b.obtenerTurno()) {
    LocalDateTime fechaTurno = turno.getFechaTurno();

    if (filtrarPorFecha) {
        try {
            int dia = Integer.parseInt(diaStr);
            int mes = Integer.parseInt(mesStr);
            int anio = Integer.parseInt(anioStr);

            LocalDate fechaInput = LocalDate.of(anio, mes, dia);
            if (!fechaTurno.toLocalDate().equals(fechaInput)) {
                continue; // la fecha no coincide
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(ventana, "⚠️ Fecha inválida. Asegúrese de que día, mes y año sean números.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (DateTimeException e) {
            JOptionPane.showMessageDialog(ventana, "⚠️ Fecha ingresada no válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    Object[] fila = {
        turno.getCodigoSeg(), // o getCodigoSeg() si así lo llamás
        turno.getId(), // asumido nombre correcto
        turno.getIdAlum(),  // podés agregar más campos si tenés
        fechaTurno.toLocalDate(),
        String.format("%02d:%02d", fechaTurno.getHour(), fechaTurno.getMinute())
    };

    datos.addRow(fila);
}

     
        }
       
       

     
  }      
    
