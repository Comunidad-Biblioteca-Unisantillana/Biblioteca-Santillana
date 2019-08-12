
package moduloPrestamo;

import java.sql.Date;

/**
 * entidad Prestamo_Enciclopedio_Estudiante
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoEnciclopediaEst {
    
    private int codPrestamoEnciclopediaEst;
    private String codBarraEnciclopedia,codEstudiante, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraEnciclopedia
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoEnciclopediaEst(String codBarraEnciclopedia, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoEnciclopediaEst() {
        return codPrestamoEnciclopediaEst;
    }

    public String getCodBarraEnciclopedia() {
        return codBarraEnciclopedia;
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

    public char getDevuelto() {
        return devuelto;
    }

    public void setCodPrestamoEnciclopediaEst(int codPrestamoEnciclopediaEst) {
        this.codPrestamoEnciclopediaEst = codPrestamoEnciclopediaEst;
    }

    public void setCodBarraEnciclopedia(String codBarraEnciclopedia) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }
}
