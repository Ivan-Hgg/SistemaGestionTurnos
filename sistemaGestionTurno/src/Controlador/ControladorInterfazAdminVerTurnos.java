/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Turno;
import Vista.InterfazAdminVerTurnos;
import java.util.List;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ControladorInterfazAdminVerTurnos {
    static private InterfazAdminVerTurnos ventana= new InterfazAdminVerTurnos();
    static BD b = new BD();
    
    public static void iniciarIavt(){
        ventana.setVisible(true);
       ControladorInterfazAdminVerTurnos.llenarJTable2();
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

        // Validar rangos básicos
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

        // Si la fecha es correcta, ahora llenamos la tabla con los turnos de esa fecha
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
      public static void llenarJTable2() {

   
    DefaultTableModel datos = (DefaultTableModel) ventana.getjTable2().getModel();
    datos.setRowCount(0); // limpia la tabla

    String[] columnas = {
        "Código de Seguridad", 
        "Nombre", 
        "Legajo", 
        "Correo", 
        "Fecha", 
        "Hora", 
        "Documento"
    };
    datos.setColumnIdentifiers(columnas);

    LocalDateTime ahora = LocalDateTime.now();
    LocalDate hoy = ahora.toLocalDate();

    System.out.println("🟢 Ahora es: " + ahora);

    Object[] proximoTurno = null;
    LocalDateTime fechaHoraProximoTurno = null;

    int turnosHoy = 0;

    for (Object[] fila : b.obtenerTurnosConUsuario()) {
        try {
            LocalDate fechaTurno = LocalDate.parse((String) fila[4]); 
            String horaStr = (String) fila[5]; 

            String[] partesHora = horaStr.split(":");
            int hora = Integer.parseInt(partesHora[0]);
            int minuto = Integer.parseInt(partesHora[1]);

            LocalDateTime fechaHoraTurno = LocalDateTime.of(fechaTurno, LocalTime.of(hora, minuto));

            if (fechaTurno.equals(hoy)) {
                turnosHoy++;
                System.out.println("🔵 Turno encontrado: " + fechaHoraTurno);

                if (fechaHoraTurno.isAfter(ahora)) {
                    if (proximoTurno == null || fechaHoraTurno.isBefore(fechaHoraProximoTurno)) {
                        proximoTurno = fila;
                        fechaHoraProximoTurno = fechaHoraTurno;
                        System.out.println("🟢 Nuevo próximo turno: " + fechaHoraProximoTurno);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error procesando turno: " + e.getMessage());
        }
    }

    System.out.println("🔎 Total turnos para hoy: " + turnosHoy);

    if (proximoTurno != null) {
        datos.addRow(proximoTurno);
        JOptionPane.showMessageDialog(ventana, "✅ Próximo turno de hoy: " 
            + fechaHoraProximoTurno.toLocalDate() + " " 
            + String.format("%02d:%02d", fechaHoraProximoTurno.getHour(), fechaHoraProximoTurno.getMinute()),
            "Próximo turno", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(ventana, "ℹ️ No hay más turnos para hoy.", "Sin turnos", JOptionPane.INFORMATION_MESSAGE);
    }
}




    
      
    public static void llenarJTable() {       
       
    DefaultTableModel datos = (DefaultTableModel) ventana.getjTable1().getModel();
    datos.setRowCount(0); // limpia la tabla

    // Si querés filtrar por fecha:
    String diaStr = ventana.getSDia().getText().trim();
    String mesStr = ventana.getSMes().getText().trim();
    String anioStr = ventana.getSAño().getText().trim();

    boolean filtrarPorFecha = !diaStr.isEmpty() && !mesStr.isEmpty() && !anioStr.isEmpty();

    int turnosMostrados = 0; // contador de turnos mostrados

    for (Object[] fila : b.obtenerTurnosConUsuario()) {
        if (filtrarPorFecha) {
            try {
                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);
                int anio = Integer.parseInt(anioStr);

                LocalDate fechaInput = LocalDate.of(anio, mes, dia);
                LocalDate fechaTurno = LocalDate.parse((String) fila[4]); // columna fecha

                if (!fechaTurno.equals(fechaInput)) {
                    continue; // No coincide, no lo muestro
                }

            } catch (NumberFormatException | DateTimeException e) {
                JOptionPane.showMessageDialog(ventana, "⚠️ Fecha inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        datos.addRow(fila);
        turnosMostrados++;
    }

    // Mostrar mensaje
    if (turnosMostrados > 0) {
        JOptionPane.showMessageDialog(ventana, "✅ Estos son los turnos del día: " 
            + diaStr + "/" + mesStr + "/" + anioStr, "Turnos encontrados", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(ventana, "ℹ️ No hay turnos para la fecha: " 
            + diaStr + "/" + mesStr + "/" + anioStr, "Sin turnos", JOptionPane.INFORMATION_MESSAGE);
    }
}
    
    static List<Object[]> turnosDeHoy = new ArrayList<>();
static int indiceTurnoActual = 0;

    public static void cargarTurnosDeHoy() {
    turnosDeHoy.clear(); // limpiar la lista
    indiceTurnoActual = 0;

    LocalDateTime ahora = LocalDateTime.now();
    LocalDate hoy = ahora.toLocalDate();

    for (Object[] fila : b.obtenerTurnosConUsuario()) {
        try {
            LocalDate fechaTurno = LocalDate.parse((String) fila[4]);
            String horaStr = (String) fila[5];
            String[] partesHora = horaStr.split(":");
            int hora = Integer.parseInt(partesHora[0]);
            int minuto = Integer.parseInt(partesHora[1]);
            LocalDateTime fechaHoraTurno = LocalDateTime.of(fechaTurno, LocalTime.of(hora, minuto));

            if (fechaTurno.equals(hoy) && fechaHoraTurno.isAfter(ahora.minusMinutes(5))) { // tolerancia 5 min
                turnosDeHoy.add(fila);
            }

        } catch (Exception e) {
            System.out.println("❌ Error procesando turno: " + e.getMessage());
        }
    }

    // Ordenar por hora (opcional, por si la query no lo devuelve ordenado)
    turnosDeHoy.sort((fila1, fila2) -> {
        try {
            String h1 = (String) fila1[5];
            String h2 = (String) fila2[5];
            return h1.compareTo(h2);
        } catch (Exception e) {
            return 0;
        }
    });
}

    public static void siguienteTurno() {
    if (indiceTurnoActual + 1 < turnosDeHoy.size()) {
        indiceTurnoActual++;
        llenarJTable2();
    } else {
        JOptionPane.showMessageDialog(ventana, "✅ Ya no hay más turnos para hoy.", "Fin de turnos", JOptionPane.INFORMATION_MESSAGE);
    }
}


   }

       


     
        
     
    
