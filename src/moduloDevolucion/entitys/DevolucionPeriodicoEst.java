package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionPeriodicoEst {

    private int codDevolucionPeriodicoEst;
    private int codPrestamoPeriodicoEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionPeriodicoEst(int codPrestamoPeriodicoEst, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoPeriodicoEst = codPrestamoPeriodicoEst;
        this.idBibliotecario = idBibliotecario;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionPeriodicoEst() {
        return codDevolucionPeriodicoEst;
    }

    public int getCodPrestamoPeriodicoEst() {
        return codPrestamoPeriodicoEst;
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

    public void setCodDevolucionPeriodicoEst(int codDevolucionPeriodicoEst) {
        this.codDevolucionPeriodicoEst = codDevolucionPeriodicoEst;
    }

    public void setCodPrestamoPeriodicoEst(int codPrestamoPeriodicoEst) {
        this.codPrestamoPeriodicoEst = codPrestamoPeriodicoEst;
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
