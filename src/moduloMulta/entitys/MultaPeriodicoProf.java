package moduloMulta.entitys;

import java.sql.Date;

/**
 * la clase representa la entidad multa periódico profesor.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 30/08/2019
 */
public class MultaPeriodicoProf {

    private int codMultaPeriodicoProf;
    private int codPrestamoPeriodicoProf;
    private int diasAtrasados;
    private int codPrecioMulta;
    private int valorMulta;
    private String estadoCancelacion;
    private String descripcionCancelacion;
    private Date fechaMulta;

    /**
     * constructor de la clase sin parámetros.
     */
    public MultaPeriodicoProf() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codPrestamoPeriodicoProf
     * @param diasAtrasados
     * @param codPrecioMulta
     * @param valorMulta
     * @param estadoCancelacion
     * @param descripcionCancelacion
     * @param fechaMulta
     */
    public MultaPeriodicoProf(int codPrestamoPeriodicoProf, int diasAtrasados, int codPrecioMulta, int valorMulta, String estadoCancelacion, String descripcionCancelacion, Date fechaMulta) {
        this.codPrestamoPeriodicoProf = codPrestamoPeriodicoProf;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioMulta = codPrecioMulta;
        this.valorMulta = valorMulta;
        this.estadoCancelacion = estadoCancelacion;
        this.descripcionCancelacion = descripcionCancelacion;
        this.fechaMulta = fechaMulta;
    }

    public int getCodMultaPeriodicoProf() {
        return codMultaPeriodicoProf;
    }

    public void setCodMultaPeriodicoProf(int codMultaPeriodicoProf) {
        this.codMultaPeriodicoProf = codMultaPeriodicoProf;
    }

    public int getCodPrestamoPeriodicoProf() {
        return codPrestamoPeriodicoProf;
    }

    public void setCodPrestamoPeriodicoProf(int codPrestamoPeriodicoProf) {
        this.codPrestamoPeriodicoProf = codPrestamoPeriodicoProf;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public void setDiasAtrasados(int diasAtrasados) {
        this.diasAtrasados = diasAtrasados;
    }

    public int getCodPrecioMulta() {
        return codPrecioMulta;
    }

    public void setCodPrecioMulta(int codPrecioMulta) {
        this.codPrecioMulta = codPrecioMulta;
    }

    public int getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getEstadoCancelacion() {
        return estadoCancelacion;
    }

    public void setEstadoCancelacion(String estadoCancelacion) {
        this.estadoCancelacion = estadoCancelacion;
    }

    public String getDescripcionCancelacion() {
        return descripcionCancelacion;
    }

    public void setDescripcionCancelacion(String descripcionCancelacion) {
        this.descripcionCancelacion = descripcionCancelacion;
    }

    public Date getFechaMulta() {
        return fechaMulta;
    }

    public void setFechaMulta(Date fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

}
