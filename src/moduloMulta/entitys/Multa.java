
package moduloMulta.entitys;

import java.sql.Date;

/**
 * clase multa
 * @author Camilo
 * @author Julian
 * @creado 31/08/2019
 * @modificado 31/08/2019
 */
public class Multa {
    private String candelado;
    private String codBarrasRecurso;
    private int codMulta;
    private int codPrestamo;
    private int diasAtrasados;
    private Date fechaMulta;
    private String idUsuario;
    private String nombreUsuario;
    private String tipoRecurso;
    private String tituloRecurso;
    private int valorTotal;

    public Multa(String candelado, int codMulta, int diasAtrasados, String tipoRecurso, int valorTotal) {
        this.candelado = candelado;
        this.codMulta = codMulta;
        this.diasAtrasados = diasAtrasados;
        this.tipoRecurso = tipoRecurso;
        this.valorTotal = valorTotal;
    }

    public String getCandelado() {
        return candelado;
    }

    public String getCodBarrasRecurso() {
        return codBarrasRecurso;
    }

    public int getCodMulta() {
        return codMulta;
    }

    public int getCodPrestamo() {
        return codPrestamo;
    }

    public int getDiasAtrasados() {
        return diasAtrasados;
    }

    public Date getFechaMulta() {
        return fechaMulta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public String getTituloRecurso() {
        return tituloRecurso;
    }

    public int getValorTotal() {
        return valorTotal;
    }

    public void setCandelado(String candelado) {
        this.candelado = candelado;
    }

    public void setCodBarrasRecurso(String codBarrasRecurso) {
        this.codBarrasRecurso = codBarrasRecurso;
    }

    public void setCodMulta(int codMulta) {
        this.codMulta = codMulta;
    }

    public void setCodPrestamo(int codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public void setDiasAtrasados(int diasAtrasados) {
        this.diasAtrasados = diasAtrasados;
    }

    public void setFechaMulta(Date fechaMulta) {
        this.fechaMulta = fechaMulta;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public void setTituloRecurso(String tituloRecurso) {
        this.tituloRecurso = tituloRecurso;
    }

    public void setValorTotal(int valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}
