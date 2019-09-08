
package moduloReserva.entitys;

import java.util.Date;

/**
 *
 * @author Julian
 * @creado: 
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class ReservaColgenEstudiante {
    
    private String codBarraLibro;
    private String codEstudiante;
    private int codReservaColgenEst;
    private Date fechaLimiteReserva;
    private Date fechaReserva;
    private Date fechaRetencion;
    private String idBibliotecario;

    public ReservaColgenEstudiante(String codBarraLibro, String codEstudiante, String idBibliotecario) {
        this.codBarraLibro = codBarraLibro;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
    }

    public String getCodBarraLibro() {
        return codBarraLibro;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public int getCodReservaColgenEst() {
        return codReservaColgenEst;
    }

    public Date getFechaLimiteReserva() {
        return fechaLimiteReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setCodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public void setCodReservaColgenEst(int codReservaColgenEst) {
        this.codReservaColgenEst = codReservaColgenEst;
    }

    public void setFechaLimiteReserva(Date fechaLimiteReserva) {
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }
    
    
    
    
}
