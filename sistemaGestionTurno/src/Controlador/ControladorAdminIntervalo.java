
package Controlador;


import Modelo.BD;
import Modelo.Documento;
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
    
    public static void cerrarAdminInterAbrirAgregDocInt() {
    v.dispose();

    int filaSeleccionada = v.getjTable1().getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(v, "Debe seleccionar un intervalo para agregar documentos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }


    int idIntervalo = (int) v.getjTable1().getValueAt(filaSeleccionada, 0); // la columna 0 es el ID

    ControladorInterfazAgregarDocIntervalo.iniciarIADI(idIntervalo);
    }

    
    public static void cerrarAdminInterRegresoAdmin2(){
        v.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    
    public static void abrirAgregarDocumentoParaNuevoIntervalo() {
    try {
        // Validás y armás un objeto Intervalo con lo que el usuario escribió en la pantalla
        String nombre = v.getTfNombre().getText();

        String textoAnio = v.getTfAnio().getText().trim();
        String textoMes = v.getTfMes().getText().trim();
        String textoDia = v.getTfDia().getText().trim();
        String textoHora = v.getTfHora().getText().trim();
        String textoMin = v.getTfMin().getText().trim();

        String textoAnio1 = v.getTfAnio1().getText().trim();
        String textoMes1 = v.getTfMes1().getText().trim();
        String textoDia1 = v.getTfDia1().getText().trim();
        String textoHora1 = v.getTfHora1().getText().trim();
        String textoMin1 = v.getTfMin1().getText().trim();

        if (textoAnio.isEmpty() || textoMes.isEmpty() || textoDia.isEmpty() || textoHora.isEmpty() || textoMin.isEmpty()
            || textoAnio1.isEmpty() || textoMes1.isEmpty() || textoDia1.isEmpty() || textoHora1.isEmpty() || textoMin1.isEmpty()) {
            throw new Exception("Debe completar la fecha y hora de inicio y fin antes de agregar documentos.");
        }

        int anio = Integer.parseInt(textoAnio);
        int mes = Integer.parseInt(textoMes);
        int dia = Integer.parseInt(textoDia);
        int hora = Integer.parseInt(textoHora);
        int min = Integer.parseInt(textoMin);

        int anio1 = Integer.parseInt(textoAnio1);
        int mes1 = Integer.parseInt(textoMes1);
        int dia1 = Integer.parseInt(textoDia1);
        int hora1 = Integer.parseInt(textoHora1);
        int min1 = Integer.parseInt(textoMin1);

        LocalDateTime inicio = LocalDateTime.of(anio, mes, dia, hora, min);
        LocalDateTime fin = LocalDateTime.of(anio1, mes1, dia1, hora1, min1);

        Intervalo i;
        if (nombre == null || nombre.trim().isEmpty()) {
            i = new Intervalo(0, inicio, fin);
        } else {
            i = new Intervalo(0, inicio, fin, nombre.trim());
        }

        // Abrís la ventana de Agregar Documento para este intervalo (NO lo grabamos todavía)
        ControladorInterfazAgregarDocIntervalo.iniciarIADI(i);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(v, "Error al preparar intervalo para agregar documentos:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
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

        bd.agregarIntervalo(i);

        int idIntervaloNuevo = bd.obtenerUltimoIdIntervalo();

        for (Documento d : ControladorInterfazAgregarDocIntervalo.getDocumentosElegidos()) {
            bd.agregarDocumentoAIntervalo(idIntervaloNuevo, d.getId());
        }

        ControladorInterfazAgregarDocIntervalo.getDocumentosElegidos().clear();

        JOptionPane.showMessageDialog(v, "Intervalo agregado con éxito:\n" + i.toString());
        System.out.println("Intervalo agregado: " + i);

        llenarJTableIntervalos();
        limpiarCampos();
        
        } catch (Exception ex) {
        
        JOptionPane.showMessageDialog(v, "Error al agregar intervalo:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
       
    }  
    
    public static void llenarJTableIntervalos() {
    try {
        DefaultTableModel model = (DefaultTableModel) v.getjTable1().getModel();
        model.setNumRows(0);

        ArrayList<Intervalo> listaIntervalos = bd.obtenerIntervalo();

        for (Intervalo i : listaIntervalos) {
            Object[] fila = new Object[5];

            fila[0] = i.getId();
            fila[1] = i.getNombre();
            fila[2] = i.getFechaIng() != null ? i.getFechaIng().toString() : "";
            fila[3] = i.getFechaOut() != null ? i.getFechaOut().toString() : "";

            // acá llamamos a la BD para obtener los documentos del intervalo
             ArrayList<Documento> docs = bd.obtenerDocumentosDeIntervalo(i.getId());

            if (docs.isEmpty()) {
                fila[4] = "Sin documento";
            } else {
                StringBuilder nombresDocs = new StringBuilder();
                for (Documento d : docs) {
                    nombresDocs.append(d.getNombre()).append(", ");
                }
                
                if (nombresDocs.length() > 2) {
                    nombresDocs.setLength(nombresDocs.length() - 2);
                }
                fila[4] = nombresDocs.toString();
            }

            model.addRow(fila);
        }

        } catch (Exception e) {
        JOptionPane.showMessageDialog(v, "Error al llenar la tabla de intervalos:\n" + e.getMessage(),
                "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }   
    
    public static void botonEliminar() {
    try {
        // Ver si hay selección en la tabla
        int filaSeleccionada = v.getjTable1().getSelectedRow();
        int idAEliminar = -1;

        // Si hay ID escrita, se usa esa
        String textoId = v.getTfId().getText().trim();

        if (!textoId.isEmpty()) {
            try {
                idAEliminar = Integer.parseInt(textoId);
            } catch (NumberFormatException e) {
                throw new Exception("El ID debe ser un número entero.");
            }
        } else if (filaSeleccionada != -1) {
            // Si no hay ID, pero hay fila seleccionada, se toma esa
            idAEliminar = (int) v.getjTable1().getValueAt(filaSeleccionada, 0);
        } else {
            // Si no hay nada, error
            throw new Exception("Debe seleccionar un intervalo o escribir un ID para eliminar.");
        }

        // Confirmar con el usuario
        int confirmacion = JOptionPane.showConfirmDialog(
                v,
                "¿Está seguro que desea eliminar el intervalo con ID: " + idAEliminar + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar
            bd.eliminarIntervalo(idAEliminar);
            JOptionPane.showMessageDialog(v, "Intervalo eliminado correctamente.");

            // Refrescar tabla
            llenarJTableIntervalos();

            // Limpiar campo ID
            v.getTfId().setText("");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(v, "Error al eliminar intervalo:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    //aca lo hice con seleccionar con el mouse
    /*
    public static void botonEliminar() {
    try {
        int filaSeleccionada = v.getjTable1().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(v, "Debe seleccionar un intervalo para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idIntervalo = (int) v.getjTable1().getValueAt(filaSeleccionada, 0); // Columna 0 = ID

        int confirmacion = JOptionPane.showConfirmDialog(v, "¿Está seguro que desea eliminar el intervalo seleccionado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            bd.eliminarIntervalo(idIntervalo);
            JOptionPane.showMessageDialog(v, "Intervalo eliminado con éxito.");
            llenarJTableIntervalos(); // refrescar tabla
        }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error al eliminar intervalo:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
    }
    */
    
    public static void botonModificar() {
    try {
        String textoId = v.getTfId().getText().trim();
        if (textoId.isEmpty()) {
            throw new Exception("Debe ingresar el ID del intervalo a modificar.");
        }

        int idIntervalo = Integer.parseInt(textoId);

        // Reutilizamos la misma lógica que en agregar:
        String nombre = v.getTfNombre().getText();

        int anio = Integer.parseInt(v.getTfAnio().getText().trim());
        int mes = Integer.parseInt(v.getTfMes().getText().trim());
        int dia = Integer.parseInt(v.getTfDia().getText().trim());
        int hora = Integer.parseInt(v.getTfHora().getText().trim());
        int min = Integer.parseInt(v.getTfMin().getText().trim());

        int anio1 = Integer.parseInt(v.getTfAnio1().getText().trim());
        int mes1 = Integer.parseInt(v.getTfMes1().getText().trim());
        int dia1 = Integer.parseInt(v.getTfDia1().getText().trim());
        int hora1 = Integer.parseInt(v.getTfHora1().getText().trim());
        int min1 = Integer.parseInt(v.getTfMin1().getText().trim());

        LocalDateTime inicio = LocalDateTime.of(anio, mes, dia, hora, min);
        LocalDateTime fin = LocalDateTime.of(anio1, mes1, dia1, hora1, min1);

        Intervalo intervaloModificado = new Intervalo(idIntervalo, inicio, fin, nombre.trim());

        bd.modificarIntervalo(intervaloModificado);

        JOptionPane.showMessageDialog(v, "Intervalo modificado con éxito.");
        llenarJTableIntervalos(); // refrescar tabla

        } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error al modificar intervalo:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
    }
    
    
    private static void limpiarCampos() {
        
        v.getTfId().setText("");
        v.getTfNombre().setText("");

        v.getTfAnio().setText("");
        v.getTfMes().setText("");
        v.getTfDia().setText("");
        v.getTfHora().setText("");
        v.getTfMin().setText("");

        v.getTfAnio1().setText("");
        v.getTfMes1().setText("");
        v.getTfDia1().setText("");
        v.getTfHora1().setText("");
        v.getTfMin1().setText("");
    }

}
