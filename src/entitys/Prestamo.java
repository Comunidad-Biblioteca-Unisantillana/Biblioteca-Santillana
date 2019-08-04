package entitys;

import java.sql.Date;

/**
 * Entidad Prestamo_Diccionario.
 * @author Camilo
 */
public class Prestamo{
    private int codPrestamo;
    private String titulo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public Prestamo(int codPrestamo, String titulo, Date fechaPrestamo, Date fechaDevolucion) {
        this.codPrestamo = codPrestamo;
        this.titulo = titulo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamo() {
        return codPrestamo;
    }

    public void setCodPrestamo(int codPrestamo) {
        this.codPrestamo = codPrestamo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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