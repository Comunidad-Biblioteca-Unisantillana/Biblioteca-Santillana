package moduloReserva.modelo;

import moduloReserva.entitys.ReservaColgenUsuario;
import controller.exceptions.NonexistentEntityException;
import general.modelo.ConnectionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import moduloReserva.DAO.ReservaColgenDAOEst;
import moduloReserva.DAO.ReservaColgenDAOProf;
import moduloReserva.DAO.ReservaRecursoDAOAbs;
import recursos.controllers.LibroJpaController;
import recursos.entitys.Libro;

/**
 * la clase se encarga de eliminar reservas que no se han hecho
 * efectivas(vencidas).
 *
 * @author Miguel Fernàndez
 * @creado: 08/09/2019
 * @author Miguel Fernàndez
 * @modificado: 08/09/2019
 */
public class VerificaReservaVencida {

    private ConnectionBD connection;

    /**
     * constructor de la clase sin paràmetros.
     */
    public VerificaReservaVencida() {
        connection = ConnectionBD.getInstance();
    }

    /**
     * el mètodo busca una reserva que se ha vencido y la elimina.
     */
    public void reserva() {
        Date fechaActual = new Date();
        ArrayList<ReservaColgenUsuario> listReservas = readReservaColgenUsuario();

        if (listReservas != null) {
            for (int i = 0; i < listReservas.size(); i++) {
                String codBarraLibro = listReservas.get(i).getcodBarraLibro();
                LibroJpaController libroJpaController = new LibroJpaController();
                Libro libro = libroJpaController.findLibro(codBarraLibro);

                if (libro != null) {
                    //Se compara si la fecha actual es mayor a la fecha limite de reserva
                    //Si ocurre esto, entonces se debería quitar la reserva.
                    if (fechaActual.after(listReservas.get(i).getFechaLimiteReserva())) {
                        try {
                            libro.setDisponibilidad("disponible");
                            libroJpaController.edit(libro);
                            deleteReservaColgenUsaurio(codBarraLibro, listReservas.get(i).getCodUsuario());
                            System.out.println("Quitando Reserva....!");
                        } catch (NonexistentEntityException ex) {
                            System.out.println("Error con el libro, en tarea programada de reserva.");
                        } catch (Exception ex) {
                            System.out.println("Error al actulizar el estado del libro, en tarea programada de reserva.");
                        }
                    }
                }
            }
        }
    }

    /**
     * el mètodo consulta las reservas retenidas en profesor y estudiante.
     *
     * @param reserva
     * @return ArrayList<ReservaColgenUsuario>
     */
    private ArrayList<ReservaColgenUsuario> readReservaColgenUsuario() {
        PreparedStatement pps;
        ResultSet rs;
        ArrayList<ReservaColgenUsuario> listReservas = new ArrayList<>();
        String sqlSentence
                = " SELECT rce.codReservaColGenEst AS codReservaColGen, rce.codBarraLibro, rce.codEstudiante AS codUsuario, rce.idBibliotecario, rce.fechaReserva, rce.fechaRetencion, rce.fechaLimiteReserva"
                + " FROM Reserva_Colgen_Estudiante rce"
                + " WHERE fechaRetencion IS NOT NULL"
                + " UNION"
                + " SELECT rcp.codReservaColGenProf AS codReservaColGen, rcp.codBarraLibro, rcp.idProfesor AS codUsuario, rcp.idBibliotecario, rcp.fechaReserva, rcp.fechaRetencion, rcp.fechaLimiteReserva"
                + " FROM Reserva_Colgen_Profesor rcp"
                + " WHERE fechaRetencion IS NOT NULL";

        try {
            pps = connection.getConnection().prepareStatement(sqlSentence);
            rs = pps.executeQuery();

            while (rs.next()) {
                ReservaColgenUsuario reservaTmp = new ReservaColgenUsuario();
                reservaTmp.setCodReservaColGen(rs.getInt("codReservaColGen"));
                reservaTmp.setcodBarraLibro(rs.getString("codBarraLibro"));
                reservaTmp.setCodUsuario(rs.getString("codUsuario"));
                reservaTmp.setIdBibliotecario(rs.getString("idBibliotecario"));
                reservaTmp.setFechaReserva(rs.getDate("fechaReserva"));
                reservaTmp.setFechaRetencion(rs.getDate("fechaRetencion"));
                reservaTmp.setFechaLimiteReserva(rs.getDate("fechaLimiteReserva"));
                listReservas.add(reservaTmp);
            }

            rs.close();
            pps.close();
        } catch (SQLException ex) {
            System.out.println("Error con el readReservaColgenUsuario, en  actulizadorReserva.");
        }
        return listReservas;
    }

    /**
     * el mètodo elimina la reserva de un estudiante o profesor.
     *
     * @param codBarraLibro
     * @param codUsuario
     */
    private void deleteReservaColgenUsaurio(String codBarraLibro, String codUsuario) {
        ReservaRecursoDAOAbs reserva;

        if (codUsuario.length() == 7) {
            reserva = new ReservaColgenDAOEst();
        } else {
            reserva = new ReservaColgenDAOProf();
        }

        reserva.deleteDAO(codBarraLibro, codUsuario);
    }

}
