package entitys;

import java.util.Date;

/**
 * Entidad Devolucion_Enciclopedia.
 * @author Camilo
 */
public class DevolucionEnciclopedia {
    
    private int codDevolucionEnciclopedia;
    private int codPrestamoEnciclopedia;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionEnciclopedia(int codPrestamoEnciclopedia, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoEnciclopedia = codPrestamoEnciclopedia;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionEnciclopedia() {
        return codDevolucionEnciclopedia;
    }

    public void setCodDevolucionEnciclopedia(int codDevolucionEnciclopedia) {
        this.codDevolucionEnciclopedia = codDevolucionEnciclopedia;
    }

    public int getCodPrestamoEnciclopedia() {
        return codPrestamoEnciclopedia;
    }

    public void setCodPrestamoEnciclopedia(int codPrestamoEnciclopedia) {
        this.codPrestamoEnciclopedia = codPrestamoEnciclopedia;
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
