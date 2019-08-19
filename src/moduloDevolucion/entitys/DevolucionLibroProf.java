package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:39 a. m.
 */
public class DevolucionLibroProf {

    private int codDevolucionLibroProf;
    private int codPrestamoLibroProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionLibroProf(int codPrestamoLibroProf, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoLibroProf = codPrestamoLibroProf;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionLibroProf() {
        return codDevolucionLibroProf;
    }

    public int getCodPrestamoLibroProf() {
        return codPrestamoLibroProf;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setCodDevolucionLibroProf(int codDevolucionLibroProf) {
        this.codDevolucionLibroProf = codDevolucionLibroProf;
    }

    public void setCodPrestamoLibroProf(int codPrestamoLibroProf) {
        this.codPrestamoLibroProf = codPrestamoLibroProf;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }
}
