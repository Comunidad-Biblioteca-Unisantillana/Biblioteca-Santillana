
package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Libro Estudiante
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoLibroEst {
    
    private int codPrestamoLibroEst;
    private String codBarraLibro,codEstudiante, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private int numRenovaciones;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraLibro
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoLibroEst(String codBarraLibro, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraLibro = codBarraLibro;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public char getDevuelto() {
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }

    
}
