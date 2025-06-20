/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JOptionPane;


public class BD {
    Connection c;
    String forName="com.mysql.cj.jdbc.Driver";
    String cadenaConexion= "jdbc:mysql://localhost:3306/sgt";
    String usuario="root";
    String contr="";

    //para establecer coneccion en la base de datos 
    public BD() {
        try {
            Class.forName(forName);
            c = DriverManager.getConnection(cadenaConexion, usuario, contr);
            System.out.println("Conectado a la base de datos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //ABMQ DOCUMENTOS -------------------------------------------------------------------------
    public ArrayList<Documento> obtenerDocumentos(){//SI FUNCIONA
        ArrayList<Documento> docs = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT * FROM documentos");
            while(res.next()){
                Documento d = new Documento(res.getString("DOCNOM"), res.getInt("idDOCUMENTOS"));
                docs.add(d);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return docs;
    }
    public int obtenerIDDocumentos(String nom){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT idDOCUMENTOS FROM documentos WHERE DOCNOM='" + nom +"'");
            return res.getInt("idDOCUMENTOS");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    
    public void agregarDocumento(Documento d){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO documentos (DOCNOM) values (?)");
            s.setString(1, d.getNombre());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void modificarDocumento(Documento d){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("UPDATE documentos SET DOCNOM=? WHERE idDOCUMENTOS =?");
            s.setString(1, d.getNombre());
            s.setInt(2, d.getId());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //ELIMINA PASANDOLE EL CODIGO DEL OBJETO DIRECTAMENTE COMO PARAMETRO
    public void eliminarDocumento(int codigo){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM documentos WHERE idDOCUMENTOS="+codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //POLIMORFISMO DE ELIMINAR DOCUMENTO, SACANDO EL ID DESDE EL OBJETO
    public void eliminarDocumento(Documento d){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM documentos WHERE idDOCUMENTOS="+d.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //ABMQ de intervalo ---------------------------------------------------------------------------
    public ArrayList<Intervalo> obtenerIntervalo(){//SI FUNCIONA
        ArrayList<Intervalo> ints = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT * FROM intervalo");
            while(res.next()){
                int id = res.getInt("idINTERVALO");

                // Convertir correctamente
                Timestamp tsIng = res.getTimestamp("FECHING");
                Timestamp tsFin = res.getTimestamp("FECHFIN");

                LocalDateTime fechaIng = tsIng != null ? tsIng.toLocalDateTime() : null;
                LocalDateTime fechaFin = tsFin != null ? tsFin.toLocalDateTime() : null;

                String nombre = res.getString("nombre");

                Intervalo in = new Intervalo(id, fechaIng, fechaFin, nombre);
                ints.add(in);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ints;
    }
    
    public boolean agregarIntervalo(Intervalo I){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO intervalo (FECHING, FECHFIN, nombre) values (?,?,?)");
            s.setObject(1, I.getFechaIng());
            s.setObject(2, I.getFechaOut());
            s.setObject(3, I.getNombre());
            s.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex){
            String msg = ex.getMessage();

            if (msg.contains("FECHING")) {
                JOptionPane.showMessageDialog(null, "ERROR: la fecha de inicio ya esta asignada a un intervalo", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else if (msg.contains("FECHFIN")) {
                JOptionPane.showMessageDialog(null, "ERROR: la fecha final ya fue asignada por otro intervalo", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error de integridad: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
        
    public void modificarIntervalo(Intervalo I){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("UPDATE intervalo SET FECHING=?, FECHFIN=?, nombre=? WHERE idINTERVALO =?");
            s.setObject(1, I.getFechaIng());
            s.setObject(2, I.getFechaOut());
            s.setString(3, I.getNombre());
            s.setInt(4, I.getId());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //ELIMINA PASANDOLE EL CODIGO DEL OBJETO DIRECTAMENTE COMO PARAMETRO
    public void eliminarIntervalo(int codigo){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM intervalo WHERE idINTERVALO="+codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //POLIMORFISMO DE ELIMINAR DOCUMENTO, SACANDO EL ID DESDE EL OBJETO
    public void eliminarIntervalo(Intervalo i){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM intervalo WHERE idINTERVALO="+i.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
    
    //ABMQ USUARIOS ------------------------------------------------------------------------
    public ArrayList<Usuario> obtenerUsuario(){//SI FUNCIONA
        ArrayList<Usuario> usu = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT * FROM usuario");
            while(res.next()){
                Usuario u = new Usuario(res.getInt("idUSUARIO"), res.getString("CORREO"), res.getInt("LEGAJO"), res.getString("CONTR"), res.getString("APENOM"), res.getBoolean("TIPOUSU"));
                usu.add(u);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return usu;
    }
    
    public int obtenerIDUsuario(int legajo){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT idUSUARIO FROM usuario WHERE legajo='" + legajo +"'");
            return res.getInt("idUSUARIO");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    }

    
    
    public boolean agregarUsuario(Usuario u){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO usuario (APENOM, CORREO, LEGAJO, CONTR) values (?,?,?,?)");
            s.setString(1, u.getApeNom());
            s.setString(2, u.getCorreo());
            s.setInt(3, u.getLegajo());
            s.setString(4, u.getContraseña());
            s.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException ex){
            String msg = ex.getMessage();

            if (msg.contains("CORREO")) {
                JOptionPane.showMessageDialog(null, "ERROR: El correo ya está registrado", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else if (msg.contains("LEGAJO")) {
                JOptionPane.showMessageDialog(null, "ERROR: El legajo ya está registrado", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error de integridad: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void modificarContrUsuario(Usuario u){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("UPDATE usuario SET CONTR=? WHERE LEGAJO =?");
            s.setString(1, u.getContraseña());
            s.setInt(2, u.getLegajo());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //ELIMINA PASANDOLE EL LEGAJO DEL OBJETO DIRECTAMENTE COMO PARAMETRO
    public void eliminarUsuario(int leg){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM usuario WHERE LEGAJO="+leg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //POLIMORFISMO DE ELIMINAR DOCUMENTO, SACANDO EL LEGAJO DESDE EL OBJETO
    public void eliminarUsuario(Usuario u){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM usuario WHERE LEGAJO="+u.getLegajo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //ABMQ TURNOS -------------------------------------------------------------------------------------
    public ArrayList<Turno> obtenerTurno(){//SI FUNCIONA
        ArrayList<Turno> turs = new ArrayList<>();
        try {
            Statement s = c.createStatement();
            ResultSet res= s.executeQuery("SELECT * FROM turnos ORDER BY FECHTUR ASC");
            while(res.next()){
                int idT = res.getInt("idTURNOS");
                int idI= res.getInt("idINTERVALO");
                int idU= res.getInt("idUSUARIO");
                int idD= res.getInt("idDOCUMENTO");
                String codseg= res.getString("CODSEG");

                // Convertir correctamente
                Timestamp tsIng = res.getTimestamp("FECHTUR");

                LocalDateTime fechaTur = tsIng != null ? tsIng.toLocalDateTime() : null;

                Turno tur = new Turno(idT, fechaTur, idD, codseg, idU, idI);
                turs.add(tur);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return turs;
    }
    
    
    public boolean agregarTurno(Turno t, String ruta){//la ruta es para acceder al archivo y subirlo a la BD
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO turnos (idINTERVALO, idUSUARIO, idDOCUMENTOS, CODSEG, FECHTUR) values (?,?,?,?,?)");
            s.setInt(1, t.getIdInt());
            s.setInt(2, t.getIdAlum());
            s.setInt(3, t.getIdDoc());
            s.setString(4, t.getCodigoSeg());
            s.setObject(5, t.getFechaTurno());
            
            s.executeUpdate();
            return true;
        }catch (SQLIntegrityConstraintViolationException ex){
            String msg = ex.getMessage();

            if (msg.contains("CODSEG")) {
                JOptionPane.showMessageDialog(null, "ERROR: el codigo de seguridad fue mal generado", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else if (msg.contains("FECHTUR")) {
                JOptionPane.showMessageDialog(null, "ERROR: la fecha ya está ocupada", "Duplicado", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error de integridad: " + msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    //ELIMINA PASANDOLE EL LEGAJO DEL OBJETO DIRECTAMENTE COMO PARAMETRO
    public void eliminarTurno(String codseg){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("DELETE FROM turnos WHERE CODSEG = ?");
            s.setString(1, codseg);
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //POLIMORFISMO DE ELIMINAR DOCUMENTO, SACANDO EL LEGAJO DESDE EL OBJETO
    public void eliminarTurno(Turno t){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("DELETE FROM turnos WHERE CODSEG = ?");
            s.setString(1, t.getCodigoSeg());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
}
