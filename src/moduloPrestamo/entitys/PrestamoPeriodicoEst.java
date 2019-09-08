package moduloPrestamo.entitys;

import java.util.Date;

/**
 * clase de la entidad Prestamo Periodico Estudiante.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoPeriodicoEst {

    private int codPrestamoPeriodicoEst;
    private String codBarraPeriodico;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoPeriodicoEst() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarraPeriodico
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoPeriodicoEst(String codBarraPeriodico, String codEstudiante, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraPeriodico = codBarraPeriodico;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoPeriodicoEst() {
        return codPrestamoPeriodicoEst;
    }

    public String getCodBarraPeriodico() {
        return codBarraPeriodico;
    }

    public String getCodEstudiante() {
        return codEstudiante;
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

    public void setCodPrestamoPeriodicoEst(int codPrestamoPeriodicoEst) {
        this.codPrestamoPeriodicoEst = codPrestamoPeriodicoEst;
    }

    public void setCodBarraPeriodico(String codBarraPeriodico) {
        this.codBarraPeriodico = codBarraPeriodico;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
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
