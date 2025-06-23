/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.BD;
import Modelo.Documento;
import Modelo.Intervalo;
import Modelo.Turno;
import Modelo.Usuario;
import Vista.GestionDeTurno;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.JComboBox;
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
        llenarComboBox();
        g.setVisible(true);
    }
    public static void cerrarGT(){
        g.dispose();
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
        ruta= t;//esto es para tener la ruta de los documentos y pasarlo a BD para subirlo a la base de datos
    }
    public static void llenarComboBox(){

        JComboBox<String> combo = g.getComboTipoGestion();
        combo.removeAllItems();

        ArrayList<Documento> documentos = b.obtenerDocumentos();
        for (Documento doc : documentos) {
            combo.addItem(doc.getNombre());// solo muestra el nombre
        }
    }
/*public static int obtenerIDDocumento() {
    JComboBox<String> combo = g.getComboTipoGestion();
    String seleccionado = (String) combo.getSelectedItem(); // lo que seleccionó el usuario
    ArrayList<Documento> documentos = b.obtenerDocumentos();
    

    for (Documento doc : documentos) {
        if (doc.getNombre().equals(seleccionado)) { 
            System.out.println("el ID seleccionado es:" + doc.getId());
            return doc.getId(); // lo encontramos, devolvemos
            
        }
    }
    System.out.println("no se encontro id");
    return 0; // no encontrado
}*/
    
