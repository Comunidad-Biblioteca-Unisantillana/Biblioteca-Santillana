package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Libro Estudiante.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroEst {

    private int codPrestamoLibroEst;
    private String codBarraLibro;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int numRenovaciones;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroEst() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraLibro
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoLibroEst(String codBarraLibro, String codEstudiante, String idBibliotecario,
            Date fechaPrestamo, Date fechaDevolucion, int numRenovaciones, String devuelto) {
        this.codBarraLibro = codBarraLibro;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.numRenovaciones = numRenovaciones;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoLibroEst() {
        return codPrestamoLibroEst;
    }

    public String getCodBarraLibro() {
        return codBarraLibro;
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

    public int getNumRenovaciones() {
        return numRenovaciones;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setCodPrestamoLibroEst(int codPrestamoLibroEst) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
    }

    public void setCodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
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

    public void setNumRenovaciones(int numRenovaciones) {
        this.numRenovaciones = numRenovaciones;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
