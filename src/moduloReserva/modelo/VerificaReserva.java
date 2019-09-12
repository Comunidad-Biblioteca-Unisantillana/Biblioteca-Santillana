package moduloReserva.modelo;

import general.modelo.NotificacionEmail;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.DAO.ReservaRecursoDAOAbs;
import moduloReserva.entitys.ReservaColgenEstudiante;
import moduloReserva.entitys.ReservaColgenProfesor;
import recursos.entitys.Libro;
import usuarios.control.EstudianteJpaController;
import usuarios.control.ProfesorJpaController;
import usuarios.entitys.Estudiante;
import usuarios.entitys.Profesor;

/**
 * la clase se encarga de verificar las reservas, liberarlas y notificar al
 * usuario de la reserva generada.
 *
 * @author Miguel Fernàndez
 * @creado: 30/08/2019
 * @author Miguel Fernàndez
 * @modificado: 07/09/2019
 */
public class VerificaReserva {

    private ReservaRecursoDAOAbs reservaRecurso;

    /**
     * constructor de la clase sin paràmetros.
     */
    public VerificaReserva() {
        reservaRecurso = null;
    }

    /**
     * el mètodo se encarga de eliminar la reserva de un recurso solicitado por
     * el esudiante, cuando se lleva a cabo el prèstamo del recurso.
     *
     * @param codEstudiante
     * @param codBarraRecurso
     * @return boolean
     */
    public boolean liberarReservaAPrestamoEst(String codEstudiante, String codBarraRecurso) {
        ReservaColgenDAOEst reservaColgenDAOEst = new ReservaColgenDAOEst();

        return reservaColgenDAOEst.deleteDAO(codBarraRecurso, codEstudiante);
    }

    /**
     * el mètodo se encarga de eliminar la reserva de un recurso solicitado por
     * el profesor, cuando se lleva a cabo el prèstamo del recurso.
     *
     * @param idProfesor
     * @param codBarraRecurso
     * @return boolean
     */
    public boolean liberarReservaAPrestamoProf(String idProfesor, String codBarraRecurso) {
        ReservaColgenDAOProf reservaColgenDAOProf = new ReservaColgenDAOProf();

        return reservaColgenDAOProf.deleteDAO(codBarraRecurso, idProfesor);
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la retención del
     * libro.
     *
     * @param libro
     */
    public void notificarRetencionEmailEst(Libro libro) {
        ReservaColgenDAOEst reservaColgenDAOEst = new ReservaColgenDAOEst();
        ReservaColgenEstudiante reservaColgenEst = reservaColgenDAOEst.readDAO(libro.getCodbarralibro());

        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(reservaColgenEst.getCodEstudiante());

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + estudiante.getCodestudiante() + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) reservaColgenEst.getFechaReserva()) + ";"
                + formatoFecha.format((Date) reservaColgenEst.getFechaRetencion()) + ";"
                + formatoFecha.format((Date) reservaColgenEst.getFechaLimiteReserva()) + ";"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeRetencion");
        notificacionEmail.start();
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al profesor, notificandole de la retención del
     * libro.
     *
     * @param libro
     */
    public void notificarRetencionEmailProf(Libro libro) {
        ReservaColgenDAOProf reservaColgenDAOProf = new ReservaColgenDAOProf();
        ReservaColgenProfesor reservaColgenProf = reservaColgenDAOProf.readDAO(libro.getCodbarralibro());

        ProfesorJpaController profesorJpaController = new ProfesorJpaController();
        Profesor profesor = profesorJpaController.findProfesor(reservaColgenProf.getIdProfesor());

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = profesor.getApellidos().toUpperCase() + ";"
                + profesor.getNombres().toUpperCase() + ";"
                + "Identificación: " + profesor.getIdprofesor() + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) reservaColgenProf.getFechaReserva()) + ";"
                + formatoFecha.format((Date) reservaColgenProf.getFechaRetencion()) + ";"
                + formatoFecha.format((Date) reservaColgenProf.getFechaLimiteReserva()) + ";"
                + profesor.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeRetencion");
        notificacionEmail.start();
    }

    /**
     * el método verifica, si el estudiante ha realizado la reserva de un libro.
     *
     * @param codBarras
     * @return boolean
     */
    public boolean verificarReservaEst(String codBarras) {
        reservaRecurso = new ReservaColgenDAOEst();
        return reservaRecurso.readDAO(codBarras) != null;
    }

    /**
     * el método verifica, si el profesor ha realizado la reserva de un libro.
     *
     * @param codBarras
     * @return boolean
     */
    public boolean verificarReservaProf(String codBarras) {
        reservaRecurso = new ReservaColgenDAOProf();
        return reservaRecurso.readDAO(codBarras) != null;
    }

}
