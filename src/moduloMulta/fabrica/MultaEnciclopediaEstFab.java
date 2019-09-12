package moduloMulta.fabrica;

import general.modelo.ServicioFecha;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOEst;
import moduloDevolucion.entitys.DevolucionEnciclopediaEst;
import moduloMulta.DAO.MultaEnciclopediaDAOEst;
import moduloMulta.control.ControlPrecioMultaJpaController;
import moduloMulta.entitys.ControlPrecioMulta;
import moduloMulta.entitys.MultaEnciclopediaEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOEst;
import moduloPrestamo.entitys.PrestamoEnciclopediaEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaEnciclopediaEstFab implements IMulta {

    public MultaEnciclopediaEstFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {
        try {
            List<MultaEnciclopediaEst> listaMultas = readMultasEnciclopediasEstDAO();
            for (int i = 0; i < listaMultas.size(); i++) {
                if (listaMultas.get(i).getEstadoCancelacion().equalsIgnoreCase("no")) {
                    MultaEnciclopediaDAOEst multaDAO = new MultaEnciclopediaDAOEst();
                    Date fechaDevolucion = getFechaDevolucionPrestamo(listaMultas.get(i).getCodPrestamoEnciclopediaEst());
                    java.util.Date fechaActual = new java.util.Date();
                    if (fechaDevolucion != null) {
                        int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()), fechaDevolucion);//Obtiene los dias atrasados
                        listaMultas.get(i).setDiasAtrasados(diasAtrasados);
                        multaDAO.updateDAO(listaMultas.get(i));
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MultaDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void generarMulta() {
        java.util.Date fechaActual = new java.util.Date();
        List<PrestamoEnciclopediaEst> listaPres = readPrestamosEnciclopediasEstDAO();
        List<DevolucionEnciclopediaEst> listaDev = readDevolucionEnciclopediasEstDAO();
        boolean existePrestamoDev = false;

        if (listaPres != null && listaDev != null) {
            for (int i = 0; i < listaPres.size(); i++) {
                int codPrestamo = listaPres.get(i).getCodPrestamoEnciclopediaEst();
                //Se compara si la fecha actual es mayor a la fecha de devoluciÃ³n
                //Si ocurre esto, entonces se deberÃ­a generar una multa, en caso de que no se haya devuelto el recurso.
                if (fechaActual.after(listaPres.get(i).getFechaDevolucion())) {
                    for (int j = 0; j < listaDev.size(); j++) {
                        //Compara si existe alguna devoluciÃ³n con el cÃ³digo de este prÃ©stamo, si existe entonces
                        //Ya fue devuelto el recurso y no se debe generar una multa.
                        if (Objects.equals(listaDev.get(j).getCodPrestamoEnciclopediaEst(), codPrestamo)) {
                            existePrestamoDev = true;
                            break;
                        }
                    }

                    if (!existePrestamoDev) {
                        try {
                            //En caso de que no exista una devolucion con ese prestamo, entonces se genera la multa
                            System.out.println("Generando multa...!");
                            List<MultaEnciclopediaEst> listaMultas = readMultasEnciclopediasEstDAO();
                            boolean existeMulta = false;
                            //Se verifica si existe una multa sobre ese prestamo, en caso de que ya exista entonces no se hace otra multa
                            if (listaMultas != null) {
                                for (int k = 0; k < listaMultas.size(); k++) {
                                    if (listaMultas.get(k).getCodPrestamoEnciclopediaEst()== codPrestamo) {
                                        existeMulta = true;
                                        System.out.println("la multa ya existe");
                                        break;
                                    }
                                }
                            }
                            if (!existeMulta) {
                                System.out.println("Se genera multa!");
                                ControlPrecioMultaJpaController precioMultaJPA = new ControlPrecioMultaJpaController();
                                ControlPrecioMulta precioMulta = precioMultaJPA.findControlPrecioMulta(1);
                                MultaEnciclopediaDAOEst multa = new MultaEnciclopediaDAOEst();
                                int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()),new Date(listaPres.get(i).getFechaDevolucion().getTime()));//Obtiene los dias atrasados
                                if (precioMulta != null) {
                                    MultaEnciclopediaEst multaEst = new MultaEnciclopediaEst(codPrestamo, diasAtrasados, 1, precioMulta.getValorpordia(), "no","no aplica",new Date(fechaActual.getTime()));
                                    multa.createDAO(multaEst);
                                    System.out.println("hole");
                                } else {
                                    System.out.println("Error con el precio de la multa..");
                                }
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(MultaDiccionarioEstFab.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                existePrestamoDev = false; //Se renueva el valor               
            }
        }
    }

    private Date getFechaDevolucionPrestamo(int codigoPrestamo) {
        List<PrestamoEnciclopediaEst> listaPrestamos = readPrestamosEnciclopediasEstDAO();
        if (listaPrestamos != null) {
            for (int i = 0; i < listaPrestamos.size(); i++) {
                if (listaPrestamos.get(i) != null) {
                    if (listaPrestamos.get(i).getCodPrestamoEnciclopediaEst() == codigoPrestamo
                            && listaPrestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                        return new Date(listaPrestamos.get(i).getFechaDevolucion().getTime());
                    }
                }
            }
        }
        return null;
    }

    private void notificarMulta(PrestamoEnciclopediaEst prestamo, int valorMulta) {

    }

    private List<DevolucionEnciclopediaEst> readDevolucionEnciclopediasEstDAO() {
        DevolucionEnciclopediaDAOEst dev = new DevolucionEnciclopediaDAOEst();
        List<DevolucionEnciclopediaEst> listaDev = null;
        try {
            listaDev = dev.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen devoluciones en la BD");
        }
        return listaDev;
    }

    private List<MultaEnciclopediaEst> readMultasEnciclopediasEstDAO() {
        MultaEnciclopediaDAOEst multa = new MultaEnciclopediaDAOEst();
        List<MultaEnciclopediaEst> listaMulta = null;
        try {
            listaMulta = multa.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen multas en la BD");
        }
        return listaMulta;
    }

    private List<PrestamoEnciclopediaEst> readPrestamosEnciclopediasEstDAO() {
        PrestamoEnciclopediaDAOEst pres = new PrestamoEnciclopediaDAOEst();
        List<PrestamoEnciclopediaEst> listaPrestamos = null;
        try {
            listaPrestamos = pres.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen prestamos en la BD");
        }
        return listaPrestamos;
    }

}
