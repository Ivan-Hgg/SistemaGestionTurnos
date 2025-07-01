
package Controlador;

import Modelo.BD;
import Modelo.Documento;
import Modelo.Intervalo;
import Vista.InterfazAgregarDocumentoIntervalo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ivan y otros
 */
public class ControladorInterfazAgregarDocIntervalo {
    private static InterfazAgregarDocumentoIntervalo v= new InterfazAgregarDocumentoIntervalo();
    private static BD bd = new BD();
    private static ArrayList<Documento> documentosElegidos = new ArrayList<>();
    private static Intervalo intervaloNuevo = null;;
    private static int idIntervaloExistenteEditando = -1;;
    
    public static ArrayList<Documento> getDocumentosElegidos() {
    return documentosElegidos;
    }

    public static void iniciarIADI(Intervalo i) {
    v = new InterfazAgregarDocumentoIntervalo();
    documentosElegidos.clear();
    intervaloNuevo = i;  // guardás el intervalo en construcción
    v.setVisible(true);
    llenarJTableDocumentosDisponibles();
    }
    
    // Para cuando estás EDITANDO un intervalo ya existente
    public static void iniciarIADI(int idIntervalo) {
    v = new InterfazAgregarDocumentoIntervalo();
    documentosElegidos.clear();
    idIntervaloExistenteEditando = idIntervalo;
    v.setVisible(true);
    llenarJTableDocumentosDisponibles();
    // Si querés, acá podés cargar los documentos ya asociados al intervalo existente
    }
    
    
    public static void cerrarIADIabrirIAI(){
        v.dispose();
        ControladorAdminIntervalo.iniciarAdminInter();
    }
    
    
    
    public static void llenarJTableDocumentosDisponibles() {
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
        JOptionPane.showMessageDialog(v, "Error al llenar la tabla de documentos disponibles:\n" + e.getMessage(),
                "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void agregarDocumentoAIntervalo() {
    try {
        String textoId = v.getTFIdDocumento().getText().trim();

        if (textoId.isEmpty()) {
            throw new Exception("Debe ingresar el ID del documento a agregar.");
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            throw new Exception("El ID del documento debe ser un número entero.");
        }

        DefaultTableModel modeloDisponibles = (DefaultTableModel) v.getjTable1().getModel();
        DefaultTableModel modeloAgregados = (DefaultTableModel) v.getjTable2().getModel();

        boolean encontrado = false;

        for (int i = 0; i < modeloDisponibles.getRowCount(); i++) {
            int idDoc = (int) modeloDisponibles.getValueAt(i, 0);

            if (idDoc == id) {
                Object[] fila = new Object[2];
                fila[0] = modeloDisponibles.getValueAt(i, 0);
                fila[1] = modeloDisponibles.getValueAt(i, 1);

               
                modeloAgregados.addRow(fila);
                modeloDisponibles.removeRow(i);

                String nombreDoc = fila[1].toString();
                Documento doc = new Documento(nombreDoc, id);
                documentosElegidos.add(doc);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró el documento con ese ID en la tabla de disponibles.");
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(v, "Error al agregar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }


    
    /* LA COMENTO POR SI LAS DUDAS ALGUN ERROR SUCEDE
    public static void agregarDocumentoAIntervalo() {
    try {
        String textoId = v.getTFIdDocumento().getText().trim();

        if (textoId.isEmpty()) {
            throw new Exception("Debe ingresar el ID del documento a agregar.");
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            throw new Exception("El ID del documento debe ser un número entero.");
        }

        DefaultTableModel modeloDisponibles = (DefaultTableModel) v.getjTable1().getModel();
        DefaultTableModel modeloAgregados = (DefaultTableModel) v.getjTable2().getModel();

        boolean encontrado = false;

        for (int i = 0; i < modeloDisponibles.getRowCount(); i++) {
            int idDoc = (int) modeloDisponibles.getValueAt(i, 0);

            if (idDoc == id) {
                Object[] fila = new Object[2];
                fila[0] = modeloDisponibles.getValueAt(i, 0);
                fila[1] = modeloDisponibles.getValueAt(i, 1);

                modeloAgregados.addRow(fila);
                modeloDisponibles.removeRow(i);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró el documento con ese ID en la tabla de disponibles.");
        }

        } catch (Exception ex) {
        JOptionPane.showMessageDialog(v, "Error al agregar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    */
    
    
    public static void eliminarDocumentoDeIntervalo() {
    try {
        String textoId = v.getTFIdDocumento().getText().trim();

        if (textoId.isEmpty()) {
            throw new Exception("Debe ingresar el ID del documento a eliminar.");
        }

        int id;
        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            throw new Exception("El ID del documento debe ser un número entero.");
        }

        DefaultTableModel modeloDisponibles = (DefaultTableModel) v.getjTable1().getModel();
        DefaultTableModel modeloAgregados = (DefaultTableModel) v.getjTable2().getModel();

        boolean encontrado = false;

        for (int i = 0; i < modeloAgregados.getRowCount(); i++) {
            int idDoc = (int) modeloAgregados.getValueAt(i, 0);

            if (idDoc == id) {
                Object[] fila = new Object[2];
                fila[0] = modeloAgregados.getValueAt(i, 0);
                fila[1] = modeloAgregados.getValueAt(i, 1);

                modeloDisponibles.addRow(fila);
                modeloAgregados.removeRow(i);

                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new Exception("No se encontró el documento con ese ID en la tabla de agregados.");
        }

        } catch (Exception ex) {
        JOptionPane.showMessageDialog(v, "Error al eliminar documento:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
}
