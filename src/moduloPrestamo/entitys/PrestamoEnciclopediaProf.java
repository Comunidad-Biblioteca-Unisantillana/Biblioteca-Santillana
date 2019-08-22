
package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo_Enciclopedio_Profesor
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoEnciclopediaProf {
    
    private int codPrestamoEnciclopedioProf;
    private String codBarraEnciclopedia,idProfesor, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;
    
    public PrestamoEnciclopediaProf(String codBarraEnciclopedia, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoEnciclopediaProf() {
        return codPrestamoEnciclopedioProf;
    }

    public String getCodBarraEnciclopedia() {
        return codBarraEnciclopedia;
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

    public void setCodPrestamoEnciclopedioProf(int codPrestamoEnciclopedioProf) {
        this.codPrestamoEnciclopedioProf = codPrestamoEnciclopedioProf;
    }

    public void setCodBarraEnciclopedia(String codBarraEnciclopedia) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
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
