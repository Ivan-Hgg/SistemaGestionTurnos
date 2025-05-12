/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Ivan y otros
 */
public class Usuario { //EN USUARIO VA A ENGLOBAR A ALUMNO Y A ADMINISTRADOR. SU DIFERENCIA ESTÁ
    //EN LA VARIBLE BOLEANA DE TIPO
    private String correo;
    private int legajo;
    private String contraseña;
    private String apeNom;
    private int dni;
    private boolean tipoUsu;//false para admin, true para alumno
    //private Fecha fechaAlta = new Fecha();// POR AHORA NO ES NECESARIO fecha en que se creo la cuenta del usuario

    public Usuario() {
    }

    public Usuario(String correo, String contraseña, String apeNom, int dni, boolean tipo, int legajo) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.apeNom = apeNom;
        this.dni = dni;
        this.tipoUsu = tipo;
        this.legajo = legajo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getApeNom() {
        return apeNom;
    }

    public void setApeNom(String apeNom) {
        this.apeNom = apeNom;
    }

    public boolean isTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(boolean tipoUsu) {
        this.tipoUsu = tipoUsu;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "correo=" + correo + ", legago=" + legajo + ", contrase\u00f1a=" + contraseña + ", apeNom=" + apeNom + ", dni=" + dni + ", tipoUsu=" + tipoUsu + '}';
    }
   
    
}
