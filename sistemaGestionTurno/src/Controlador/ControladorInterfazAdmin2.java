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
    public static void cerrarIa2AbrirIAdmInter(){
        ia2.dispose();
        ControladorAdminIntervalo.iniciarAdminInter();
    }
    public static void cerrarIa2AbrirIAdmVerTur(){
        ia2.dispose();
        ControladorInterfazAdminVerTurnos.iniciarIavt();
    }
    public static void cerrarIa2AbrirGestDocs(){
        ia2.dispose();
        ControladorGestionDoc.iniciarGestionDoc();
    }
    public static void cerrarIa2AbrirVisualPDF(){
        ia2.dispose();
        ControladorVisualizarPDF.iniciarVisualPDF();
    }
    public static void cerrarSesion(){
        ia2.dispose();
    ControladorInterfaz1.iniciarVentanaI1();
    }
    
}
