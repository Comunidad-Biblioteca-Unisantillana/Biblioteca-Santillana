package entitysRecursos;

import java.util.Date;

/**
 * Entidad Devolucion_Periodico.
 * @author Camilo
 */
public class DevolucionPeriodico {
    
    private int codDevolucionPeriodico;
    private int codPrestamoPeriodico;
    private String idBibliotecario;
    private Date fechaDevolucion;
    private String estadoDevolucion;

    public DevolucionPeriodico(int codPrestamoPeriodico, String idBibliotecario, Date fechaDevolucion, String estadoDevolucion) {
        this.codPrestamoPeriodico = codPrestamoPeriodico;
        this.idBibliotecario = idBibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estadoDevolucion = estadoDevolucion;
    }

    public int getCodDevolucionPeriodico() {
        return codDevolucionPeriodico;
    }

    public void setCodDevolucionPeriodico(int codDevolucionPeriodico) {
        this.codDevolucionPeriodico = codDevolucionPeriodico;
    }

    public int getCodPrestamoPeriodico() {
        return codPrestamoPeriodico;
    }

    public void setCodPrestamoPeriodico(int codPrestamoPeriodico) {
        this.codPrestamoPeriodico = codPrestamoPeriodico;
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
