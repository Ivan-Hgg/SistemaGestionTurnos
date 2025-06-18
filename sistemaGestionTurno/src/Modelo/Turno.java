
package Modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Ivan y otros
 */
public class Turno {//clase de turno. Describir sus atributos y funciones.
    private int id;
    private LocalDateTime fechaTurno;//FECHA Y HORA DEL TURNO
    private int idDoc;//tipo de la nota/documento a presentar
    private String codigoSeg;//codigo unico genreado aleatoriamente que identidica univocamente el turno. ID
    private int idAlum;//para relacion del legajo del alumno con su turno
    int idInt;
    public Turno() {
    }

    public Turno(int id, LocalDateTime fechaTurno, int idDoc, String codigoSeg, int idAlum, int idInt) {
        this.id = id;
        this.fechaTurno = fechaTurno;
        this.idDoc = idDoc;
        this.codigoSeg = codigoSeg;
        this.idAlum = idAlum;
        this.idInt = idInt;
    }

    public Turno(LocalDateTime fechaTurno, int idDoc, String codigoSeg, int idAlum, int idInt) {
        this.fechaTurno = fechaTurno;
        this.idDoc = idDoc;
        this.codigoSeg = codigoSeg;
        this.idAlum = idAlum;
        this.idInt = idInt;
    }
    
    

    
    
    public String getCodigoSeg() {
        return codigoSeg;
    }

    public void setCodigoSeg(String codigoSeg) {
        this.codigoSeg = codigoSeg;
    }

    public LocalDateTime getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDateTime fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public int getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public int getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(int idAlum) {
        this.idAlum = idAlum;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInt() {
        return idInt;
    }

    public void setIdInt(int idInt) {
        this.idInt = idInt;
    }

    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", fechaTurno=" + fechaTurno + ", idDoc=" + idDoc + ", codigoSeg=" + codigoSeg + ", idAlum=" + idAlum + ", idInt=" + idInt + '}';
    }

    
    
    

    
      
}
