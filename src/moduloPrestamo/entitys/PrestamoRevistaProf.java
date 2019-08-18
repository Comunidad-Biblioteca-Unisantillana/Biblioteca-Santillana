package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Revista Profesor
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoRevistaProf {

    private int codPrestamoRevistaProf;
    private String codBarraRevista;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private char devuelto;

    public PrestamoRevistaProf(String codBarraRevista, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraRevista = codBarraRevista;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoRevistaProf() {
        return codPrestamoRevistaProf;
    }

    public String getCodBarraRevista() {
        return codBarraRevista;
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

    public void setCodPrestamoRevistaProf(int codPrestamoRevistaProf) {
        this.codPrestamoRevistaProf = codPrestamoRevistaProf;
    }

    public void setCodBarraRevista(String codBarraRevista) {
        this.codBarraRevista = codBarraRevista;
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
