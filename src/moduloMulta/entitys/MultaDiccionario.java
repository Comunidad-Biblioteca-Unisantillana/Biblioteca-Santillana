package moduloMulta.entitys;

/**
 * Entidad Multa_Diccionario.
 * @author Camilo
 */
public class MultaDiccionario{
    
    private int codMultaDiccionario;
    private int codPrestamoDiccionario;
    private int diasAtrasados;
    private int codPrecioHistMulta;
    private int valorMulta;
    private String cancelado;
    private String tipo;

    public MultaDiccionario(int codPrestamoDiccionario, int diasAtrasados, int codPrecioHistMulta, int valorMulta, String cancelado, String tipo) {
        this.codPrestamoDiccionario = codPrestamoDiccionario;
        this.diasAtrasados = diasAtrasados;
        this.codPrecioHistMulta = codPrecioHistMulta;
        this.valorMulta = valorMulta;
        this.cancelado = cancelado;
        this.tipo = tipo;
    }

    public int getCodMultaDiccionario() {
        return codMultaDiccionario;
    }

    public void setCodMultaDiccionario(int codMultaDiccionario) {
        this.codMultaDiccionario = codMultaDiccionario;
    }

    public int getCodPrestamoDiccionario() {
        return codPrestamoDiccionario;
    }

    public void setCodPrestamoDiccionario(int codPrestamoDiccionario) {
        this.codPrestamoDiccionario = codPrestamoDiccionario;
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
