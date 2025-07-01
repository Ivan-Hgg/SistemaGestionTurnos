
package Controlador;

import Vista.InterfazMainAlumno;


public class ControladorInterfazMainAlumno {
    static InterfazMainAlumno i = new InterfazMainAlumno();
    
    public static void iniciarVentanaMA(){
        i.setVisible(true);
    }
    public static void cerrarMAAbrirGT(){
        i.dispose();
        ControladorGestionDeTurno.iniciarGT();
    }
    
    
    
    
}
