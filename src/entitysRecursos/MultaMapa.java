package entitys;

/**
 * Entidad Multa_Mapa.
 * @author Camilo
 */
public class MultaMapa{
    
    private int codMultaMapa;
    private int codPrestamoMapa;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;

    public MultaMapa(int codPrestamoMapa, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoMapa = codPrestamoMapa;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }

    public int getCodMultaMapa() {
        return codMultaMapa;
    }

    public void setCodMultaMapa(int codMultaMapa) {
        this.codMultaMapa = codMultaMapa;
    }

    public int getCodPrestamoMapa() {
        return codPrestamoMapa;
    }

    public void setCodPrestamoMapa(int codPrestamoMapa) {
        this.codPrestamoMapa = codPrestamoMapa;
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
