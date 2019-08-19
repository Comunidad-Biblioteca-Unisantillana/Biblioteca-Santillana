package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:43 a. m.
 */
public class DevolucionMapaEst {

    private int codDevolucionMapaEst;
    private int codPrestamoMapaEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionMapaEst(int codPrestamoMapaEst, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoMapaEst = codPrestamoMapaEst;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionMapaEst() {
        return codDevolucionMapaEst;
    }

    public int getCodPrestamoMapaEst() {
        return codPrestamoMapaEst;
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

    public void setCodDevolucionMapaEst(int codDevolucionMapaEst) {
        this.codDevolucionMapaEst = codDevolucionMapaEst;
    }

    public void setCodPrestamoMapaEst(int codPrestamoMapaEst) {
        this.codPrestamoMapaEst = codPrestamoMapaEst;
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
