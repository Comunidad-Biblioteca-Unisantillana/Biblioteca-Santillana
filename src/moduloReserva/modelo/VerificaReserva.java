package moduloReserva.modelo;

import java.sql.Date;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.DAO.ReservaRecursoDAOAbs;

/**
 * la clase se encarga de verificar las reservas, liberarlas y notificar al
 * usuario de la reserva generada.
 *
 * @author Miguel Fernàndez
 * @creado: 30/08/2019
 * @author Miguel Fernàndez
 * @modificado: 30/08/2019
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
        return false;
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
        return false;
    }

    /**
     * el mètodo realiza la construcciòn del correo que se le enviarà al
     * estudiante, notificandole de la retenciòn del recurso solicitado.
     *
     * @param codEstudiante
     * @param tituloRecurso
     * @param fechaReserva
     * @param fechaRetencion
     * @param fechaLimiteReserva
     */
    public void notificarRetencionEmailEst(String codEstudiante, String tituloRecurso, Date fechaReserva, Date fechaRetencion, Date fechaLimiteReserva) {

    }

    /**
     * el mètodo realiza la construcciòn del correo que se le enviarà al
     * profesor, notificandole de la retenciòn del recurso solicitado.
     *
     * @param idProfesor
     * @param tituloRecurso
     * @param fechaReserva
     * @param fechaRetencion
     * @param fechaLimiteReserva
     */
    public void notificarRetencionEmailProf(String idProfesor, String tituloRecurso, Date fechaReserva, Date fechaRetencion, Date fechaLimiteReserva) {

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
