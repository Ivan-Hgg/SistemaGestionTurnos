/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Vista.AgregarPDF;
import Vista.GestionDeTurno;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author User
 */
public class ControladorAgregarPDF {
    static AgregarPDF a= new AgregarPDF();
    static BD b = new BD();
    
    public static void iniciarAPDF(){
        a.setVisible(true);
    }
    public static void cerrarAPDFAbrirGT(){
        a.dispose();
        ControladorGestionDeTurno.iniciarGT();
    }
    
    public static String SeleccionDeArchivo(JFrame ventanaActual, ActionEvent evt) {
        JFileChooser chooser = (JFileChooser) evt.getSource();

        if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            File archivo = chooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + archivo.getAbsolutePath());            
            cerrarAPDFAbrirGT();  // Cerramos la ventana actual
            
            return archivo.getAbsolutePath();
              
            /* Si querés pasar el archivo, usá un constructor personalizado:
            GestionDeTurno siguientePantalla = new GestionDeTurno(); 
            siguientePantalla.setVisible(true);
*/
        } else if (evt.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
            ventanaActual.dispose();  // Cerramos la ventana actual
            new GestionDeTurno().setVisible(true);
            return "";
        }
        return"";
    }
}
