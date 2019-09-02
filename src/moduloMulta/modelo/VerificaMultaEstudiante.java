package moduloMulta.modelo;

import moduloMulta.DAO.MultaDiccionarioDAOEst;
import moduloMulta.DAO.MultaEnciclopediaDAOEst;
import moduloMulta.DAO.MultaLibroDAOEst;
import moduloMulta.DAO.MultaMapaDAOEst;
import moduloMulta.DAO.MultaPeriodicoDAOEst;
import moduloMulta.DAO.MultaRevistaDAOEst;

/**
 * la clse se encarga de verificar las multas de un estudiante en un recurso
 * especifico.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class VerificaMultaEstudiante implements IVerificaMulta {

    /**
     * constructor de la clase sin parámetros.
     */
    public VerificaMultaEstudiante() {

    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * libro.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaLibro(String idUsuario) {
        MultaLibroDAOEst multaLibroDAOEst = new MultaLibroDAOEst();
        return multaLibroDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * enciclopedia.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaEnciclopedia(String idUsuario) {
        MultaEnciclopediaDAOEst multaEnciclopediaDAOEst = new MultaEnciclopediaDAOEst();
        return multaEnciclopediaDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * diccionario.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaDiccionario(String idUsuario) {
        MultaDiccionarioDAOEst multaDiccionarioDAOEst = new MultaDiccionarioDAOEst();
        return multaDiccionarioDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * revista.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaRevista(String idUsuario) {
        MultaRevistaDAOEst multaRevistaDAOEst = new MultaRevistaDAOEst();
        return multaRevistaDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * periódico.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaPeriodico(String idUsuario) {
        MultaPeriodicoDAOEst multaPeriodicoDAOEst = new MultaPeriodicoDAOEst();
        return multaPeriodicoDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa asociada al recurso
     * mapa.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaMapa(String idUsuario) {
        MultaMapaDAOEst multaMapaDAOEst = new MultaMapaDAOEst();
        return multaMapaDAOEst.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el estudiante tiene una multa en algún recurso.
     *
     * @param idUsuario
     * @return estadoMulta
     */
    @Override
    public boolean verificarMultaUsuario(String idUsuario) {
        boolean estadoMulta = false;

        if (buscarMultaLibro(idUsuario)) {
            estadoMulta = true;
        } else if (buscarMultaEnciclopedia(idUsuario)) {
            estadoMulta = true;
        } else if (buscarMultaDiccionario(idUsuario)) {
            estadoMulta = true;
        } else if (buscarMultaRevista(idUsuario)) {
            estadoMulta = true;
        } else if (buscarMultaPeriodico(idUsuario)) {
            estadoMulta = true;
        } else if (buscarMultaMapa(idUsuario)) {
            estadoMulta = true;
        }

        return estadoMulta;
    }

}
