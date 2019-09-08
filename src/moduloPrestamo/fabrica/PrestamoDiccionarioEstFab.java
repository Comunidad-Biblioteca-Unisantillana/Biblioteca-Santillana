package moduloPrestamo.fabrica;

import general.modelo.NotificacionEmail;
import moduloPrestamo.modelo.IPrestamo;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;
import recursos.controllers.DiccionarioJpaController;
import recursos.entitys.Diccionario;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * La clase se encarga gestionar el préstamo del diccionario de un estudiante.
 *
 * @author Julian
 * @creado:
 * @author Miguel Fernández
 * @modificado: 07/09/2019
 */
public class PrestamoDiccionarioEstFab implements IPrestamo {

    /**
     * constructor de la clase sin parámetros.
     */
    public PrestamoDiccionarioEstFab() {

    }

    /**
     * el método realiza el préstamo del dicionario al estudiante.
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
            Diccionario diccionario = controlDic.findDiccionario(codBarras);

            if (diccionario != null) {
                if (diccionario.getDisponibilidad().equalsIgnoreCase("disponible")) {
                    PrestamoDiccionarioEst prestDicEst = new PrestamoDiccionarioEst();
                    prestDicEst.setCodBarraDiccionario(codBarras);
                    prestDicEst.setCodEstudiante(codUsuario);
                    prestDicEst.setIdBibliotecario(idBibliotecario);

                    PrestamoDiccionarioDAOEst prestDicDAOEst = new PrestamoDiccionarioDAOEst();

                    if (prestDicDAOEst.createDAO(prestDicEst)) {
                        diccionario.setDisponibilidad("prestado");
                        controlDic.edit(diccionario);
                        notificarPrestamoEmail(codUsuario, diccionario);
                        
                        return true;
                    }
                } else if (diccionario.getDisponibilidad().equalsIgnoreCase("prestado")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El diccionario: " + codBarras
                            + ", se encuentra préstado a otro usuario.");
                } else if (diccionario.getDisponibilidad().equalsIgnoreCase("vencido")) {
                    alert.showAlert("Anuncio", "Préstamo diccionario", "El dicionario: " + codBarras
                            + ", no ha sido devuelto por el usuario al que se le presto.");
                }
            } else {
                alert.showAlert("Anuncio", "Préstamo diccionario", "No se encuentró un diccionario "
                        + "asociado al código: " + codBarras + ".");
            }
        } catch (Exception e) {
            System.out.println("Error al generar el préstamo del diccionario a un estudiante");
        }

        return false;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole del préstamo del
     * dicionario.
     *
     * @param codEstudiante
     * @param diccionario
     */
    private void notificarPrestamoEmail(String codEstudiante, Diccionario diccionario) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        PrestamoDiccionarioDAOEst prestDicDAOEst = new PrestamoDiccionarioDAOEst();
        PrestamoDiccionarioEst prestDicEst = prestDicDAOEst.readDAO(prestDicDAOEst.readCodigoDAO(diccionario.getCodbarradiccionario()));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + diccionario.getTitulo() + ";"
                + diccionario.getCodbarradiccionario() + ";"
                + formatoFecha.format((Date) prestDicEst.getFechaPrestamo()) + ";"
                + formatoFecha.format((Date) prestDicEst.getFechaDevolucion()) + ";"
                + "referencia;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail em = new NotificacionEmail();
        em.gestionarNotificacion(datos, "mensajePrestamo");
    }

}
