/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagestionturno;

import Controlador.Controlador;
import Controlador.ControladorInterfaz1;
import Modelo.BD;
import Modelo.Documento;
import Modelo.Intervalo;
import Modelo.Turno;
import Modelo.Usuario;
import java.time.LocalDateTime;

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
        //ControladorInterfaz1.iniciarVentanaI1();
        //PRUEBA DE RECUPERACION DE PDF
        BD b = new BD();
        b.recuperarPdf(13, "salida.pdf");
        
        
        /*
        BD b = new BD();
        Turno t=new Turno(0, LocalDateTime.of(204, 8, 15, 9, 0), 1, "5d", 4, 4);
        b.eliminarTurno(t);
        
        /*PRUEBAS DED USUARIO
        Usuario u= new Usuario("alu@", 58369, "PELO", "JAJA");
        System.out.println(b.obtenerUsuario());
        
        /*pruiebas de base de datos de intervalo
        Intervalo i1=new Intervalo(0, LocalDateTime.of(2023, 7, 15, 9, 0), LocalDateTime.of(2026, 6, 15, 17, 30));
        b.agregarIntervalo(i1);
        //i1.setId(3);
        //b.modificarIntervalo(i1);
        //b.eliminarIntervalo(i1);
        System.out.println(b.obtenerIntervalo());
/*
        
        
        
        /*PRUEBAS DE BD de docuemnto
        Documento d = new Documento("Cambio de Comision");
        d.setId(2);
        //b.agregarDocumento(d);
        //b.eliminarDocumento(2);
        b.modificarDocumento(d);
        System.out.println(b.obtenerDocumentos());
        ControladorInterfaz1.iniciarVentana();*/
    }
    
}
