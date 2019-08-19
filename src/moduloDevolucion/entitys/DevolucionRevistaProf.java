package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:38:01 a. m.
 */
public class DevolucionRevistaProf {

    private int codDevolucionRevistaProf;
    private int codPrestamoRevistaProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionRevistaProf(int codPrestamoRevistaProf, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoRevistaProf = codPrestamoRevistaProf;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionRevistaProf() {
        return codDevolucionRevistaProf;
    }

    public int getCodPrestamoRevistaProf() {
        return codPrestamoRevistaProf;
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

    public void setCodDevolucionRevistaProf(int codDevolucionRevistaProf) {
        this.codDevolucionRevistaProf = codDevolucionRevistaProf;
    }

    public void setCodPrestamoRevistaProf(int codPrestamoRevistaProf) {
        this.codPrestamoRevistaProf = codPrestamoRevistaProf;
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
