package moduloMulta.modelo;

import moduloMulta.fabrica.MultaDiccionarioEstFab;
import moduloMulta.fabrica.MultaDiccionarioProfFab;
import moduloMulta.fabrica.MultaEnciclopediaEstFab;
import moduloMulta.fabrica.MultaEnciclopediaProfFab;
import moduloMulta.fabrica.MultaLibroEstFab;
import moduloMulta.fabrica.MultaLibroProfFab;
import moduloMulta.fabrica.MultaMapaEstFab;
import moduloMulta.fabrica.MultaMapaProfFab;
import moduloMulta.fabrica.MultaPeriodicoEstFab;
import moduloMulta.fabrica.MultaPeriodicoProfFab;
import moduloMulta.fabrica.MultaRevistaEstFab;
import moduloMulta.fabrica.MultaRevistaProfFab;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class FabricaMulta {

    public IMulta getMulta(String tipoMulta, String usuario) {
        IMulta multa = null;
        if (usuario.equalsIgnoreCase("estudiante")) {
            switch (tipoMulta.toLowerCase()) {
                case "diccionario":
                    multa = new MultaDiccionarioEstFab();
                    break;
                case "enciclopedia":
                    multa = new MultaEnciclopediaEstFab();
                    break;
                case "libro":
                    multa = new MultaLibroEstFab();
                    break;
                case "mapa":
                    multa = new MultaMapaEstFab();
                    break;
                case "periodico":
                    multa = new MultaPeriodicoEstFab();
                    break;
                case "revista":
                    multa = new MultaRevistaEstFab();
                    break;
                default:
                    System.out.println("error al fabricar una multa de estudiante");
                    break;
            }
        } else {
            switch (tipoMulta.toLowerCase()) {
                case "diccionario":
                    multa = new MultaDiccionarioProfFab();
                    break;
                case "enciclopedia":
                    multa = new MultaEnciclopediaProfFab();
                    break;
                case "libro":
                    multa = new MultaLibroProfFab();
                    break;
                case "mapa":
                    multa = new MultaMapaProfFab();
                    break;
                case "periodico":
                    multa = new MultaPeriodicoProfFab();
                    break;
                case "revista":
                    multa = new MultaRevistaProfFab();
                    break;
                default:
                    System.out.println("error al fabricar una multa de profesor");
                    break;
            }
        }
        return multa;
    }

}
