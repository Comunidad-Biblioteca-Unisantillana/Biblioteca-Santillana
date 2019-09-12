package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionPeriodicoProf {

    private int codDevolucionPeriodicoProf;
    private int codPrestamoPeriodicoProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionPeriodicoProf(int codPrestamoPeriodicoProf, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoPeriodicoProf = codPrestamoPeriodicoProf;
        this.idBibliotecario = idBibliotecario;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionPeriodicoProf() {
        return codDevolucionPeriodicoProf;
    }

    public int getCodPrestamoPeriodicoProf() {
        return codPrestamoPeriodicoProf;
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

    public void setCodDevolucionPeriodicoProf(int codDevolucionPeriodicoProf) {
        this.codDevolucionPeriodicoProf = codDevolucionPeriodicoProf;
    }

    public void setCodPrestamoPeriodicoProf(int codPrestamoPeriodicoProf) {
        this.codPrestamoPeriodicoProf = codPrestamoPeriodicoProf;
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
