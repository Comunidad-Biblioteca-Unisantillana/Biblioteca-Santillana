
package moduloPrestamo.entitys;

import java.sql.Date;
/**
 * entidad Prestamo Revista Estudiante
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:12/08/2019
 */
public class PrestamoRevistaEst {

    private int codPrestamoRevistaEst;
    private String codBarraRevista;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private char devuelto;

    public PrestamoRevistaEst(String codBarraRevista, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraRevista = codBarraRevista;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoRevistaEst() {
        return codPrestamoRevistaEst;
    }

    public String getCodBarraRevista() {
        return codBarraRevista;
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

    public void setCodPrestamoRevistaEst(int codPrestamoRevistaEst) {
        this.codPrestamoRevistaEst = codPrestamoRevistaEst;
    }

    public void setCodBarraRevista(String codBarraRevista) {
        this.codBarraRevista = codBarraRevista;
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
