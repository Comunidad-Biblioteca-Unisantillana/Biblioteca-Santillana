package entitysRecursos;

import java.util.Date;

/**
 * Entidad Devolucion_Revista.
 * @author Camilo
 */
public class DevolucionRevista {
    
    private int codDevolucionRevista;
    private int codPrestamoRevista;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionRevista(int codPrestamoRevista, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoRevista = codPrestamoRevista;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionRevista() {
        return codDevolucionRevista;
    }

    public void setCodDevolucionRevista(int codDevolucionRevista) {
        this.codDevolucionRevista = codDevolucionRevista;
    }

    public int getCodPrestamoRevista() {
        return codPrestamoRevista;
    }

    public void setCodPrestamoRevista(int codPrestamoRevista) {
        this.codPrestamoRevista = codPrestamoRevista;
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
