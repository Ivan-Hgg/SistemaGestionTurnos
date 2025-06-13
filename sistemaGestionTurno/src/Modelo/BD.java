/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.sql.*;
import java.util.ArrayList;

public class BD {
    Connection c;
    String forName="com.mysql.cj.jdbc.Driver";
    String cadenaConexion= "jdbc:mysql://localhost:3306/sgt";
    String usuario="root";
    String contr="MYSQL1259";//esto se cambia segun la compu de cada uno

    public BD() {//SI FUNCIONA
        try {
            Class.forName(forName);
            c = DriverManager.getConnection(cadenaConexion, usuario, contr);
            System.out.println("Conectado a la base de datos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
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
        
        //agregar ABM de todos los modelos
        
        
    }
    
    
    
}
