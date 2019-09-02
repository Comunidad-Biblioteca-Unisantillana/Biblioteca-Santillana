package moduloMulta.entitys;

/**
 * Entidad Multa_Revista.
 * @author Camilo
 */
public class MultaRevista{
    
    private int codMultaRevista;
    private int codPrestamoRevista;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;

    public MultaRevista(int codPrestamoRevista, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoRevista = codPrestamoRevista;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }

    
    public int getCodMultaRevista() {
        return codMultaRevista;
    }

    public void setCodMultaRevista(int codMultaRevista) {
        this.codMultaRevista = codMultaRevista;
    }

    public int getCodPrestamoRevista() {
        return codPrestamoRevista;
    }

    public void setCodPrestamoRevista(int codPrestamoRevista) {
        this.codPrestamoRevista = codPrestamoRevista;
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
