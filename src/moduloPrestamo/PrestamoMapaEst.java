
package moduloPrestamo;

import java.sql.Date;

/**
 * entidad Prestamo Mapa Estudiante
 *
 * @author Julian 
 * Fecha creación:11/08/2019 
 * Fecha ultima modificación:11/08/2019
 */
public class PrestamoMapaEst {
    
    private int codPrestamoMapaEst;
    private String codBarraMapa,codEstudiante, idBibliotecario;
    private Date fechaPrestamo, fechaDevolucion;
    private char devuelto;
    
    /**
     * Constructor de la clase
     * @param codBarraMapa
     * @param codEstudiante
     * @param idBibliotecario
     * @param fechaPrestamo
     * @param fechaDevolucion
     */
    public PrestamoMapaEst(String codBarraMapa, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraMapa = codBarraMapa;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoMapaEst() {
        return codPrestamoMapaEst;
    }

    public String getCodBarraMapa() {
        return codBarraMapa;
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

    public void setCodPrestamoMapaEst(int codPrestamoMapaEst) {
        this.codPrestamoMapaEst = codPrestamoMapaEst;
    }

    public void setCodBarraMapa(String codBarraMapa) {
        this.codBarraMapa = codBarraMapa;
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