public static void llenarCombosFecha() {
    String nombreDocSeleccionado = (String) g.getComboTipoGestion().getSelectedItem();
    int idDoc = b.obtenerIDDocumentos(nombreDocSeleccionado);
    System.out.println("el ID seleccionado es:" + idDoc);
    
    ArrayList<Integer> idsIntervalos = b.obtenerIdIntervalosDeDocumento(idDoc); // Paso 1

    ArrayList<Intervalo> intervalos = b.obtenerIntervaloConSuID(idsIntervalos); // Paso 2

    // Ahora podés llenar los combos como ya venías haciendo
    JComboBox<String> comboDia = g.getComboFechaDia();
    JComboBox<String> comboMes = g.getComboFechaMes();
    JComboBox<String> comboAnio = g.getComboFechaAño();

    comboDia.removeAllItems();
    comboMes.removeAllItems();
    comboAnio.removeAllItems();

    Set<String> dias = new TreeSet<>(Comparator.comparingInt(Integer::parseInt));
    Set<String> meses = new TreeSet<>(Comparator.comparingInt(Integer::parseInt));
    Set<String> años = new TreeSet<>(Comparator.comparingInt(Integer::parseInt));

    for (Intervalo in : intervalos) {
        LocalDateTime fecha = in.getFechaIng();
        LocalDateTime fin = in.getFechaOut();

        while (!fecha.isAfter(fin)) {
            dias.add(String.valueOf(fecha.getDayOfMonth()));
            meses.add(String.valueOf(fecha.getMonthValue()));
            años.add(String.valueOf(fecha.getYear()));
            fecha = fecha.plusDays(1);
        }
    }

    for (String d : dias) comboDia.addItem(d);
    for (String m : meses) comboMes.addItem(m);
    for (String a : años) comboAnio.addItem(a);
    /*JComboBox<String> comboDia = g.getComboFechaDia();
    JComboBox<String> comboMes = g.getComboFechaMes();
    JComboBox<String> comboAnio = g.getComboFechaAño();

    comboDia.removeAllItems();
    comboMes.removeAllItems();
    comboAnio.removeAllItems();

    ArrayList<Intervalo> intervalos = b.obtenerIntervalo();

    Set<Integer> dias = new TreeSet<>();
    Set<Integer> meses = new TreeSet<>();
    Set<Integer> anios = new TreeSet<>();
    
    String nombreDocSeleccionado = (String) g.getComboTipoGestion().getSelectedItem();
    int idDoc = b.obtenerIDDocumentos(nombreDocSeleccionado);
    System.out.println("el ID seleccionado es:" + idDoc);
    
    for (Intervalo in : intervalos) {
       
        // Este filtro deberías adaptarlo si los Intervalos tienen una relación con Documento
        if(idDoc == in.getId()){
        LocalDateTime fecha = in.getFechaIng();
        LocalDateTime fin = in.getFechaOut();

        while (!fecha.isAfter(fin)) {
            dias.add(fecha.getDayOfMonth());
            meses.add(fecha.getMonthValue());
            anios.add(fecha.getYear());
            fecha = fecha.plusDays(1);
        }
    }
    }

    // Llenar combos con los valores únicos y ordenados
    for (int d : dias) comboDia.addItem(String.valueOf(d));
    for (int m : meses) comboMes.addItem(String.valueOf(m));
    for (int a : anios) comboAnio.addItem(String.valueOf(a));*/
}
    
    
    //FALTA RESOLVER QUE GUARDE EL ID DEL INTERVALO CORREGIR
    public static void ConfirmarTurno() {
        String tipoGestion = g.getComboTipoGestion().getSelectedItem().toString();
        String fechaTextoDia = g.getComboFechaDia().getSelectedItem().toString();
        String fechaTextoMes = g.getComboFechaMes().getSelectedItem().toString();
        String fechaTextoAño = g.getComboFechaAño().getSelectedItem().toString();

        //CORREGIR QUE OBTENGA LA FECHA DEL TURNO QUE SELECCIONO, NO ES ALGO QUE DEJEMOS FIJO
        int dia = Integer.parseInt(fechaTextoDia.split(" ")[0]);
        int mes = Integer.parseInt(fechaTextoMes.split(" ")[0]);
        int año = Integer.parseInt(fechaTextoAño.split(" ")[0]);


        // Asignar horario automático / CORREGIR QUE SEA 5 MINUTOS LUEGO DEL ANTERIOR TURNO
        //tener en cuenta que NO TRABAJAN TODO EL DIA por lo que los horarios son de la joranada que tengan
        //eso quedara medio pendiente y lo podemos dejar para el final
        int hora = 9 + (int)(Math.random() * 5); // entre 9 y 13
        int min = Math.random() < 0.5 ? 0 : 30;
        LocalDateTime fechaTurno = LocalDateTime.of(año, mes, dia, hora, min);
        
        String codigo = generarCodigoUnico();
        int idDoc=b.obtenerIDDocumentos(tipoGestion);
        
        if(idDoc!=0){
            int idAlum=ControladorInterfaz1.retornarIdUsuario();
            if(idAlum!=0){
                if(ruta != null){
                    Turno turno = new Turno(fechaTurno, idDoc, codigo, idAlum, 1);//CORREGIR LOS CAMPOS
                    boolean exito = b.agregarTurno(turno, ruta);
                    if(exito){
                        String mensaje = "✅ Turno confirmado:\n\n"
                                   + "📄 Tipo de gestión: " + tipoGestion + "\n"
                                   + "📅 Fecha: " + dia + "/" + mes + "/" + año + "\n"
                                   + "⏰ Hora: " + String.format("%02d:%02d", hora, min) + "\n"
                                   + "🔐 Código: " + codigo;

                        JOptionPane.showMessageDialog(g, mensaje, "Turno Confirmado", JOptionPane.INFORMATION_MESSAGE);

                        g.dispose(); 
                        cerrarGTAbrirMA();
                    }else{
                        JOptionPane.showMessageDialog(g, "ERROR subiendo turno a la base de datos", "Error", JOptionPane.ERROR_MESSAGE);

                        //hay error se debrian limpiar los campos.
                    }
                }else{
                    JOptionPane.showMessageDialog(g, "ERROR: no seleccionó un archivo", "Error", JOptionPane.ERROR_MESSAGE);

                }
                
                //
            }else{
                JOptionPane.showMessageDialog(g, "ERROR: no se encontró el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(g, "ERROR: no se encontró el documento", "Error", JOptionPane.ERROR_MESSAGE);

        }
        

}

    
    private static String generarCodigoUnico() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    
    
    }
    
    
    
    
    
}
