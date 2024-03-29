/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloReserva.entitys;

import java.util.Date;

/**
 *
 * @author Storkolm
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class ReservaColgenProfesor {
    
    private String codBarraLibro;
    private int codReservaColgenProf;
    private Date fechaLimiteReserva;
    private Date fechaReserva;
    private Date fechaRetencion;
    private String idBibliotecario;
    private String idProfesor;

    public ReservaColgenProfesor(String codBarraLibro, String idBibliotecario, String idProfesor) {
        this.codBarraLibro = codBarraLibro;
        this.idBibliotecario = idBibliotecario;
        this.idProfesor = idProfesor;
    }

    public String getCodBarraLibro() {
        return codBarraLibro;
    }

    public int getCodReservaColgenProf() {
        return codReservaColgenProf;
    }

    public Date getFechaLimiteReserva() {
        return fechaLimiteReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public String getIdProfesor() {
        return idProfesor;
    }

    public void setCodBarraLibro(String codBarraLibro) {
        this.codBarraLibro = codBarraLibro;
    }

    public void setCodReservaColgenProf(int codReservaColgenProf) {
        this.codReservaColgenProf = codReservaColgenProf;
    }

    public void setFechaLimiteReserva(Date fechaLimiteReserva) {
        this.fechaLimiteReserva = fechaLimiteReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    
    
}
