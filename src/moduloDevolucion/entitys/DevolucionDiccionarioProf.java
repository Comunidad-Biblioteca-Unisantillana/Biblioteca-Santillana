package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:22 a. m.
 */
public class DevolucionDiccionarioProf {

    private int codDevolucionDiccionarioProf;
    private int codPrestamoDiccionarioProf;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionDiccionarioProf(int codPrestamoDiccionarioProf, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoDiccionarioProf = codPrestamoDiccionarioProf;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionDiccionarioProf() {
        return codDevolucionDiccionarioProf;
    }

    public int getCodPrestamoDiccionarioProf() {
        return codPrestamoDiccionarioProf;
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

    public void setCodDevolucionDiccionarioProf(int codDevolucionDiccionarioProf) {
        this.codDevolucionDiccionarioProf = codDevolucionDiccionarioProf;
    }

    public void setCodPrestamoDiccionarioProf(int codPrestamoDiccionarioProf) {
        this.codPrestamoDiccionarioProf = codPrestamoDiccionarioProf;
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
