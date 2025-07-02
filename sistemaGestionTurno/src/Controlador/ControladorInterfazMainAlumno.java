
package Controlador;

import Modelo.BD;
import Modelo.Turno;
import Vista.InterfazMainAlumno;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControladorInterfazMainAlumno {
    static InterfazMainAlumno i = new InterfazMainAlumno();
    private static BD bd = new BD();
    
    public static void iniciarVentanaMA(){
        llenarJTableTurnos();
        i.setVisible(true);
    }
    
    public static void cerrarMAAbrirGT(){
        i.dispose();
        ControladorGestionDeTurno.iniciarGT();
    }
    
    public static void cerrarSesion() {
        i.dispose();
        ControladorInterfaz1.iniciarVentanaI1();
    }
    
    
    public static void llenarJTableTurnos() {
    try {
        int legajo = ControladorInterfaz1.retornarIdUsuario();  // acá es ID

        // Obtener turnos del alumno
        ArrayList<Turno> listaTurnos = bd.obtenerTurno();

        // Limpiar el modelo
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"ID", "Codigo Seguridad", "Fecha y Hora", "Documento a Presentar"});
        model.setRowCount(0);

        for (Turno t : listaTurnos) {
            if (t.getIdAlum() == legajo) {
                String nombreDoc = bd.obtenerNOMBREDocumento(t.getIdDoc());
                Object[] fila = new Object[]{
                    t.getId(),
                    t.getCodigoSeg(),
                    t.getFechaTurno() != null ? t.getFechaTurno().toString() : "",
                    nombreDoc
                };
                model.addRow(fila);
            }
        }

        i.getjTable1().setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "Error al llenar tabla de turnos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    
    public static void eliminarTurno() {
        try {
            String idTexto = i.getTextoId().getText().trim();
            String codigo = i.getTextoCodigo().getText().trim();

            if (idTexto.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(i, "Debe completar ID y Código de Seguridad.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validar que ID sea numérico
            int id = Integer.parseInt(idTexto);

            // Confirmar
            int confirmacion = JOptionPane.showConfirmDialog(i,
                    "¿Está seguro que desea eliminar el turno con código: " + codigo + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                bd.eliminarTurno(codigo);

                JOptionPane.showMessageDialog(i, "Turno eliminado correctamente.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);

                // Limpiar campos
                i.getTextoId().setText("");
                i.getTextoCodigo().setText("");

                // Refrescar tabla
                llenarJTableTurnos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(i, "El campo ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "Error al eliminar turno: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
}

   
  
    
    
}
