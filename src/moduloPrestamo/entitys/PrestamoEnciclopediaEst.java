package moduloPrestamo.entitys;

import java.util.Date;

/**
 * clase de la entidad Prestamo Enciclopedia Estudiante.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaEst {

    private int codPrestamoEnciclopediaEst;
    private String codBarraEnciclopedia;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoEnciclopediaEst() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraEnciclopedia
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoEnciclopediaEst(String codBarraEnciclopedia, String codEstudiante, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
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

    public String getDevuelto() {
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

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }

}
