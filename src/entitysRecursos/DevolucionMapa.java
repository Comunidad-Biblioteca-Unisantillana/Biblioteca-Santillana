package entitys;

import java.util.Date;

/**
 * Entidad Devolucion_Mapa.
 * @author Camilo
 */
public class DevolucionMapa {
    
    private int codDevolucionMapa;
    private int codPrestamoMapa;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;   

    public DevolucionMapa(int codPrestamoMapa, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoMapa = codPrestamoMapa;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionMapa() {
        return codDevolucionMapa;
    }

    public void setCodDevolucionMapa(int codDevolucionMapa) {
        this.codDevolucionMapa = codDevolucionMapa;
    }

    public int getCodPrestamoMapa() {
        return codPrestamoMapa;
    }

    public void setCodPrestamoMapa(int codPrestamoMapa) {
        this.codPrestamoMapa = codPrestamoMapa;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }
    
    
}
