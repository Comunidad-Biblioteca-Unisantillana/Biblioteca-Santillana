package moduloPrestamo.modelo;

import java.sql.Date;

/**
 * clase con datos comunes en las entidades de préstamos.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class Prestamo {

    private String codBarrasRecurso;
    private String tituloRecurso;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    /**
     * consatructor de la clase sin parámetros.
     */
    public Prestamo() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarrasRecurso
     * @param tituloRecurso
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public Prestamo(String codBarrasRecurso, String tituloRecurso, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarrasRecurso = codBarrasRecurso;
        this.tituloRecurso = tituloRecurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getCodBarrasRecurso() {
        return codBarrasRecurso;
    }

    public String getTituloRecurso() {
        return tituloRecurso;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setCodBarrasRecurso(String codBarrasRecurso) {
        this.codBarrasRecurso = codBarrasRecurso;
    }

    public void setTituloRecurso(String tituloRecurso) {
        this.tituloRecurso = tituloRecurso;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

}
