/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author User
 */
public class Documento {
    private String nombre;
    private int id;

    public Documento() {
    }

    public Documento(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Documento{" + "nombre=" + nombre + ", id=" + id + '}';
    }
    
    
    
}
