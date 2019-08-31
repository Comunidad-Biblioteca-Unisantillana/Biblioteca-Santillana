package moduloMulta.modelo;

import moduloMulta.DAO.MultaDiccionarioDAOProf;
import moduloMulta.DAO.MultaEnciclopediaDAOProf;
import moduloMulta.DAO.MultaLibroDAOProf;
import moduloMulta.DAO.MultaMapaDAOProf;
import moduloMulta.DAO.MultaPeriodicoDAOProf;
import moduloMulta.DAO.MultaRevistaDAOProf;

/**
 * la clse se encarga de verificar las multas de un profesor en un recurso
 * especifico.
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author Miguel Fernández
 * @modificado: 31/08/2019
 */
public class VerificaMultaProfesor implements IVerificaMulta {

    /**
     * constructor de la clase sin parámetros.
     */
    public VerificaMultaProfesor() {

    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * libro.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaLibro(String idUsuario) {
        MultaLibroDAOProf multaLibroDAOProf = new MultaLibroDAOProf();
        return multaLibroDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * enciclopedia.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaEnciclopedia(String idUsuario) {
        MultaEnciclopediaDAOProf multaEnciclopediaDAOProf = new MultaEnciclopediaDAOProf();
        return multaEnciclopediaDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * diccionario.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaDiccionario(String idUsuario) {
        MultaDiccionarioDAOProf multaDiccionarioDAOProf = new MultaDiccionarioDAOProf();
        return multaDiccionarioDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * revista.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaRevista(String idUsuario) {
        MultaRevistaDAOProf multaRevistaDAOProf = new MultaRevistaDAOProf();
        return multaRevistaDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * periódico.
     *
     * @param idUsuario
     * @return boolean
     */
    @Override
    public boolean buscarMultaPeriodico(String idUsuario) {
        MultaPeriodicoDAOProf multaPeriodicoDAOProf = new MultaPeriodicoDAOProf();
        return multaPeriodicoDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa asociada al recurso
     * mapa.
     *
     * @param idUsuario
     * @return boolean
     * @return boolean
     */
    @Override
    public boolean buscarMultaMapa(String idUsuario) {
        MultaMapaDAOProf multaMapaDAOProf = new MultaMapaDAOProf();
        return multaMapaDAOProf.readIdUsuarioDAO(idUsuario);
    }

    /**
     * el método verifica, si el profesor tiene una multa en algún recurso.
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
