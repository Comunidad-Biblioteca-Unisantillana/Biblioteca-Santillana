package entitys;

import java.sql.Date;

/**
 * Entidad Prestamo_Diccionario.
 * @author Camilo
 */
public class PrestamoDiccionario{
    private int codPrestamoDiccionario;
    private String codBarraDiccionario;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public PrestamoDiccionario(String codBarraDiccionario, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraDiccionario = codBarraDiccionario;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoDiccionario() {
        return codPrestamoDiccionario;
    }

    public void setCodPrestamoDiccionario(int codPrestamoDiccionario) {
        this.codPrestamoDiccionario = codPrestamoDiccionario;
    }

    public String getCodBarraDiccionario() {
        return codBarraDiccionario;
    }

    public void setCodBarraDiccionario(String codBarraDiccionario) {
        this.codBarraDiccionario = codBarraDiccionario;
    }

    public String getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
    
}