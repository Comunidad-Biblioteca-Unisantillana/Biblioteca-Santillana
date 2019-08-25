package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Mapa Profesor.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoMapaProf {

    private int codPrestamoMapaProf;
    private String codBarraMapa;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaProf() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraMapa
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoMapaProf(String codBarraMapa, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraMapa = codBarraMapa;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoMapaProf() {
        return codPrestamoMapaProf;
    }

    public String getCodBarraMapa() {
        return codBarraMapa;
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

    public void setCodPrestamoMapaProf(int codPrestamoMapaProf) {
        this.codPrestamoMapaProf = codPrestamoMapaProf;
    }

    public void setCodBarraMapa(String codBarraMapa) {
        this.codBarraMapa = codBarraMapa;
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
