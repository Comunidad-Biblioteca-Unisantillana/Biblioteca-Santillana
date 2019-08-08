package entitys;

import java.sql.Date;

/**
 * Entidad Prestamo_Enciclopedia.
 * @author Camilo
 */
public class PrestamoEnciclopedia {
    private int codPrestamoEnciclopedia;
    private String codBarraEnciclopedia;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public PrestamoEnciclopedia(String codBarraEnciclopedia, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoEnciclopedia() {
        return codPrestamoEnciclopedia;
    }

    public void setCodPrestamoEnciclopedia(int codPrestamoEnciclopedia) {
        this.codPrestamoEnciclopedia = codPrestamoEnciclopedia;
    }

    public String getCodBarraEnciclopedia() {
        return codBarraEnciclopedia;
    }

    public void setCodBarraEnciclopedia(String codBarraEnciclopedia) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
}
