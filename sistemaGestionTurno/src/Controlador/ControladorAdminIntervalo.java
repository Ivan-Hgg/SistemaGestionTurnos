
package Controlador;


import Modelo.BD;
import Modelo.Intervalo;
import Vista.InterfazAdminIntervalo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan y otros
 */
public class ControladorAdminIntervalo {
    private static InterfazAdminIntervalo v = new InterfazAdminIntervalo();
    private static BD bd = new BD();
    
    
    public static void iniciarAdminInter(){
        v.setVisible(true);
        llenarJTableIntervalos();
        
    }
    
    public static void cerrarAdminInterAbrirAgregDocInt(){
        v.dispose();
        ControladorInterfazAgregarDocIntervalo.iniciarIADI();
    }
    
    public static void cerrarAdminInterRegresoAdmin2(){
        v.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    
    public static void botonAgregar() {

    try {
        String nombre = v.getTfNombre().getText();

        
        String textoAnio = v.getTfAnio().getText().trim();
        String textoAnio1 = v.getTfAnio1().getText().trim();

        if (textoAnio.isEmpty() || textoAnio1.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos.");
        }

        int anio, anio1;
        try {
            anio = Integer.parseInt(textoAnio);
            anio1 = Integer.parseInt(textoAnio1);
            if (anio != 2025 || anio1 != 2025) {
                throw new Exception("El año de inicio y fin debe ser 2025.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Los campos 'Año de inicio' y 'Año de fin' deben ser números enteros.");
        }

        
        String textoMes = v.getTfMes().getText().trim();
        String textoMes1 = v.getTfMes1().getText().trim();

        if (textoMes.isEmpty() || textoMes1.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos.");
        }

        int mes, mes1;
        try {
            mes = Integer.parseInt(textoMes);
            mes1 = Integer.parseInt(textoMes1);
            if (mes < 1 || mes > 12 || mes1 < 1 || mes1 > 12) {
                throw new Exception("Los campos 'Mes de inicio' y 'Mes de fin' deben estar entre 1 y 12.");
            }
            
            if (mes > mes1) {
            throw new Exception("El mes de inicio no puede ser posterior al mes de fin.");
            }
            
            
            
        } catch (NumberFormatException e) {
            throw new Exception("Los campos 'Mes de inicio' y 'Mes de fin' deben ser números enteros.");
        }

        
        String textoDia = v.getTfDia().getText().trim();
        String textoDia1 = v.getTfDia1().getText().trim();

        if (textoDia.isEmpty() || textoDia1.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos.");
        }

        int dia, dia1;
        try {
            dia = Integer.parseInt(textoDia);
            dia1 = Integer.parseInt(textoDia1);
            if (dia < 1 || dia > 30 || dia1 < 1 || dia1 > 30) {
                throw new Exception("Los campos 'Día de inicio' y 'Día de fin' deben estar entre 1 y 30.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Los campos 'Día de inicio' y 'Día de fin' deben ser números enteros.");
        }

        
        String textoHora = v.getTfHora().getText().trim();
        String textoHora1 = v.getTfHora1().getText().trim();

        if (textoHora.isEmpty() || textoHora1.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos.");
        }

        int hora, hora1;
        try {
            hora = Integer.parseInt(textoHora);
            hora1 = Integer.parseInt(textoHora1);
            if (hora < 0 || hora > 23 || hora1 < 0 || hora1 > 23) {
                throw new Exception("Las horas de inicio y fin deben estar entre 0 y 23.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Los campos 'Hora de inicio' y 'Hora de fin' deben ser números enteros.");
        }

        
        String textoMin = v.getTfMin().getText().trim();
        String textoMin1 = v.getTfMin1().getText().trim();

        if (textoMin.isEmpty() || textoMin1.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacíos.");
        }

        int min, min1;
        try {
            min = Integer.parseInt(textoMin);
            min1 = Integer.parseInt(textoMin1);
            if (min < 0 || min > 59 || min1 < 0 || min1 > 59) {
                throw new Exception("Los minutos de inicio y fin deben estar entre 0 y 59.");
            }
        } catch (NumberFormatException e) {
            throw new Exception("Los campos 'Minuto de inicio' y 'Minuto de fin' deben ser números enteros.");
        }

        
        LocalDateTime inicio = LocalDateTime.of(anio, mes, dia, hora, min);
        LocalDateTime fin = LocalDateTime.of(anio1, mes1, dia1, hora1, min1);

        
        Intervalo i;
        if (nombre == null || nombre.trim().isEmpty()) {
            i = new Intervalo(0, inicio, fin);
        } else {
            i = new Intervalo(0, inicio, fin, nombre.trim());
        }

        //Agregar a la BD
        bd.agregarIntervalo(i);

        
        JOptionPane.showMessageDialog(v, "Intervalo agregado con éxito:\n" + i.toString());
        System.out.println("Intervalo agregado: " + i);

        } catch (Exception ex) {
        
        JOptionPane.showMessageDialog(v, "Error al agregar intervalo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        llenarJTableIntervalos();
    }  
    
    public static void llenarJTableIntervalos() {
    try {
        // Configurar el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) v.getjTable1().getModel();
        model.setNumRows(0); // Limpiar la tabla

        BD bd = new BD();

        ArrayList<Intervalo> listaIntervalos = bd.obtenerIntervalo();

        for (Intervalo i : listaIntervalos) {
            Object[] fila = new Object[5];

            fila[0] = i.getId();
            fila[1] = i.getNombre();
            fila[2] = i.getFechaIng() != null ? i.getFechaIng().toString() : "";
            fila[3] = i.getFechaOut() != null ? i.getFechaOut().toString() : "";
            fila[4] = "Sin Documento";
            // Ahora buscamos el documento:
            /*
            int idDocumento = obtenerPrimerDocumentoDeIntervalo(i.getId());

            if (idDocumento != 0) {
                String nombreDoc = bd.obtenerNOMBREDocumento(idDocumento);
                if (!nombreDoc.equalsIgnoreCase("Error")) {
                    fila[4] = nombreDoc;
                } else {
                    fila[4] = "Sin documento";
                }
            } else {
                fila[4] = "Sin documento";
            }
            */

            model.addRow(fila);
            
        }
            } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error al llenar la tabla de intervalos:\n" + e.getMessage(), "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }
    
    
}
