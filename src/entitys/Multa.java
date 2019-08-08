
package entitys;

/**
 *
 * @author Camilo
 */
public class Multa {
    private int codMulta;
    private int codPrestamo;
    private int diasAtrasados;
    private int valorTotal;
    private String cancelado;
    private String tipo;

    public Multa(int codMulta, int codPrestamos, int diasAtrasados, int valorTotal, String cancelado, String tipo) {
        this.codMulta = codMulta;
        this.codPrestamo = codPrestamos;
        this.diasAtrasados = diasAtrasados;
        this.valorTotal = valorTotal;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }
    
    public int getCodMulta() {
        return codMulta;
    }

    public void setCodMulta(int codMulta) {
        this.codMulta = codMulta;
    }

    public int getCodPrestamo() {
        return codPrestamo;
    }

    public void setCodPrestamos(int codPrestamos) {
        this.codPrestamo = codPrestamos;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public void setDiasAtrasados(int diasAtrasados) {
        this.diasAtrasados = diasAtrasados;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
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
