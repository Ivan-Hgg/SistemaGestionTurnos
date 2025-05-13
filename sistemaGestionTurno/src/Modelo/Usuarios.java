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
    
    
    public static void agregarUsuario(Usuario usu){
        usuarios.add(usu);
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
    
    
    
    public boolean buscarUsuario(String contra, int leg){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(leg==usu.getLegajo() &&contra.equals(usu.getContraseña())){
                r = true;
            }
        }
        return r;
    }
    
    //buscar tipo usuario
    public boolean buscarTipoUsuario(int leg){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(leg==usu.getLegajo()){
                r = usu.isTipoUsu();
            }
        }
        return r;
    }
    
    //buscar correo
    public boolean buscarCorreo(String correo){
        boolean r=false; //false no encontro, true encontro
        for (Usuario usu : usuarios) {
            if(correo.equals(usu.getCorreo())){
                r = true;
            }
            
        }
    return r;
    }

    @Override
    public String toString() {
        return "Usuarios{" + usuarios +'}';
    }
    
    
    
}
