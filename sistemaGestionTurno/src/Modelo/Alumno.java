/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Ivan y otros
 */
public class Alumno extends Usuario{
    private int legago;

    public Alumno() {
    }

    public Alumno(int legago) {
        this.legago = legago;
    }

    public Alumno(int legago, String correo, String contraseña, String apeNom, int dni, boolean tipo) {
        super(correo, contraseña, apeNom, dni, tipo);
        this.legago = legago;
    }

    public int getLegago() {
        return legago;
    }

    public void setLegago(int legago) {
        this.legago = legago;
    }

    @Override
    public String toString() {
        return "Alumno{" + "legago=" + legago + super.toString() + "}";
    }
    
    
    //FUNCIONES:
    public void crearTurno(){}
    
}
