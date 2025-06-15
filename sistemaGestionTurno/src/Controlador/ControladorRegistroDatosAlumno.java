/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Usuario;
import Vista.RegistroDatosAlumno;
import javax.swing.JOptionPane;


/**
 *
 * @author User
 */
public class ControladorRegistroDatosAlumno {
    static RegistroDatosAlumno i = new RegistroDatosAlumno();
    static BD b = new BD();
    
    public static void iniciarVentanaI2(){
        i.setVisible(true);
    }
    public static void cerrarVentanaI2abrirI1(){
        i.dispose();
        ControladorInterfaz1.iniciarVentanaI1();
    }
    
    private static void limpiarCampos(){
        i.getApeNomAlum().setText("");
        i.getContraseña().setText("");
        i.getCorreoAlum().setText("");
        i.getLegajoAlum().setText("");
    }
    
    
    public static void RegistroDatos(){//usado en RegistroDatosAlumno
        try {
            boolean bandera= false;//bandera para saber si hay datos con errores, en caso de que no es falso, si hay un error la bandera es verdadera
            String correo= i.getCorreoAlum().getText();
            int legajo= Integer.parseInt(i.getLegajoAlum().getText());
            String contraseña =i.getContraseña().getText();
            String apeNom = i.getApeNomAlum().getText();
            
            //SECCION CORREO INSTITUCIONAL
            //para corroborar el mail institucional solo debo corroborar los ultimos 18 caracteres
            //que guarde la cadena hasta que encuentre el @ para ver si es igual a "alu.frt.utn.edu.ar"
            String direcMail="";
            boolean ban =false; //sirve para identificar si ya se encontro el @
            for (int j = 0; j < correo.length(); j++) {
                if(correo.charAt(j) == '@'){
                    ban=true;
                }
                if(ban==true){
                    direcMail += correo.charAt(j);
                }
            }
            if(!direcMail.equals("@alu.frt.utn.edu.ar")){
                bandera=true;
                JOptionPane.showMessageDialog(i, "Correo no valido", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            //SECCION MENSAJE DE ERROR
            if(bandera==false){
                //registra los datos en una instancia y lo almacena en arraylist
                Usuario us= new Usuario(correo, legajo, contraseña, apeNom);
                boolean lol = b.agregarUsuario(us);
                if(lol){
                    JOptionPane.showMessageDialog(i, "Datos Correctos", "Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                    cerrarVentanaI2abrirI1();
                }else{
                    limpiarCampos();
                }
            }else{
                limpiarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "ERROR EN DATOS INGRESADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
        
    }
    
}
