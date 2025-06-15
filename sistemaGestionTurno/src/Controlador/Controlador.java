
package Controlador;
//para la coneccion con base de datos
import java.sql.*;

import Modelo.Fecha;
import Modelo.Turno;
import Modelo.Turnos;
import Modelo.Usuario;
import Modelo.Usuarios;
import Vista.AgregarDocumentos;
import Vista.GestionDeTurno;
import Vista.Interfaz1;
import Vista.InterfazAdmin2;
import Vista.InterfazAdminConfig;
import Vista.InterfazAdminVerTurnos;
import Vista.RegistroDatosAlumno;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Controlador {
    private static Usuarios usuarios = new Usuarios();
    private static Turnos turnos = new Turnos();
    private static Usuario alumno = new Usuario();
    private static LocalDate fechaDesdeGlobal;
    private static LocalDate fechaHastaGlobal;
    private static boolean banderaInicio = false;
    //A futuro mejorar que se crean muchas instancias de las vistas para cerrar o abrir,  solucion: instanciar una vez aqui y llamar al objeto en cada lugar donde se crea la instancia

    /*public static void llenarJTable(InterfazAdminVerTurnos v){
    DefaultTableModel datos = (DefaultTableModel) v.getjTable1().getModel();
    datos.setNumRows(0); 
    
     for (Turno turno : turnos.getTurnos()) { 
        Object[] fila = {
       turno.getCodigoSeg(),
       turno.getTipoNota(),
       turno.getAlum(),
       turno.getFechaTurno(),
        };
        datos.addRow(fila); 
    }
    }*/


//PRUEBA DE FUNCION LLENAR JTABLE



    
 /* public static class jtable1 extends JFrame {  
   public  void tablaturnos(ArrayList <Turno> turnos){
       DefaultTableModel modelo = new DefaultTableModel();
      
 


       for (Turno turno : turnos) {
    Object[] fila = {
       turno.getCodigoSeg(),
       turno.getTipoNota(),
       turno.getAlum(),
       turno.getFechaTurno(),
    };
    modelo.addRow(fila);
       }
       JTable tabla = new JTable(modelo);
JScrollPane scrollPane = new JScrollPane(tabla); // Para que tenga barra si hay muchos datos
 add(scrollPane, BorderLayout.CENTER);
 setVisible(true);

   // public static void dispose() {
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
}

   
   

    //public static void dispose() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  }*/
  
   
}
