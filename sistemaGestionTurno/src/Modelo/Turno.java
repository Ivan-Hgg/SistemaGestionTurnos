/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Ivan y otros
 */
public class Turno {//clase de turno. Describir sus atributos y funciones.
    private Fecha fechaTurno = new Fecha();//FECHA Y HORA DEL TURNO
    private String tipoNota;//tipo de la nota/documento a presentar
    private String codigoSeg;//codigo unico genreado aleatoriamente que identidica univocamente el turno. ID
    private Alumno alum= new Alumno();//para relacion del legajo del alumno con su turno

    public Turno() {
    }

    public Turno(String tipoNota, String codigoSeg, Fecha fecha, Alumno alum) {
        this.tipoNota = tipoNota;
        this.codigoSeg = codigoSeg;
        this.fechaTurno = fecha;
        this.alum= alum;
        
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }

    public String getCodigoSeg() {
        return codigoSeg;
    }

    public void setCodigoSeg(String codigoSeg) {
        this.codigoSeg = codigoSeg;
    }

    public Alumno getAlum() {
        return alum;
    }

    public void setAlum(Alumno alum) {
        this.alum = alum;
    }

    public Fecha getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(Fecha fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    @Override
    public String toString() {
        return "Turno{" + "fechaTurno=" + fechaTurno + ", tipoNota=" + tipoNota + ", codigoSeg=" + codigoSeg + ", alum=" + alum + '}';
    }
    
    
    
    
    
    
}
