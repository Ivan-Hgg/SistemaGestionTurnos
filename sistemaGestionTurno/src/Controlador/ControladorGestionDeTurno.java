/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Turno;
import Modelo.Usuario;
import Vista.GestionDeTurno;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ControladorGestionDeTurno {
    static GestionDeTurno g = new GestionDeTurno();
    static BD b = new BD();
    static String ruta;
    public static void iniciarGT(){
        g.setVisible(true);
    }
    public static void cerrarGTAbrirMA(){
        g.dispose();
        ControladorInterfazMainAlumno.iniciarVentanaMA();
    }
    public static void cerrarGTAbrirAD(){
        g.dispose();
        ControladorAgregarPDF.iniciarAPDF();
    }
    public static void obtenerRutaDocumento(String t){
        ruta=t;//esto es para tener la ruta de los documentos y pasarlo a BD para subirlo a la base de datos
    }
    
    
    
    
    //FALTA RESOLVER QUE GUARDE EL ID DEL INTERVALO CORREGIR
    public static void ConfirmarTurno() {
        String tipoGestion = g.getComboTipoGestion().getSelectedItem().toString();
        String fechaTexto = g.getComboFecha().getSelectedItem().toString();

        //CORREGIR QUE OBTENGA LA FECHA DEL TURNO QUE SELECCIONO, NO ES ALGO QUE DEJEMOS FIJO
        int dia = Integer.parseInt(fechaTexto.split(" ")[0]);
        int mes = 4; //CORREGIR
        int anio = 2025;//CORREGIR

        // Asignar horario automático / CORREGIR QUE SEA 5 MINUTOS LUEGO DEL ANTERIOR TURNO
        //tener en cuenta que NO TRABAJAN TODO EL DIA por lo que los horarios son de la joranada que tengan
        //eso quedara medio pendiente y lo podemos dejar para el final
        int hora = 9 + (int)(Math.random() * 5); // entre 9 y 13
        int min = Math.random() < 0.5 ? 0 : 30;
        LocalDateTime fechaTurno = LocalDateTime.of(anio, mes, dia, hora, min);
        
        String codigo = generarCodigoUnico();
        int idDoc=b.obtenerIDDocumentos(tipoGestion);
        if(idDoc!=0){
            int idAlum=ControladorInterfaz1.retornarIdUsuario();
            if(idAlum!=0){
                Turno turno = new Turno(fechaTurno, idDoc, codigo, idAlum, 1);//CORREGIR LOS CAMPOS
                boolean exito = b.agregarTurno(turno, ruta);
                if(exito){
                    String mensaje = "✅ Turno confirmado:\n\n"
                               + "📄 Tipo de gestión: " + tipoGestion + "\n"
                               + "📅 Fecha: " + dia + "/" + mes + "/" + anio + "\n"
                               + "⏰ Hora: " + String.format("%02d:%02d", hora, min) + "\n"
                               + "🔐 Código: " + codigo;

                    JOptionPane.showMessageDialog(g, mensaje, "Turno Confirmado", JOptionPane.INFORMATION_MESSAGE);

                    g.dispose(); 
                    cerrarGTAbrirMA();
                }else{
                    //hay error se debrian limpiar los campos.
                }
                //
            }
            //
        }
        

}

    
    private static String generarCodigoUnico() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    
    
    }
    
    
    
    
    
}
