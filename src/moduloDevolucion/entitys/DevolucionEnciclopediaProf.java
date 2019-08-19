package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:30 a. m.
 */
public class DevolucionEnciclopediaProf {

    private int codDevolucionEnciclopediaProf;
    private int codPrestamoEnciclopediaProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionEnciclopediaProf(int codPrestamoEnciclopediaProf, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoEnciclopediaProf = codPrestamoEnciclopediaProf;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionEnciclopediaProf() {
        return codDevolucionEnciclopediaProf;
    }

    public int getCodPrestamoEnciclopediaProf() {
        return codPrestamoEnciclopediaProf;
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

    public void setCodDevolucionEnciclopediaProf(int codDevolucionEnciclopediaProf) {
        this.codDevolucionEnciclopediaProf = codDevolucionEnciclopediaProf;
    }

    public void setCodPrestamoEnciclopediaProf(int codPrestamoEnciclopediaProf) {
        this.codPrestamoEnciclopediaProf = codPrestamoEnciclopediaProf;
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
