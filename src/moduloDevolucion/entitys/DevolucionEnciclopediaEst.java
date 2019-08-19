package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:28 a. m.
 */
public class DevolucionEnciclopediaEst {

    private int codDevolucionEnciclopediaEst;
    private int codPrestamoEnciclopediaEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionEnciclopediaEst(int codPrestamoEnciclopediaEst, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoEnciclopediaEst = codPrestamoEnciclopediaEst;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionEnciclopediaEst() {
        return codDevolucionEnciclopediaEst;
    }

    public int getCodPrestamoEnciclopediaEst() {
        return codPrestamoEnciclopediaEst;
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

    public void setCodDevolucionEnciclopediaEst(int codDevolucionEnciclopediaEst) {
        this.codDevolucionEnciclopediaEst = codDevolucionEnciclopediaEst;
    }

    public void setCodPrestamoEnciclopediaEst(int codPrestamoEnciclopediaEst) {
        this.codPrestamoEnciclopediaEst = codPrestamoEnciclopediaEst;
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
