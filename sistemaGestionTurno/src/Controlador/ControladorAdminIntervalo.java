/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.InterfazAdminIntervalo;

/**
 *
 * @author Ivan y otros
 */
public class ControladorAdminIntervalo {
    private static InterfazAdminIntervalo v = new InterfazAdminIntervalo();
    
    public static void iniciarAdminInter(){
        v.setVisible(true);
    }
    public static void cerrarAdminInterAbrirAgregDocInt(){
        v.dispose();
        ControladorInterfazAgregarDocIntervalo.iniciarIADI();
    }
    public static void cerrarAdminInterRegresoAdmin2(){
        v.dispose();
        ControladorInterfazAdmin2.iniciarIa2();
    }
    
}
