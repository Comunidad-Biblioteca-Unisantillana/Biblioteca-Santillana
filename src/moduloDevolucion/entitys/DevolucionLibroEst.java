package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionLibroEst {

    private int codDevolucionLibroEst;
    private int codPrestamoLibroEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionLibroEst(int codPrestamoLibroEst, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
        this.idBibliotecario = idBibliotecario;
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
