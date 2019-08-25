package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Periodico Profesor.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoPeriodicoProf {

    private int codPrestamoPeriodicoProf;
    private String codBarraPeriodico;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase con parámetros.
     */
    public PrestamoPeriodicoProf() {

    }

    /**
     * constructor de la clase sin parámetros.
     *
     * @param codBarraPeriodico
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoPeriodicoProf(String codBarraPeriodico, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraPeriodico = codBarraPeriodico;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoPeriodicoProf() {
        return codPrestamoPeriodicoProf;
    }

    public String getCodBarraPeriodico() {
        return codBarraPeriodico;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setCodPrestamoPeriodicoProf(int codPrestamoPeriodicoEst) {
        this.codPrestamoPeriodicoProf = codPrestamoPeriodicoEst;
    }

    public void setCodBarraPeriodico(String codBarraPeriodico) {
        this.codBarraPeriodico = codBarraPeriodico;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
