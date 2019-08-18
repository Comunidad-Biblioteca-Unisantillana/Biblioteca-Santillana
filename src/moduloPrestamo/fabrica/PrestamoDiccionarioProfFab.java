package moduloPrestamo.fabrica;

import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import controllers.DiccionarioJpaController;
import entitys.Diccionario;
import java.sql.Date;
import modelo.QueryRecurso;
import modelo.ServicioFecha;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import moduloPrestamo.IPrestamo;

/**
 *
 * @author Julian
 */
public class PrestamoDiccionarioProfFab implements IPrestamo {

    public PrestamoDiccionarioProfFab() {

    }

    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        try {
            Diccionario dicccionario = QueryRecurso.consultarDiccionario(codBarras);
            if (dicccionario != null) {
                if (dicccionario.getDisponibilidad().equalsIgnoreCase("disponible")) {

                    java.util.Date fechaActual = new java.util.Date();
                    java.util.Date fechaDevolucion = ServicioFecha.sumarDiasAFecha(fechaActual, 1);

                    PrestamoDiccionarioProf prestDicProf = new PrestamoDiccionarioProf(codBarras, codUsuario,
                            idBibliotecario, new Date(fechaActual.getTime()), new Date(fechaDevolucion.getTime()));
                    PrestamoDiccionarioDAOProf prestDicDAOProf = new PrestamoDiccionarioDAOProf();

                    if (prestDicDAOProf.createDAO(prestDicProf)) {
                        System.out.println("Cambiando disponibilidad del diccionario ...");
                        DiccionarioJpaController controlDic = new DiccionarioJpaController();
                        dicccionario.setDisponibilidad("prestado");
                        controlDic.edit(dicccionario);
                        return true;
                    }

                } else {
                    System.out.println("el diccionario no se encuentra disponible");
                }
            } else {
                System.out.println("Ningun diccionario tiene este codigo de barra");
            }
        } catch (Exception e) {
            System.out.println("error al generar el prestamo de diccionario de un profesor");
        }
        return false;
    }

    public void notificarPrestamoEmail(String idProfesor, String tituloRecurso, java.util.Date fechaPrestamo, java.util.Date fechaDevolucion) {

    }
}
