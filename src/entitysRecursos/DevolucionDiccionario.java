package entitysRecursos;

import java.util.Date;

/**
 * Entidad DevolucionDiccionario.
 * @author Camilo
 */
public class DevolucionDiccionario {
    
    private int codDevolucionDiccionario;
    private int codPrestamoDiccionario;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionDiccionario(int codPrestamoDiccionario, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoDiccionario = codPrestamoDiccionario;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionDiccionario() {
        return codDevolucionDiccionario;
    }

    public void setCodDevolucionDiccionario(int codDevolucionDiccionario) {
        this.codDevolucionDiccionario = codDevolucionDiccionario;
    }

    public int getCodPrestamoDiccionario() {
        return codPrestamoDiccionario;
    }

    public void setCodPrestamoDiccionario(int codPrestamoDiccionario) {
        this.codPrestamoDiccionario = codPrestamoDiccionario;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoDevolucion() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(String estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }
    
    
}
