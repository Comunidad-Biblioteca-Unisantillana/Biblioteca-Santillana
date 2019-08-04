package entitys;

import java.sql.Date;

/**
 * Entidad Prestamo_Mapa.
 * @author Camilo
 */
public class PrestamoMapa {
    
    private int codPrestamoMapa;
    private String codBarraMapa;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public PrestamoMapa(String codBarraMapa, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraMapa = codBarraMapa;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoMapa() {
        return codPrestamoMapa;
    }

    public void setCodPrestamoMapa(int codPrestamoMapa) {
        this.codPrestamoMapa = codPrestamoMapa;
    }

    public String getCodBarraMapa() {
        return codBarraMapa;
    }

    public void setCodBarraMapa(String codBarraMapa) {
        this.codBarraMapa = codBarraMapa;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
}
