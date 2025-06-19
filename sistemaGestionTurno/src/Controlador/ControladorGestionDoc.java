/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.InterfazGestionDocumento;

/**
 *
 * @author Ivan y otros
 */
public class ControladorGestionDoc {
    private static InterfazGestionDocumento v = new InterfazGestionDocumento();
    
    public static void iniciarGestionDoc(){
        v.setVisible(true);
    }
    public static void cerrarGestionDocAbrirIa2(){
        v.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    
}
