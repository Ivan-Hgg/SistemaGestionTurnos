/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Vista.InterfazAdmin2;

/**
 *
 * @author User
 */
public class ControladorInterfazAdmin2 {
    static private InterfazAdmin2 ia2= new InterfazAdmin2();
    BD b = new BD();
    
    public static void iniciarIa2(){
        ia2.setVisible(true);
    }
    public static void cerrarIa2AbrirIac(){
        ia2.dispose();
        ControladorInterfazAdminConfig.iniciarIAc();
    }
    public static void cerrarIa2AbrirIavt(){
        ia2.dispose();
        ControladorInterfazAdminVerTurnos.iniciarIavt();
    }
    
}
