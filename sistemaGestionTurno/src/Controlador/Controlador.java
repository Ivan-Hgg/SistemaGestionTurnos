
package Controlador;
//sadasd
import Modelo.Fecha;
import Modelo.Usuario;
import Modelo.Usuarios;
import Modelo.Turno;
import Modelo.Turnos;

        
import Vista.AgregarDocumentos;
import Vista.GestionDeTurno;
import Vista.Interfaz1;
import Vista.InterfazAdmin2;
import Vista.InterfazAdminConfig;
import Vista.InterfazAdminVerTurnos;
import Vista.RegistroDatosAlumno;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan y otros
 */
public class Controlador {
    private static Usuarios usuarios = new Usuarios();
    private static Turnos turnos = new Turnos();
    private static Usuario alumno = new Usuario();
    
    
    public static void inicio(){
        Interfaz1 i= new Interfaz1 ();
        i.setVisible(true);
        //Usuarios de prueba
        Usuario u = new Usuario("a@alu.frt.utn.edu.ar", "1", "1", 1, true, 1);//alum
        usuarios.agregarUsuario(u);
        Usuario us = new Usuario("b@alu.frt.utn.edu.ar", "2", "2", 2, true, 2);//alum
        usuarios.agregarUsuario(us);
        Usuario usu = new Usuario("c@alu.frt.utn.edu.ar", "3", "3", 3, false, 3);//admin
        usuarios.agregarUsuario(usu);
        usuarios.mostrar();
    }
    
    
    
