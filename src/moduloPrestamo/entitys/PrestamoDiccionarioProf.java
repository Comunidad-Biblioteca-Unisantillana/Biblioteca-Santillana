package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Diccionario Profesor.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioProf {

    private int codPrestamoDiccionarioProf;
    private String codBarraDiccionario;
    private String idProfesor;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioProf() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codBarraDiccionario
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devueto
     */
    public PrestamoDiccionarioProf(String codBarraDiccionario, String idProfesor, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraDiccionario = codBarraDiccionario;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoDiccionarioProf() {
        return codPrestamoDiccionarioProf;
    }

    public String getCodBarraDiccionario() {
        return codBarraDiccionario;
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

    public void setCodPrestamoDiccionarioProf(int codPrestamoDiccionarioProf) {
        this.codPrestamoDiccionarioProf = codPrestamoDiccionarioProf;
    }

    public void setCodBarraDiccionario(String codBarraDiccionario) {
        this.codBarraDiccionario = codBarraDiccionario;
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
