package moduloDevolucion.entitys;

import java.util.Date;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class DevolucionEnciclopediaProf {

    private int codDevolucionEnciclopediaProf;
    private int codPrestamoEnciclopediaProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionEnciclopediaProf(int codPrestamoEnciclopediaProf, String idBibliotecario, String estadoDevolucion) {
        this.codPrestamoEnciclopediaProf = codPrestamoEnciclopediaProf;
        this.idBibliotecario = idBibliotecario;
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
