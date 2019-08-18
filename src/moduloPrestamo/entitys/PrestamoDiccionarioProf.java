
package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Diccionario Profesor
 *
 * @author Julian 
 * Fecha creación:10/08/2019 
 * Fecha ultima modificación:10/08/2019
 */
public class PrestamoDiccionarioProf {
    
    private int codPrestamoDiccionarioProf;
    private String codBarraDiccionario, idProfesor, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraDiccionario
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoDiccionarioProf(String codBarraDiccionario, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraDiccionario = codBarraDiccionario;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public char getDevuelto() {
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }

    
}
