package moduloMulta.entitys;

import java.sql.Date;

/**
 * la clase representa la entidad multa libro estudiante.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 30/08/2019
 */
public class MultaLibroEst {

    private int codMultaLibroEst;
    private int codPrestamoLibroEst;
    private int diasAtrasados;
    private int codPrecioMulta;
    private int valorMulta;
    private String estadoCancelacion;
    private String descripcionCancelacion;
    private Date fechaMulta;

    /**
     * constructor de la clase sin parámetros.
     */
    public MultaLibroEst() {

    }

    /**
     * constructor de la clase con parámetros.
     *
     * @param codPrestamoLibroEst
     * @param diasAtrasados
     * @param codPrecioMulta
     * @param valorMulta
     * @param estadoCancelacion
     * @param descripcionCancelacion
     * @param fechaMulta
     */
    public MultaLibroEst(int codPrestamoLibroEst, int diasAtrasados, int codPrecioMulta, int valorMulta,
            String estadoCancelacion, String descripcionCancelacion, Date fechaMulta) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioMulta = codPrecioMulta;
        this.valorMulta = valorMulta;
        this.estadoCancelacion = estadoCancelacion;
        this.descripcionCancelacion = descripcionCancelacion;
        this.fechaMulta = fechaMulta;
    }

    public int getCodMultaLibroEst() {
        return codMultaLibroEst;
    }

    public void setCodMultaLibroEst(int codMultaLibroEst) {
        this.codMultaLibroEst = codMultaLibroEst;
    }

    public int getCodPrestamoLibroEst() {
        return codPrestamoLibroEst;
    }

    public void setCodPrestamoLibroEst(int codPrestamoLibroEst) {
        this.codPrestamoLibroEst = codPrestamoLibroEst;
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
