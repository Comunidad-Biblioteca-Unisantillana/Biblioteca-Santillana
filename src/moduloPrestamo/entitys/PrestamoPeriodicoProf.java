package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Periodico Profesor
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:12/08/2019
 */
public class PrestamoPeriodicoProf {

    private int codPrestamoPeriodicoProf;
    private String codBarraPeriodico;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private char devuelto;

    public PrestamoPeriodicoProf(String codBarraPeriodico, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraPeriodico = codBarraPeriodico;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public char getDevuelto() {
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }
}
