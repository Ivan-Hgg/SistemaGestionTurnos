/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    
    public void agregarIntervalo(Intervalo I){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO intervalo (FECHING, FECHFIN, nombre) values (?,?,?)");
            s.setObject(1, I.getFechaIng());
            s.setObject(2, I.getFechaOut());
            s.setObject(3, I.getNombre());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    
    
    public void agregarUsuario(Usuario u){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("INSERT INTO usuario (APENOM, CORREO, LEGAJO, CONTR) values (?,?,?,?)");
            s.setString(1, u.getApeNom());
            s.setString(2, u.getCorreo());
            s.setInt(3, u.getLegajo());
            s.setString(4, u.getContraseña());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void modificarContrUsuario(Usuario u){//SI FUNCIONA
        try {
            PreparedStatement s = c.prepareStatement("UPDATE usuario SET CONTR=? WHERE idUSUARIO =?");
            s.setString(1, u.getContraseña());
            s.setInt(2, u.getId());
            s.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //ELIMINA PASANDOLE EL CODIGO DEL OBJETO DIRECTAMENTE COMO PARAMETRO
    public void eliminarUsuario(int codigo){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM usuario WHERE idUSUARIO="+codigo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //POLIMORFISMO DE ELIMINAR DOCUMENTO, SACANDO EL ID DESDE EL OBJETO
    public void eliminarUsuario(Usuario u){//SI FUNCIONA
        try {
            Statement s = c.createStatement();
            s.executeUpdate("DELETE FROM usuario WHERE idUSUARIO="+u.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    
    
}
