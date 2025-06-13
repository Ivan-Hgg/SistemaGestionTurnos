/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionturno;

import Controlador.Controlador;
import Controlador.ControladorInterfaz1;
import Modelo.BD;
import Modelo.Documento;

/**
 *
 * @author Ivan y otros
 */
public class SistemaGestionTurno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here;
        BD b = new BD();
        /*PRUEBAS DE BD
        Documento d = new Documento("Baja de Regularidad");
        d.setId(3);
        //b.agregarDocumento(d);
        //b.eliminarDocumento(2);
        b.modificarDocumento(d);*/
        System.out.println(b.obtenerDocumentos());
        //ControladorInterfaz1.iniciarVentana();
    }
    
}
