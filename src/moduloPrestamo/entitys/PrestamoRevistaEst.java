package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Revista Estudiante.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoRevistaEst {

    private int codPrestamoRevistaEst;
    private String codBarraRevista;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámettros.
     */
    public PrestamoRevistaEst() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarraRevista
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoRevistaEst(String codBarraRevista, String codEstudiante, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraRevista = codBarraRevista;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
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

    public String getDevuelto() {
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

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
