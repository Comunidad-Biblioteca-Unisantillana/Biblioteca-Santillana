package entitysRecursos;

/**
 * Entidad Multa_Periodico.
 * @author Camilo
 */
public class MultaPeriodico{
    
    private int codMultaPeriodico;
    private int codPrestamoPeriodico;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;

    public MultaPeriodico(int codPrestamoPeriodico, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoPeriodico = codPrestamoPeriodico;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }

    
    public int getCodMultaPeriodico() {
        return codMultaPeriodico;
    }

    public void setCodMultaPeriodico(int codMultaPeriodico) {
        this.codMultaPeriodico = codMultaPeriodico;
    }

    public int getCodPrestamoPeriodico() {
        return codPrestamoPeriodico;
    }

    public void setCodPrestamoPeriodico(int codPrestamoPeriodico) {
        this.codPrestamoPeriodico = codPrestamoPeriodico;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public void setDiasAtrasados(int diasAtrasados) {
        this.diasAtrasados = diasAtrasados;
    }

    public int getCodPrecioHistMulta() {
        return codPrecioHistMulta;
    }

    public void setCodPrecioHistMulta(int codPrecioHistMulta) {
        this.codPrecioHistMulta = codPrecioHistMulta;
    }

    public int getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(int valorMulta) {
        this.valorMulta = valorMulta;
    }

    public String getCancelado() {
        return cancelado;
    }

    public void setCancelado(String cancelado) {
        this.cancelado = cancelado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
