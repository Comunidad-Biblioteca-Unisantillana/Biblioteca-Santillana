
package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Libro Profesor
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoLibroProf {
    private int codPrestamoLibroProf;
    private String codBarraLibro,idProfesor, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private int numRenovaciones;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraLibro
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoLibroProf(String codBarraLibro, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraLibro = codBarraLibro;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public char getDevuelto() {
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }

    
}
