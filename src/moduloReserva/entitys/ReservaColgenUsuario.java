package moduloReserva.entitys;

import java.util.Date;

/**
 * la clase representa las tablas de resserva de profesor y estudiante.
 *
 * @author Miguel Fernàndez
 * @creado: 08/09/2019
 * @author Miguel Fernàndez
 * @modificado: 08/09/2019
 */
public class ReservaColgenUsuario {

    private int codReservaColGen;
    private String codBarraLibro;
    private String codUsuario;
    private String idBibliotecario;
    private Date fechaReserva;
    private Date fechaRetencion;
    private Date fechaLimiteReserva;

    /**
     * constructor de la clase sin paràmtros.
     */
    public ReservaColgenUsuario() {

    }

    /**
     * constructor de la clase con paràmtros.
     *
     * @param codReservaColGen
     * @param codBarraLibro
     * @param codUsuario
     * @param idBibliotecario
     * @param fechaReserva
     * @param fechaRetencion
     * @param fechaLimiteReserva
     */
    public ReservaColgenUsuario(int codReservaColGen, String codBarraLibro, String codUsuario, String idBibliotecario, Date fechaReserva, Date fechaRetencion, Date fechaLimiteReserva) {
        this.codReservaColGen = codReservaColGen;
        this.codBarraLibro = codBarraLibro;
        this.codUsuario = codUsuario;
        this.idBibliotecario = idBibliotecario;
        this.fechaReserva = fechaReserva;
        this.fechaRetencion = fechaRetencion;
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

    public int getCodReservaColGen() {
        return codReservaColGen;
    }

    public void setCodReservaColGen(int codReservaColGen) {
        this.codReservaColGen = codReservaColGen;
    }

    public String getcodBarraLibro() {
        return codBarraLibro;
    }

    public void setcodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
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

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public Date getFechaLimiteReserva() {
        return fechaLimiteReserva;
    }

    public void setFechaLimiteReserva(Date fechaLimiteReserva) {
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

}
