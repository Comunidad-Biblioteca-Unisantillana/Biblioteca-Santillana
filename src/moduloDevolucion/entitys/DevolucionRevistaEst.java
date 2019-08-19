package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:58 a. m.
 */
public class DevolucionRevistaEst {

    private int codDevolucionRevistaEst;
    private int codPrestamoRevistaEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionRevistaEst(int codPrestamoRevistaEst, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoRevistaEst = codPrestamoRevistaEst;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionRevistaEst() {
        return codDevolucionRevistaEst;
    }

    public int getCodPrestamoRevistaEst() {
        return codPrestamoRevistaEst;
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

    public void setCodDevolucionRevistaEst(int codDevolucionRevistaEst) {
        this.codDevolucionRevistaEst = codDevolucionRevistaEst;
    }

    public void setCodPrestamoRevistaEst(int codPrestamoRevistaEst) {
        this.codPrestamoRevistaEst = codPrestamoRevistaEst;
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
