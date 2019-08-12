package moduloPrestamo;

import java.sql.Date;

/**
 * entidad Prestamo_Diccionario_Estudiante
 *
 * @author Julian 
 * Fecha creación:10/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoDiccionarioEst1 {

    private int codPrestamoDiccionarioEst;
    private String codBarraDiccionario, codEstudiante, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;

    /**
     * Constructor de la clase
     * @param codBarraDiccionario
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoDiccionarioEst1(String codBarraDiccionario, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraDiccionario = codBarraDiccionario;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
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

    public char getDevuelto() {
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

    public void setDevuelto(char devuelto) {
        this.devuelto = devuelto;
    }

    

    

}
