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

    public static void iniciarGT(){
        g.setVisible(true);
    }
    public static void cerrarGTAbrirMA(){
        g.dispose();
        ControladorInterfazMainAlumno.iniciarVentanaMA();
    }
    public static void cerrarGTAbrirAD(){
        g.dispose();
        ControladorAgregarDocumentos.iniciarAD();
    }
    
    
    
    public static void ConfirmarTurno() {
        String tipoGestion = g.getComboTipoGestion().getSelectedItem().toString();
        String fechaTexto = g.getComboFecha().getSelectedItem().toString();

    //HACE FALTA CORREGIR PARA QUE CUMPLA CON LOS ATRIBUTOS ACTUALES DEL TURNO
        // Convertir texto a fecha 
        int dia = Integer.parseInt(fechaTexto.split(" ")[0]);
        int mes = 4; // fijo porque es abril
        int anio = 2025;

        // Asignar horario automático (
        int hora = 9 + (int)(Math.random() * 5); // entre 9 y 13
        int min = Math.random() < 0.5 ? 0 : 30;
        
/*      SOLUCIONEN ESTO QUE YA NO USAMOS LA FECHA ASI
        Fecha fecha = new Fecha(dia, mes, anio, hora, min);
*/

        /* NO SE NECESITA YA QUE LA COMPROBACION ES EN EL INICIO DE SESION - BORRAR ESTO SI COINCIDEN
        Simular usuario logueado 
        Usuario u = usuarios.buscarUsuarioPorLegajo(alumno.getLegajo());
        if (u == null) {
            JOptionPane.showMessageDialog(g, "No se encontró el usuario actual", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }*/

        String codigo = generarCodigoUnico();
        Turno turno = new Turno(min, LocalDateTime.MIN, anio, codigo, anio, min);//CORREGIR LOS CAMPOS
        boolean exito = b.agregarTurno(turno);
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

}

    
    private static String generarCodigoUnico() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    
    
    }
    
    
    
    
    
}
