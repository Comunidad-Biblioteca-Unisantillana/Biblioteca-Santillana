package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionMapaEst {

    private int codDevolucionMapaEst;
    private int codPrestamoMapaEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionMapaEst(int codPrestamoMapaEst, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoMapaEst = codPrestamoMapaEst;
        this.idBibliotecario = idBibliotecario;
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
