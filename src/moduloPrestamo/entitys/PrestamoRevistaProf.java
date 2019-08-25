package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Revista Profesor.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoRevistaProf {

    private int codPrestamoRevistaProf;
    private String codBarraRevista;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoRevistaProf() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarraRevista
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoRevistaProf(String codBarraRevista, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraRevista = codBarraRevista;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
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

    public String getDevuelto() {
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

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
