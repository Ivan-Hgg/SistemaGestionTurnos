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
}
