
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Ivan y otros
 */
public class Turnos {//clase especial para el arraylist de Turnos
    private static ArrayList <Turno> turnos = new ArrayList<>();


    public static ArrayList <Turno> getTurnos() {
        return turnos;
    }

    public static void setTurnos(ArrayList <Turno> aTurnos) {
        turnos = aTurnos;
    }

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
