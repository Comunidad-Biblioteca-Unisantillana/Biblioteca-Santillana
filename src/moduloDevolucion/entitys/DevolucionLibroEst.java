package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:37 a. m.
 */
public class DevolucionLibroEst {

    private int codDevolucionLibroEst;
    private int codPrestamoLibroEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionLibroEst(int codPrestamoLibroEst, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionLibroEst() {
        return codDevolucionLibroEst;
    }

    public int getCodPrestamoLibroEst() {
        return codPrestamoLibroEst;
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

    public void setCodDevolucionLibroEst(int codDevolucionLibroEst) {
        this.codDevolucionLibroEst = codDevolucionLibroEst;
    }

    public void setCodPrestamoLibroEst(int codPrestamoLibroEst) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
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
