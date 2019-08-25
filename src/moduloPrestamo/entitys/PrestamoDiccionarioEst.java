package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * clase de la entidad Prestamo Diccionario Estudiante.
 *
 * @author Julian
 * @creado 10/08/2019
 * @author Julian
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioEst {

    private int codPrestamoDiccionarioEst;
    private String codBarraDiccionario;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String devuelto;

    /**
     * constructor sin parámetros.
     */
    public PrestamoDiccionarioEst() {

    }

    /**
     * Constructor de la clase con parámetros.
     *
     * @param codBarraDiccionario
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     * @param devuelto
     */
    public PrestamoDiccionarioEst(String codBarraDiccionario, String codEstudiante, String idBibliotecario, 
            Date fechaPrestamo, Date fechaDevolucion, String devuelto) {
        this.codBarraDiccionario = codBarraDiccionario;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getCodPrestamoDiccionarioEst() {
        return codPrestamoDiccionarioEst;
    }

    public String getCodBarraDiccionario() {
        return codBarraDiccionario;
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

    public void setCodPrestamoDiccionarioEst(int codPrestamoDiccionarioEst) {
        this.codPrestamoDiccionarioEst = codPrestamoDiccionarioEst;
    }

    public void setCodBarraDiccionario(String codBarraDiccionario) {
        this.codBarraDiccionario = codBarraDiccionario;
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
