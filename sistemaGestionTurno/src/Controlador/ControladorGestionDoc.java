
package Controlador;

import Modelo.BD;
import Modelo.Documento;
import Vista.InterfazGestionDocumento;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan y otros
 */
    public class ControladorGestionDoc {
    private static InterfazGestionDocumento v = new InterfazGestionDocumento();
    private static BD bd = new BD();
    
    
    public static void iniciarGestionDoc(){
        v.setVisible(true);
        llenarJTableDocumentos();
    }
    public static void cerrarGestionDocAbrirIa2(){
        v.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    
    public static void agregarDocs() {
    try {
        
        String nombreDoc = v.getTFNombreDoc().getText().trim();

        if (nombreDoc.isEmpty()) {
            throw new Exception("El nombre del documento no puede estar vacío.");
        }

        Documento doc = new Documento(nombreDoc, 0); // El id lo asigna la BD de manera autoincremental

        bd.agregarDocumento(doc);
        
        JOptionPane.showMessageDialog(v, "Documento agregado con éxito: " + doc.getNombre());

        llenarJTableDocumentos();

        }catch (Exception ex) {
        JOptionPane.showMessageDialog(v, "Error al agregar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void llenarJTableDocumentos() {
    try {
        
        DefaultTableModel model = (DefaultTableModel) v.getjTable1().getModel();
        model.setNumRows(0);

        ArrayList<Documento> listaDocs = bd.obtenerDocumentos();

        for (Documento d : listaDocs) {
            Object[] fila = new Object[2];
            fila[0] = d.getId();
            fila[1] = d.getNombre();

            model.addRow(fila);
        }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error al llenar la tabla de documentos:\n" + e.getMessage(),
                "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminarDoc() {
    try {
        String textoId = v.getTFNombreDoc1().getText().trim();

        if (textoId.isEmpty()) {
            throw new Exception("Debe ingresar el ID del documento a eliminar.");
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            throw new Exception("El ID del documento debe ser un número entero.");
        }

        bd.eliminarDocumento(id);

        JOptionPane.showMessageDialog(v, "Documento eliminado con éxito (ID: " + id + ")");
        llenarJTableDocumentos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(v, "Error al eliminar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void modificarDoc() {
    try {
        String textoId = v.getTFNombreDoc1().getText().trim();
        String nuevoNombre = v.getTFNombreDoc().getText().trim();

        if (textoId.isEmpty() || nuevoNombre.isEmpty()) {
            throw new Exception("Debe ingresar el ID y el nuevo nombre del documento.");
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            throw new Exception("El ID del documento debe ser un número entero.");
        }

        Documento doc = new Documento(nuevoNombre, id);
        
        bd.modificarDocumento(doc);

        JOptionPane.showMessageDialog(v, "Documento modificado con éxito (ID: " + id + ")");
        llenarJTableDocumentos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(v, "Error al modificar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
}
