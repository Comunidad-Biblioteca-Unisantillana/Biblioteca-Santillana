package entitysRecursos;

import java.sql.Date;

/**
 * Entidad Prestamo_Periodico.
 * @author Camilo
 */
public class PrestamoPeriodico {
    
    private int codPrestamoPeriodico;
    private String codBarraPeriodico;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public PrestamoPeriodico(String codBarraPeriodico, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraPeriodico = codBarraPeriodico;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoPeriodico() {
        return codPrestamoPeriodico;
    }

    public void setCodPrestamoPeriodico(int codPrestamoPeriodico) {
        this.codPrestamoPeriodico = codPrestamoPeriodico;
    }

    public String getCodBarraPeriodico() {
        return codBarraPeriodico;
    }

    public void setCodBarraPeriodico(String codBarraPeriodico) {
        this.codBarraPeriodico = codBarraPeriodico;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
}
