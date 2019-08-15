package moduloPrestamo;

import controllers.DiccionarioJpaController;
import entitys.Diccionario;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamoDAO.PrestamoDiccionarioDAOEst;

/**
 * Clase que genera un prestamo de diccionario a un estudiante
 * @author Julian
 */
public class PrestamoDiccionarioEstFab implements IPrestamo {

    public PrestamoDiccionarioEstFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarra, String codUsuario, String idBibliotecario) {
        try {
            Diccionario dicccionario = QueryRecurso.consultarDiccionario(codBarra);
            if (dicccionario != null) {
                if (dicccionario.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    
                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 0);
                    
                    PrestamoDiccionarioEst prestDicEst = new PrestamoDiccionarioEst(codBarra, codUsuario,
                            idBibliotecario, new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoDiccionarioDAOEst prestDicDAOEst = new PrestamoDiccionarioDAOEst();
                    prestDicDAOEst.createDAO(prestDicEst);
                    
                    DiccionarioJpaController controlDic = new DiccionarioJpaController();
                    dicccionario.setDisponibilidad("prestado");
                    controlDic.edit(dicccionario);
                    return true;
                } else {
                    System.out.println("el diccionario no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun diccionario tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de diccionario de un estudiante");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}
