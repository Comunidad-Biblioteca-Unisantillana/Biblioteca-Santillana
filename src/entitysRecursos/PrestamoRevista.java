package entitysRecursos;

import java.sql.Date;

/**
 * Entidad Prestamo_Revista.
 * @author Camilo
 */
public class PrestamoRevista {
    
    private int codPrestamoRevista;
    private String codBarraRevista;
    private String codEstudiante;
    private String idBibliotecario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;

    public PrestamoRevista(String codBarraRevista, String codEstudiante, String idBibliotecario, Date fechaPrestamo, Date fechaDevolucion) {
        this.codBarraRevista = codBarraRevista;
        this.codEstudiante = codEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCodPrestamoRevista() {
        return codPrestamoRevista;
    }

    public void setCodPrestamoRevista(int codPrestamoRevista) {
        this.codPrestamoRevista = codPrestamoRevista;
    }

    public String getCodBarraRevista() {
        return codBarraRevista;
    }

    public void setCodBarraRevista(String codBarraRevista) {
        this.codBarraRevista = codBarraRevista;
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
