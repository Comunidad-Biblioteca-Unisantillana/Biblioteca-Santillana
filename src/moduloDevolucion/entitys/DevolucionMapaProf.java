package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:46 a. m.
 */
public class DevolucionMapaProf {

    private int codDevolucionMapaProf;
    private int codPrestamoMapaProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionMapaProf(int codPrestamoMapaProf, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoMapaProf = codPrestamoMapaProf;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionMapaProf() {
        return codDevolucionMapaProf;
    }

    public int getCodPrestamoMapaProf() {
        return codPrestamoMapaProf;
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

    public void setCodDevolucionMapaProf(int codDevolucionMapaProf) {
        this.codDevolucionMapaProf = codDevolucionMapaProf;
    }

    public void setCodPrestamoMapaProf(int codPrestamoMapaProf) {
        this.codPrestamoMapaProf = codPrestamoMapaProf;
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
