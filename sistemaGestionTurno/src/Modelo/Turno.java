
package Modelo;

/**
 *
 * @author Ivan y otros
 */
public class Turno {//clase de turno. Describir sus atributos y funciones.
    private Fecha fechaTurno = new Fecha();//FECHA Y HORA DEL TURNO
    private String tipoNota;//tipo de la nota/documento a presentar
    private String codigoSeg;//codigo unico genreado aleatoriamente que identidica univocamente el turno. ID
    private Usuario alum= new Usuario();//para relacion del legajo del alumno con su turno

    public Turno() {
    }

    public Turno(String tipoNota, String codigoSeg, Fecha fecha, Usuario alum) {
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

    public Usuario getAlum() {
        return alum;
    }

    public void setAlum(Usuario alum) {
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
