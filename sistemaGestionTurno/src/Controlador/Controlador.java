/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
//sadasd
import Modelo.Usuario;
import Modelo.Usuarios;
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
    private static Usuarios usuarios = new Usuarios();
    private static Usuario us= new Usuario();
    
    
    public static void inicio(){
        Interfaz1 i= new Interfaz1 ();
        i.setVisible(true);
    }
    
    public static void RegistroDatos(RegistroDatosAlumno i){//usado en RegistroDatosAlumno
        try {
            boolean bandera= false;//bandera para saber si hay datos con errores, en caso de que no es falso, si hay un error la bandera es verdadera
            String correo= i.getCorreoAlum().getText();
            int legajo= Integer.parseInt(i.getLegajoAlum().getText());
            String contraseña =i.getContraseña().getText();
            String apeNom = i.getApeNomAlum().getText();
            int dni =Integer.parseInt(i.getDocumenAlum().getText());
            
            
            
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
            if(direcMail.equals("@alu.frt.utn.edu.ar")){
                System.out.println("bien");   
            }else{
                bandera=true;
                JOptionPane.showMessageDialog(i, "Correo no valido", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            //comprueba que no se repita el correo REVISAR
            if(usuarios.buscarCorreo(correo) == true){
                JOptionPane.showMessageDialog(i, "Correo ya registrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
            
            //SECCION LEGAJO, QUE NO SE REPITAN REVISAR
            if(usuarios.buscarLegajo(legajo) == true){
                JOptionPane.showMessageDialog(i, "Legajo ya registrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
            
            
            //SECCION MENSAJE DE ERROR
            if(bandera==false){
                //registra los datos en una instancia y lo almacena en arraylist
                us.setApeNom(apeNom);
                us.setContraseña(contraseña);
                us.setCorreo(correo);
                us.setDni(dni);
                us.setLegajo(legajo);
                us.setTipoUsu(true);//usuario alumno
                usuarios.agregarUsuario(us);
                JOptionPane.showMessageDialog(i, "Datos Correctos", "Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                i.dispose();//para cerrar y volver a la pantalla inicial en caso de todo correcto
                inicio();
            }else{
                i.getApeNomAlum().setText("");
                i.getContraseña().setText("");
                i.getCorreoAlum().setText("");
                i.getDocumenAlum().setText("");
                i.getLegajoAlum().setText("");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "ERROR EN DATOS INGRESADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                i.getApeNomAlum().setText("");
                i.getContraseña().setText("");
                i.getCorreoAlum().setText("");
                i.getDocumenAlum().setText("");
                i.getLegajoAlum().setText("");
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
        int legajo= Integer.parseInt(i.getLegajoAlum().getText());
        String contraseña =i.getContraseñaUsuario().getText();
        
        usuarios.buscarLegajo(legajo);
        
    }
    
    public static void CrearUsuario(Interfaz1 i){
        i.dispose();
        RegistroDatosAlumno v = new RegistroDatosAlumno();
        v.setVisible(true);
    }
            
}
