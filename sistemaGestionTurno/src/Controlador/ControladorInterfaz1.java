/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Usuario;
import Vista.GestionDeTurno;
import Vista.Interfaz1;
import Vista.InterfazAdmin2;
import Vista.RegistroDatosAlumno;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ControladorInterfaz1 {

    static Interfaz1 i1 = new Interfaz1();
    static BD b = new BD();
    
    public static void iniciarVentanaI1(){
        i1.setVisible(true);
    }
    public static boolean buscarUsuario(String contra, int leg){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : b.obtenerUsuario()) {
            if(leg==usu.getLegajo() &&contra.equals(usu.getContraseña())){
                r = true;
            }
        }
        return r;
    }
    
    //buscar tipo usuario
    public static boolean buscarTipoUsuario(int leg){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : b.obtenerUsuario()) {
            if(leg==usu.getLegajo()){
                r = usu.isTipoUsu();
            }
        }
        return r;
    }
    private static void limpiarCampos(){
        i1.getContraseñaUsuario().setText("");
        i1.getLegajoAlum().setText("");
    }
    
    public static int retornarIdUsuario(){
        int legajo= Integer.parseInt(i1.getLegajoAlum().getText());
        return b.obtenerIDUsuario(legajo);
        
    }
    
    public static void IniciarSesion(){
        try {
            int legajo= Integer.parseInt(i1.getLegajoAlum().getText());
            String contraseña =i1.getContraseñaUsuario().getText();
            
                
            if(buscarUsuario(contraseña,legajo) == true ){
                JOptionPane.showMessageDialog(i1, "Usuario encontrado", "Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                i1.dispose();
                if(buscarTipoUsuario(legajo)==true){//es alumno?
                    ControladorInterfazMainAlumno.iniciarVentanaMA();
                }else{//abre la interfaz siguiente del admin
                    ControladorInterfazAdmin2.iniciarIa2();
                }
                
            }else{
                JOptionPane.showMessageDialog(i1, "Usuario No Encontrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                limpiarCampos();
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i1, "Error en el Ingreso de Datos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            limpiarCampos();
        }
    }
    
    

    public static void CrearUsuario() {
        i1.dispose();
        ControladorRegistroDatosAlumno.iniciarVentanaI2();
    }
    
}
