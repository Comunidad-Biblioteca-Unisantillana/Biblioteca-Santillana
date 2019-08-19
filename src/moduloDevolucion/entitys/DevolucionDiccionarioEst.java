package moduloDevolucion.entitys;

import java.sql.Date;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:19 a. m.
 */
public class DevolucionDiccionarioEst {

    private int codDevolucionDiccionarioEst;
    private int codPrestamoDiccionarioEst;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionDiccionarioEst(int codPrestamoDiccionarioEst, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoDiccionarioEst = codPrestamoDiccionarioEst;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }
    
    

    public int getCodDevolucionDiccionarioEst() {
        return codDevolucionDiccionarioEst;
    }

    public int getCodPrestamoDiccionarioEst() {
        return codPrestamoDiccionarioEst;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setCodDevolucionDiccionarioEst(int codDevolucionDiccionarioEst) {
        this.codDevolucionDiccionarioEst = codDevolucionDiccionarioEst;
    }

    public void setCodPrestamoDiccionarioEst(int codPrestamoDiccionarioEst) {
        this.codPrestamoDiccionarioEst = codPrestamoDiccionarioEst;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }
}
