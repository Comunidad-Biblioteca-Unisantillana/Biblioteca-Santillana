package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionEnciclopediaEst {

    private int codDevolucionEnciclopediaEst;
    private int codPrestamoEnciclopediaEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionEnciclopediaEst(int codPrestamoEnciclopediaEst, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoEnciclopediaEst = codPrestamoEnciclopediaEst;
        this.idBibliotecario = idBibliotecario;
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
