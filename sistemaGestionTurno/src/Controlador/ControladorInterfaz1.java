/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.Interfaz1;
import Vista.RegistroDatosAlumno;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ControladorInterfaz1 {

    static Interfaz1 i1 = new Interfaz1();
    static RegistroDatosAlumno i2 = new RegistroDatosAlumno();
    
    public static void iniciarVentana(){
        i1.setVisible(true);
    }
    
    
    
    public static void IniciarSesion(Interfaz1 i){
        System.out.println("realizar el inicio de sesion con BD");
    }

    public static void CrearUsuario(Interfaz1 i) {
        i.dispose();
        i2.setVisible(true);
        
    }
    
}
