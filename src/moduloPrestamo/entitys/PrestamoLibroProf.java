package moduloPrestamo.entitys;

import java.util.Date;

/**
 * clase de la entidad Prestamo Libro Profesor.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoLibroProf {

    private int codPrestamoLibroProf;
    private String codBarraLibro;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private int numRenovaciones;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoLibroProf() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraLibro
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoLibroProf(String codBarraLibro, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, int numRenovaciones, String devuelto) {
        this.codBarraLibro = codBarraLibro;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.numRenovaciones = numRenovaciones;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoLibroProf() {
        return codPrestamoLibroProf;
    }

    public String getCodBarraLibro() {
        return codBarraLibro;
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

    public int getNumRenovaciones() {
        return numRenovaciones;
    }

    public String getDevuelto() {
        return devuelto;
    }

    public void setCodPrestamoLibroProf(int codPrestamoLibroProf) {
        this.codPrestamoLibroProf = codPrestamoLibroProf;
    }

    public void setCodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
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

    public void setNumRenovaciones(int numRenovaciones) {
        this.numRenovaciones = numRenovaciones;
    }

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
