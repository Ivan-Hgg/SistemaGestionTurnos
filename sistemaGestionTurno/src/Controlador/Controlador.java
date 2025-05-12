/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
//sadasd
import Vista.GestionDeTurno;
import Vista.Interfaz1;
import Vista.InterfazAdmin1;
import Vista.InterfazAdmin2;
import Vista.InterfazAdminConfig;
import Vista.InterfazAdminVerTurnos;
import Vista.RegistroDatosAlumno;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan y otros
 */
public class Controlador {
    
    
    
    public static void inicio(){
        Interfaz1 i= new Interfaz1 ();
        i.setVisible(true);
    }
    
    public static void RegistroDatos(RegistroDatosAlumno i){//usado en RegistroDatosAlumno
        try {
            boolean bandera= false;//bandera para saber si hay datos con errores, en caso de que no es falso, si hay un error la bandera es verdadera
            String correo= i.getCorreoAlum().getText();
            int legago= Integer.parseInt(i.getLegajoAlum().getText());
            String contraseña =i.getContraseña().getText();
            String apeNom = i.getApeNomAlum().getText();
            int dni =Integer.parseInt(i.getDocumenAlum().getText());
            boolean tipoUsu= true;
            
            //para corroborar el mail institucional solo debo corroborar los ultimos 18 caracteres
            //que guarde la cadena hasta que encuentre el @ para ver si es igual a "alu.frt.utn.edu.ar"
            String direcMail="";
            for (int j = 0; j < correo.length()-1; j++) {
                if(correo.charAt(j) == '@'){
                    direcMail += correo.charAt(j+1);
                }
            }
            if(direcMail != "alu.frt.utn.edu.ar"){
                bandera=true;
                JOptionPane.showMessageDialog(i, "Correo no valido", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            if(bandera==false){
                JOptionPane.showMessageDialog(i, "Datos Correctos", "Mensaje de Confirmacion", JOptionPane.OK_OPTION);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "ERROR EN DATOS INGRESADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);

        }
        
        
    }
    
    public static void inicioGestionDeTurno(RegistroDatosAlumno i){
        i.dispose();
        GestionDeTurno t = new GestionDeTurno();
        t.setVisible(true);
    }
    
    /*public static void interfazAdmin1(InterfazInicial i){
        i.dispose();
        InterfazAdmin1 vista= new InterfazAdmin1();
        vista.setVisible(true);
    }*/
    
    public static void interfazAdmin2(InterfazAdmin1 i){
        i.dispose();
        InterfazAdmin2 vist = new InterfazAdmin2();
        vist.setVisible(true);
    }
    
    public static void interfazAdminConfig(InterfazAdmin2 i){
        i.dispose();
        InterfazAdminConfig vista = new InterfazAdminConfig();
        vista.setVisible(true);
    }
    
    public static void interfazAdminVerTurnos(InterfazAdmin2 i){
        i.dispose();
        InterfazAdminVerTurnos vista = new InterfazAdminVerTurnos();
        vista.setVisible(true);
    }
    public static void IniciarSesion(Interfaz1 i){
    }
    
    public static void CrearUsuario(Interfaz1 i){
        i.dispose();
        RegistroDatosAlumno v = new RegistroDatosAlumno();
        v.setVisible(true);
    }
            
}
