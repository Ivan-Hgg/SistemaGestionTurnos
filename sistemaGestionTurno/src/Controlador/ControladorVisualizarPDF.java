/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Turno;
import Vista.InterfazVisualizarPDF;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ivan y otros
 */
public class ControladorVisualizarPDF {
    private static InterfazVisualizarPDF v = new InterfazVisualizarPDF();
    private static BD b = new BD();
    private static int legajo; 
    
    
    public static void iniciarVisualPDF(){
        v.setVisible(true);
    }
    
    public static void llenarJtableVerPDF(){
        try {
            legajo= Integer.parseInt(v.getTfLegajo().getText());
            TableColumnModel columnModel = v.getGrilla().getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(10);
            columnModel.getColumn(2).setPreferredWidth(10);
            columnModel.getColumn(3).setPreferredWidth(60);
            columnModel.getColumn(4).setPreferredWidth(120);

            DefaultTableModel model = (DefaultTableModel) v.getGrilla().getModel();
            String apenom, docnom;
            model.setNumRows(0);
            for (Turno t : b.obtenerTurnosDeAlumno(legajo)) {
                Object[] fila = new Object[5];
                fila[0] = legajo;
                apenom=b.obtenerAPENOMUsuario(legajo);
                if(!apenom.equalsIgnoreCase("Error")){
                    fila[1] = apenom;
                    fila[2] = t.getId();
                    fila[3] = t.getFechaTurno();
                    docnom=b.obtenerNOMBREDocumento(t.getIdDoc());
                    if(!docnom.equalsIgnoreCase("Error")){
                        fila[4] = docnom;
                        model.addRow(fila);
                    }else{
                        JOptionPane.showMessageDialog(v, "no se encontró el documento", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(v, "no se encontró al alumno", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                } 

            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error en el ingreso de datos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }
        
    }
    
    public static void visualizarPDF(){
        try {
            int idTur= Integer.parseInt(v.getTfIdTurno().getText());
            String destino= "Salida"+idTur+".pdf";
            b.recuperarPdf(idTur, destino);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(v, "Error en el ingreso de datos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }
        
    }
    
    
    
}
