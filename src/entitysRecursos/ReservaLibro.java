
package entitysRecursos;

import java.sql.Date;

/**
 *
 * @author Camilo
 */
public class ReservaLibro {
    
    private int codReservaColGral;
    private String codBarraLibro;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaReserva;
    private Date fechaLimite;

    public ReservaLibro(String codBarraLibro, String codEstudiante, String idBibliotecario, Date fechaReserva, Date fechaLimite) {
        this.codBarraLibro = codBarraLibro;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaReserva = fechaReserva;
        this.fechaLimite = fechaLimite;
    }

    public int getCodReservaColGral() {
        return codReservaColGral;
    }

    public void setCodReservaColGral(int codReservaColGral) {
        this.codReservaColGral = codReservaColGral;
    }

    public String getCodBarraLibro() {
        return codBarraLibro;
    }

    public void setCodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
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

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    
}
