package entitys;

import java.util.Date;

/**
 * Entidad Devolucion_Libro.
 * @author Camilo
 */
public class DevolucionLibro {
    private int codDevolucionLibro;
    private int codPrestamoLibro;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionLibro(int codPrestamoLibro, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoLibro = codPrestamoLibro;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionLibro() {
        return codDevolucionLibro;
    }

    public void setCodDevolucionLibro(int codDevolucionLibro) {
        this.codDevolucionLibro = codDevolucionLibro;
    }

    public int getCodPrestamoLibro() {
        return codPrestamoLibro;
    }

    public void setCodPrestamoLibro(int codPrestamoLibro) {
        this.codPrestamoLibro = codPrestamoLibro;
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
