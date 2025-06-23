
package Modelo;
import java.time.LocalDateTime;


/**
 *
 * @author Ivan y otros
 */
public class Intervalo {
    private int id;
    private LocalDateTime fechaIng;
    private LocalDateTime fechaOut;
    private String nombre;//esto es para poder identificarlo,

    public Intervalo(int id, LocalDateTime fechaIng, LocalDateTime fechaOut, String nombre) {
        this.id = id;
        this.fechaIng = fechaIng;
        this.fechaOut = fechaOut;
        this.nombre = nombre;
    }
//ESTE CONTROLADOR ES POR SI SE OLVIDA DE PONER EL NOMBRE EL ADMIN, EN LA BD SE LE ASIGNA UNO POR DEFAULT
    public Intervalo(int id, LocalDateTime fechaIng, LocalDateTime fechaOut) {
        this.id = id;
        this.fechaIng = fechaIng;
        this.fechaOut = fechaOut;
        this.nombre = "Intervalo Sin Nombre";
    }

    

    public Intervalo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(LocalDateTime fechaIng) {
        this.fechaIng = fechaIng;
    }

    public LocalDateTime getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(LocalDateTime fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Intervalo{" + "id=" + id + ", fechaIng=" + fechaIng + ", fechaOut=" + fechaOut + ", nombre=" + nombre + '}';
    }

    
}
