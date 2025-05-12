/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Ivan y otros
 */
public class Usuarios {
    private static ArrayList <Usuario> usuarios= new ArrayList<>();

    public Usuarios() {
    }
    
    
    public static void agregarUsuario(Usuario u){
        usuarios.add(u);
    }
    
    public void mostrar (){
        for (Usuario usu : usuarios) {
            System.out.println(usu);
        }
    }
    //revisar 
    public boolean buscarLegajo(int leg){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(usu.getLegajo()==leg){
                r= true;
            }
            return r;
        }
    return false;
    }
    
    public boolean buscarContraseña(String contra){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(usu.getContraseña()==contra){
                r = true;
            }
        }
        return r;
    }
    
    public boolean buscarCorreo(String correo){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(usu.getCorreo()==correo){
                r = true;
            }
        }
        return r;
    }
}
