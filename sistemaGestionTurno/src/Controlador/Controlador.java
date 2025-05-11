/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
//sadasd
import Vista.InterfazInicial;
import Vista.GestionDeTurno;
import Vista.InterfazAdmin1;
import Vista.InterfazAdmin2;
import Vista.InterfazAdminConfig;
import Vista.InterfazAdminVerTurnos;
import Vista.RegistroDatosAlumno;

/**
 *
 * @author Ivan y otros
 */
public class Controlador {
    
    
    
    public static void inicio(){
        InterfazInicial i= new InterfazInicial ();
        i.setVisible(true);
    }
    
    public static void incioRegistroDatosAlumno(InterfazInicial i){
        i.dispose();
        RegistroDatosAlumno v = new RegistroDatosAlumno();
        v.setVisible(true);
    }
    
    public static void inicioGestionDeTurno(RegistroDatosAlumno i){
        i.dispose();
        GestionDeTurno t = new GestionDeTurno();
        t.setVisible(true);
    }
    
    public static void interfazAdmin1(InterfazInicial i){
        i.dispose();
        InterfazAdmin1 vista= new InterfazAdmin1();
        vista.setVisible(true);
    }
    
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
            
}
