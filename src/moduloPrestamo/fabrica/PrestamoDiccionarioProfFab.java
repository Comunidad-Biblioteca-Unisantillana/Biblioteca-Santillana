package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoDiccionarioProf;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOProf;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Profesor;

/**
 * La clase se encarga gestionar el préstamo del diccionario al profesor.
 *
 * @author Julian
 * @creado
 * @author Miguel Fernández
 * @modificado 24/08/2019
 */
public class PrestamoDiccionarioProfFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioProfFab() {

    }

    /**
     * el método realiza el préstamo del dicionario al profesor.
     *
     * @param codBarras
     * @param codUsuario
     * @param idBibliotecario
     * @return boolean
     */
    @Override
    public boolean ejecutarPrestamo(String codBarras, String codUsuario, String idBibliotecario) {
        IAlertBox alert = new AlertBox();

        try {
            DiccionarioJpaController controlDic = new DiccionarioJpaController();
            Diccionario dicccionario = controlDic.findDiccionario(codBarras);

            if (dicccionario != null) {
                if (dicccionario.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoDiccionarioProf prestDicProf = new PrestamoDiccionarioProf();
                    prestDicProf.setCodBarraDiccionario(codBarras);
                    prestDicProf.setIdProfesor(codUsuario);
                    prestDicProf.setIdBibliotecario(idBibliotecario);

                    PrestamoDiccionarioDAOProf prestDicDAOProf = new PrestamoDiccionarioDAOProf();

                    if (prestDicDAOProf.createDAO(prestDicProf)) {
                        dicccionario.setDisponibilidad("prestado");
                        controlDic.edit(dicccionario);
                        notificarPrestamoEmail(codUsuario, dicccionario);

                        return true;
                    }

                } else if (dicccionario.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El diccionario: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (dicccionario.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El dicionario: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo diccionario", "No se encuentró un diccionario "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del diccionario a un profesor");
        }

        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole del préstamo del
     * dicionario.
     *
     * @param idProfesor
     * @param diccionario
     */
    private void notificarPrestamoEmail(String idProfesor, Diccionario diccionario) {
        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(idProfesor);

        PrestamoDiccionarioDAOProf prestDicDAOProf = new PrestamoDiccionarioDAOProf();
        PrestamoDiccionarioProf prestDicProf = prestDicDAOProf.readDAO(prestDicDAOProf.readCodigoDAO(diccionario.getCodbarradiccionario()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + idProfesor + ";"
                + diccionario.getTitulo() + ";"
                + diccionario.getCodbarradiccionario() + ";"
                + formatoFecha.format((Date) prestDicProf.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestDicProf.getFechaDevolucion()) + ";"
                + "referencia;"
                + profesor.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }

}
