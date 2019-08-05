package entitysRecursos;


/**
 * Entidad Multa_Enciclopedia.
 * @author Camilo
 */
public class MultaEnciclopedia {
    
    private int codMultaEnciclopedia;
    private int codPrestamoEnciclopedia;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;
    
    public MultaEnciclopedia(int codPrestamoEnciclopedia, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoEnciclopedia = codPrestamoEnciclopedia;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }

    public int getCodMultaEnciclopedia() {
        return codMultaEnciclopedia;
    }

    public void setCodMultaEnciclopedia(int codMultaEnciclopedia) {
        this.codMultaEnciclopedia = codMultaEnciclopedia;
    }

    public int getCodPrestamoEnciclopedia() {
        return codPrestamoEnciclopedia;
    }

    public void setCodPrestamoEnciclopedia(int codPrestamoEnciclopedia) {
        this.codPrestamoEnciclopedia = codPrestamoEnciclopedia;
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