    public static void IniciarSesion(Interfaz1 i){
        
        try {
            int legajo= Integer.parseInt(i.getLegajoAlum().getText());
            String contraseña =i.getContraseñaUsuario().getText();

                
            if(usuarios.buscarUsuario(contraseña,legajo) == true ){
                JOptionPane.showMessageDialog(i, "Usuario encontrado", "Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                i.dispose();
                if(usuarios.buscarTipoUsuario(legajo)==true){//es alumno?
                    //abre la interfaz del turno del usuario, no se cual es
                    GestionDeTurno g = new GestionDeTurno();
                    g.setVisible(true);
                    alumno.setLegajo(legajo);
                    
                }else{//abre la interfaz siguiente del admin
                    InterfazAdmin2 vist = new InterfazAdmin2();vist.setVisible(true);//creo q esta era la interfaz del admin
                }
                
            }else{
                JOptionPane.showMessageDialog(i, "Usuario No Encontrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                i.getContraseñaUsuario().setText("");
                i.getLegajoAlum().setText("");
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "Error en el Ingreso de Datos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            i.getContraseñaUsuario().setText("");
            i.getLegajoAlum().setText("");
        }
        
        
    }
    
    public static void RegistroDatos(RegistroDatosAlumno i){//usado en RegistroDatosAlumno
        try {
            boolean bandera= false;//bandera para saber si hay datos con errores, en caso de que no es falso, si hay un error la bandera es verdadera
            String correo= i.getCorreoAlum().getText();
            int legajo= Integer.parseInt(i.getLegajoAlum().getText());
            String contraseña =i.getContraseña().getText();
            String apeNom = i.getApeNomAlum().getText();
            int dni =Integer.parseInt(i.getDocumenAlum().getText());
            
            
            
            //SECCION CORREO INSTITUCIONAL
            //para corroborar el mail institucional solo debo corroborar los ultimos 18 caracteres
            //que guarde la cadena hasta que encuentre el @ para ver si es igual a "alu.frt.utn.edu.ar"
            String direcMail="";
            boolean ban =false; //sirve para identificar si ya se encontro el @
            for (int j = 0; j < correo.length(); j++) {
                if(correo.charAt(j) == '@'){
                    ban=true;
                }
                if(ban==true){
                    direcMail += correo.charAt(j);
                }
            }
            if(direcMail.equals("@alu.frt.utn.edu.ar")){
                System.out.println("bien");   
            }else{
                bandera=true;
                JOptionPane.showMessageDialog(i, "Correo no valido", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            //comprueba que no se repita el correo 
            if(usuarios.buscarCorreo(correo) == true){
                JOptionPane.showMessageDialog(i, "Correo ya registrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
            
            //SECCION LEGAJO, QUE NO SE REPITAN 
            if(usuarios.buscarLegajo(legajo) == true){
                JOptionPane.showMessageDialog(i, "Legajo ya registrado", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                bandera=true;
            }
            
            
            
            //SECCION MENSAJE DE ERROR
            if(bandera==false){
                //registra los datos en una instancia y lo almacena en arraylist
                Usuario us= new Usuario(correo, contraseña, apeNom, dni, true, legajo);
                usuarios.agregarUsuario(us);
                
                JOptionPane.showMessageDialog(i, "Datos Correctos", "Mensaje de Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                i.dispose();//para cerrar y volver a la pantalla inicial en caso de todo correcto
                inicio();
                usuarios.mostrar();
            }else{
                i.getApeNomAlum().setText("");
                i.getContraseña().setText("");
                i.getCorreoAlum().setText("");
                i.getDocumenAlum().setText("");
                i.getLegajoAlum().setText("");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(i, "ERROR EN DATOS INGRESADOS", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                i.getApeNomAlum().setText("");
                i.getContraseña().setText("");
                i.getCorreoAlum().setText("");
                i.getDocumenAlum().setText("");
                i.getLegajoAlum().setText("");
        }
        
        
    }
    
    
    /*public static void interfazAdmin1(InterfazInicial i){
        i.dispose();
        InterfazAdmin1 vista= new InterfazAdmin1();
        vista.setVisible(true);
    }*/
    
    /*public static void interfazAdmin2(InterfazAdmin1 i){
        i.dispose();
        InterfazAdmin2 vist = new InterfazAdmin2();
        vist.setVisible(true);
    }*/
    
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
    
    
    public static void CrearUsuario(Interfaz1 i){
        i.dispose();
        RegistroDatosAlumno v = new RegistroDatosAlumno();
        v.setVisible(true);
    }
    
    public static void GestionDeTurno(Interfaz1 i){
        i.dispose();
        GestionDeTurno g = new GestionDeTurno();
        g.setVisible(true);
    }
    
    
    public static void AgregarDocumentos(GestionDeTurno g){
        g.dispose();
        AgregarDocumentos a = new AgregarDocumentos();
        a.setVisible(true);
    }
    
    public static void ConfirmarTurno(GestionDeTurno g) {
    String tipoGestion = g.getComboTipoGestion().getSelectedItem().toString();
    String fechaTexto = g.getComboFecha().getSelectedItem().toString();

    // Convertir texto a fecha 
    int dia = Integer.parseInt(fechaTexto.split(" ")[0]);
    int mes = 4; // fijo porque es abril
    int anio = 2025;

    // Asignar horario automático (
    int hora = 9 + (int)(Math.random() * 5); // entre 9 y 13
    int min = Math.random() < 0.5 ? 0 : 30;

    Fecha fecha = new Fecha(dia, mes, anio, hora, min);

    // Simular usuario logueado 
    Usuario u = usuarios.buscarUsuarioPorLegajo(alumno.getLegajo());
    if (u == null) {
        JOptionPane.showMessageDialog(g, "No se encontró el usuario actual", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String codigo = generarCodigoUnico();
    Turno turno = new Turno(tipoGestion, codigo, fecha, u);
    turnos.agregarTurnos(turno);
    turnos.mostrar();

    String mensaje = "✅ Turno confirmado:\n\n"
                   + "📄 Tipo de gestión: " + tipoGestion + "\n"
                   + "📅 Fecha: " + dia + "/" + mes + "/" + anio + "\n"
                   + "⏰ Hora: " + String.format("%02d:%02d", hora, min) + "\n"
                   + "🔐 Código: " + codigo;

    JOptionPane.showMessageDialog(g, mensaje, "Turno Confirmado", JOptionPane.INFORMATION_MESSAGE);

    g.dispose(); 
    inicio(); 
}

    
    private static String generarCodigoUnico() {
    return java.util.UUID.randomUUID().toString().substring(0, 8);
    
    
    }
    
    
    public static void SeleccionDeArchivo(JFrame ventanaActual, ActionEvent evt) {
        JFileChooser chooser = (JFileChooser) evt.getSource();

        if (evt.getActionCommand().equals(JFileChooser.APPROVE_SELECTION)) {
            File archivo = chooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + archivo.getAbsolutePath());

            ventanaActual.dispose();  // Cerramos la ventana actual

            // Si querés pasar el archivo, usá un constructor personalizado:
            GestionDeTurno siguientePantalla = new GestionDeTurno(); 
            siguientePantalla.setVisible(true);

        } else if (evt.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {
            ventanaActual.dispose();  // Cerramos la ventana actual
            new GestionDeTurno().setVisible(true);
        }
    }
            
}
