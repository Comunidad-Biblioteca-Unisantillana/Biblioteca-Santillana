package moduloPrestamo;

import java.sql.Date;

/**
 * Entidad Prestamo_Diccionario.
 * @author Camilo
 */
public class Prestamo{
    
    private String codBarrasRecurso;
    private char devuelto;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String tituloRecurso;

    public Prestamo(String codBarrasRecurso, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarrasRecurso = codBarrasRecurso;
        this.tituloRecurso = tituloRecurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getCodBarrasRecurso() {
        return codBarrasRecurso;
    }

    public char getDevuelto() {
        return devuelto;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getTituloRecurso() {
        return tituloRecurso;
    }

    public void setCodBarrasRecurso(String codBarrasRecurso) {
        this.codBarrasRecurso = codBarrasRecurso;
    }

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setTituloRecurso(String tituloRecurso) {
        this.tituloRecurso = tituloRecurso;
    }

}