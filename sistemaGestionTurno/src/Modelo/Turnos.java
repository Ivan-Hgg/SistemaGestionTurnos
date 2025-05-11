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
public class Turnos {//clase especial para el arraylist de Turnos
    private static ArrayList <Turno> turnos= new ArrayList<>();

    public Turnos() {
    }
    
    public static void agregarTurnos(Turno t){
        turnos.add(t);
    }
    
    public void mostrar (){
        for (Turno turno : turnos) {
            System.out.println(turno);
        }
    }
    
}
