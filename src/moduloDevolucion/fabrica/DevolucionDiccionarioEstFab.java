package moduloDevolucion.fabrica;

import controllers.DiccionarioJpaController;
import controllers.LibroJpaController;
import entitys.Diccionario;
import java.util.List;
import moduloDevolucion.IDevolucion;
import moduloPrestamo.DAO.PrestamoDiccionarioDAOEst;
import moduloPrestamo.entitys.PrestamoDiccionarioEst;

/**
 * @author Camilo Jaramillo
 * @version 1.0
 * @created 04-ago.-2019 10:37:21 a. m.
 */
public class DevolucionDiccionarioEstFab implements IDevolucion {

    public DevolucionDiccionarioEstFab() {
    }

    @Override
    public boolean ejecutarDevolucion(String codBarras, String idBibliotecario, String estadoRecurso) {
        DiccionarioJpaController control = new DiccionarioJpaController();
        Diccionario diccionario = control.findDiccionario(codBarras);
        if (diccionario != null) {
            int codPrestamo = consultarPrestamoDiccionario(codBarras);
            if(codPrestamo > 0){
                
            }
        }
        return false;
    }

    public int consultarPrestamoDiccionario(String codBarras) {
        PrestamoDiccionarioDAOEst prestDAOEst = new PrestamoDiccionarioDAOEst();
        List<PrestamoDiccionarioEst> prestamos = prestDAOEst.readAllDAO();
        int codPrestamo = -1;
        for(int i=0;i<prestamos.size();i++){
            if(prestamos.get(i).getCodBarraDiccionario().equalsIgnoreCase(codBarras)){
                codPrestamo = prestamos.get(i).getCodPrestamoDiccionarioEst();
                break; 
            }
        }
        return codPrestamo;
    }

    public void notificarDevolucion(String codEstudiante, String tituloRecurso, java.util.Date fechaDevolucion) {

    }
}//end DevolucionDiccionarioEstFab
