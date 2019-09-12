package moduloDevolucion.fabrica;

import controller.exceptions.NonexistentEntityException;
import general.modelo.NotificacionEmail;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionLibroDAOEst;
import moduloDevolucion.modelo.IDevolucion;
import moduloDevolucion.entitys.DevolucionLibroEst;
import moduloPrestamo.DAO.PrestamoLibroDAOEst;
import moduloPrestamo.entitys.PrestamoLibroEst;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.entitys.ReservaColgenEstudiante;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;
import general.vista.AlertBox;
import general.vista.IAlertBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.DAO.ReservaRecursoDAOAbs;
import moduloReserva.entitys.ReservaColgenProfesor;
import moduloReserva.modelo.VerificaReserva;
import usuarios.control.EstudianteJpaController;
import usuarios.entitys.Estudiante;

/**
 * @author Camilo Jaramillo
 * @creado: 04/08/2019
 * @author Miguel Fernández
 * @modificado: 08/09/2019
 */
public class DevolucionLibroEstFab implements IDevolucion {

    public DevolucionLibroEstFab() {

    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        IAlertBox alert = new AlertBox();
        try {
            LibroJpaController control = new LibroJpaController();
            Libro libro = control.findLibro(codBarras);
            if (libro != null) {
                int codPrestamo = consultarPrestamoLibro(codBarras);
                if (codPrestamo > 0) {

                    PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
                    PrestamoLibroEst prestEst = prestDAOEst.readDAO(codPrestamo);
                    if (prestEst.getDevuelto().equalsIgnoreCase("no")) {
                        DevolucionLibroEst devEst = new DevolucionLibroEst(prestEst.getCodPrestamoLibroEst(),
                                idBibliotecario, estadoRecurso);
                        DevolucionLibroDAOEst devDAOEst = new DevolucionLibroDAOEst();
                        devDAOEst.createDAO(devEst);
                        //se verifica si esta reservado
                        Object reserva = verificarReserva(codBarras);

                        if ((libro.getCodcategoriacoleccion().getNombrecol().equalsIgnoreCase("general")) && (reserva != null)) {
                            if (reserva instanceof ReservaColgenEstudiante) {
                                ReservaColgenDAOEst reservaDAO = new ReservaColgenDAOEst();
                                ReservaColgenEstudiante reservaEst = (ReservaColgenEstudiante) reserva;

                                if (reservaDAO.updateDAO(reservaEst)) {
                                    new VerificaReserva().notificarRetencionEmailEst(libro);
                                }
                            } else {
                                ReservaColgenDAOProf reservaDAO = new ReservaColgenDAOProf();
                                ReservaColgenProfesor reservaProf = (ReservaColgenProfesor) reserva;
                                
                                if (reservaDAO.updateDAO(reservaProf)) {
                                    new VerificaReserva().notificarRetencionEmailProf(libro);
                                }
                            }

                            libro.setDisponibilidad("reservado");
                        } else {
                            libro.setDisponibilidad("disponible");
                        }

                        control.edit(libro);
                        
                        //se actualiza el prestamo
                        prestEst.setDevuelto("si");
                        prestDAOEst.updateDAO(prestEst);
                        
                        notificarDevolucion(prestEst.getCodEstudiante(), libro, codPrestamo);
                        alert.showAlert("Anuncio", "Devolución libro", "La devolución del usuario con codigo "
                                + prestEst.getCodEstudiante() + " se realizo con exito");
                    } else {
                        alert.showAlert("Anuncio", "Devolución libro", "El libro se había devuelto anteriormente");
                    }
                    return true;
                }
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DevolucionDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int consultarPrestamoLibro(String codBarras) {
        PrestamoLibroDAOEst prestDAOEst = new PrestamoLibroDAOEst();
        List<PrestamoLibroEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = 0;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodBarraLibro().equalsIgnoreCase(codBarras)
                    && prestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                codPrestamo = prestamos.get(i).getCodPrestamoLibroEst();
                break;
            }
        }
        return codPrestamo;
    }

    /**
     * el método retorna la reserva asociada al libro del estudiante o profesor.
     *
     * @param codBarras
     * @return reserva
     */
    public Object verificarReserva(String codBarras) {
        ReservaRecursoDAOAbs reservaDAO = new ReservaColgenDAOEst();
        Object reserva = reservaDAO.readDAO(codBarras);

        if (reserva == null) {
            reservaDAO = new ReservaColgenDAOProf();
            reserva = reservaDAO.readDAO(codBarras);
        }

        return reserva;
    }

    /**
     * el método realiza concatenación de los datos necesarios para la
     * construcción del e-mail al estudiante, notificandole de la devoluciòn del
     * libro.
     *
     * @param codEstudiante
     * @param libro
     * @param codPrestamo
     */
    public void notificarDevolucion(String codEstudiante, Libro libro, int codPrestamo) {
        EstudianteJpaController estudianteJpaController = new EstudianteJpaController();
        Estudiante estudiante = estudianteJpaController.findEstudiante(codEstudiante);

        DevolucionLibroDAOEst devDAOEst = new DevolucionLibroDAOEst();
        DevolucionLibroEst devEst = devDAOEst.readDAO(devDAOEst.readCodigoDAO(codPrestamo));

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        String datos = estudiante.getApellido().toUpperCase() + ";"
                + estudiante.getNombre().toUpperCase() + ";"
                + "Código: " + codEstudiante + ";"
                + libro.getTitulo() + ";"
                + libro.getCodbarralibro() + ";"
                + formatoFecha.format((Date) devEst.getFechaDevolucion()) + ";"
                + "null;"
                + "null;"
                + estudiante.getCorreoelectronico();

        NotificacionEmail notificacionEmail = new NotificacionEmail(datos, "mensajeDevolucion");
        notificacionEmail.start();
    }
    
}
