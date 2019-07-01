package entitys;

/**
 * Entidad Multa_Libro.
 * @author Camilo
 */
public class MultaLibro{
    
    private int codMultaLibro;
    private int codPrestamoLibro;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;

    public MultaLibro(int codPrestamoLibro, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoLibro = codPrestamoLibro;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }
    
 
    public int getCodMultaLibro() {
        return codMultaLibro;
    }

    public void setCodMultaLibro(int codMultaLibro) {
        this.codMultaLibro = codMultaLibro;
    }

    public int getCodPrestamoLibro() {
        return codPrestamoLibro;
    }

    public void setCodPrestamoLibro(int codPrestamoLibro) {
        this.codPrestamoLibro = codPrestamoLibro;
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
