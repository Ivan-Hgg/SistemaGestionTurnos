/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.InterfazAgregarDocumentoIntervalo;

/**
 *
 * @author Ivan y otros
 */
public class ControladorInterfazAgregarDocIntervalo {
    private static InterfazAgregarDocumentoIntervalo v= new InterfazAgregarDocumentoIntervalo();
    
    public static void iniciarIADI(){
        v.setVisible(true);
    }
    public static void cerrarIADIabrirIAI(){
        v.dispose();
        ControladorAdminIntervalo.iniciarAdminInter();
    }
    
    
}
