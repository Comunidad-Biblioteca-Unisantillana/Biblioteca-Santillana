
package moduloPrestamo.entitys;

import java.sql.Date;

/**
 * entidad Prestamo Mapa Profesor
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoMapaProf {
    
    private int codPrestamoMapaProf;
    private String codBarraMapa,idProfesor, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraMapa
     * @param idProfesor
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoMapaProf(String codBarraMapa, String idProfesor, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraMapa = codBarraMapa;
        this.idProfesor = idProfesor;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoMapaProf() {
        return codPrestamoMapaProf;
    }

    public String getCodBarraMapa() {
        return codBarraMapa;
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

    public void setCodPrestamoMapaProf(int codPrestamoMapaProf) {
        this.codPrestamoMapaProf = codPrestamoMapaProf;
    }

    public void setCodBarraMapa(String codBarraMapa) {
        this.codBarraMapa = codBarraMapa;
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
