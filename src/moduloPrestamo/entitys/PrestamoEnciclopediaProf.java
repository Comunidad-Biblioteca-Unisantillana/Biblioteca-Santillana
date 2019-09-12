package moduloPrestamo.entitys;

import java.util.Date;

/**
 * clase de la entidad Prestamo Enciclopedia Profesor.
 *
 * @author Julian
 * @creado 11/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoEnciclopediaProf {

    private int codPrestamoEnciclopediaProf;
    private String codBarraEnciclopedia;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clse sin parámetros.
     */
    public PrestamoEnciclopediaProf(){
        
    }
    
    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarraEnciclopedia
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoEnciclopediaProf(String codBarraEnciclopedia, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraEnciclopedia = codBarraEnciclopedia;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoEnciclopediaProf() {
        return codPrestamoEnciclopediaProf;
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

    public String getDevuelto() {
        return devuelto;
    }

    public void setCodPrestamoEnciclopediaProf(int codPrestamoEnciclopediaProf) {
        this.codPrestamoEnciclopediaProf = codPrestamoEnciclopediaProf;
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

    public void setDevuelto(String devuelto) {
        this.devuelto = devuelto;
    }
    
}
