package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Mapa Estudiante.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoMapaEst {

    private int codPrestamoMapaEst;
    private String codBarraMapa;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoMapaEst() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraMapa
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoMapaEst(String codBarraMapa, String codEstudiante, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraMapa = codBarraMapa;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoMapaEst() {
        return codPrestamoMapaEst;
    }

    public String getCodBarraMapa() {
        return codBarraMapa;
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

    public void setCodPrestamoMapaEst(int codPrestamoMapaEst) {
        this.codPrestamoMapaEst = codPrestamoMapaEst;
    }

    public void setCodBarraMapa(String codBarraMapa) {
        this.codBarraMapa = codBarraMapa;
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
