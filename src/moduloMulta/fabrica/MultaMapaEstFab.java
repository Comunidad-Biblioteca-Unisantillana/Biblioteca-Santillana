package moduloMulta.fabrica;

import general.modelo.ServicioFecha;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import moduloDevolucion.DAO.DevolucionMapaDAOEst;
import moduloDevolucion.entitys.DevolucionMapaEst;
import moduloMulta.DAO.MultaMapaDAOEst;
import moduloMulta.control.ControlPrecioMultaJpaController;
import moduloMulta.entitys.ControlPrecioMulta;
import moduloMulta.entitys.MultaMapaEst;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.DAO.PrestamoMapaDAOEst;
import moduloPrestamo.entitys.PrestamoMapaEst;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaMapaEstFab implements IMulta {

    public MultaMapaEstFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {
        try {
            List<MultaMapaEst> listaMultas = readMultasMapasEstDAO();
            for (int i = 0; i < listaMultas.size(); i++) {
                if (listaMultas.get(i).getEstadoCancelacion().equalsIgnoreCase("no")) {
                    MultaMapaDAOEst multaDAO = new MultaMapaDAOEst();
                    Date fechaDevolucion = getFechaDevolucionPrestamo(listaMultas.get(i).getCodPrestamoMapaEst());
                    java.util.Date fechaActual = new java.util.Date();
                    if (fechaDevolucion != null) {
                        int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()), fechaDevolucion);//Obtiene los dias atrasados
                        listaMultas.get(i).setDiasAtrasados(diasAtrasados);
                        multaDAO.updateDAO(listaMultas.get(i));
                    }
                }
            }
        } catch (Exception ex) {
            
        }
    }

    @Override
    public void generarMulta() {
        java.util.Date fechaActual = new java.util.Date();
        List<PrestamoMapaEst> listaPres = readPrestamosMapasEstDAO();
        List<DevolucionMapaEst> listaDev = readDevolucionMapasEstDAO();
        boolean existePrestamoDev = false;

        if (listaPres != null && listaDev != null) {
            for (int i = 0; i < listaPres.size(); i++) {
                int codPrestamo = listaPres.get(i).getCodPrestamoMapaEst();
                //Se compara si la fecha actual es mayor a la fecha de devoluciÃ³n
                //Si ocurre esto, entonces se deberÃ­a generar una multa, en caso de que no se haya devuelto el recurso.
                if (fechaActual.after(listaPres.get(i).getFechaDevolucion())) {
                    for (int j = 0; j < listaDev.size(); j++) {
                        //Compara si existe alguna devoluciÃ³n con el cÃ³digo de este prÃ©stamo, si existe entonces
                        //Ya fue devuelto el recurso y no se debe generar una multa.
                        if (Objects.equals(listaDev.get(j).getCodPrestamoMapaEst(), codPrestamo)) {
                            existePrestamoDev = true;
                            break;
                        }
                    }

                    if (!existePrestamoDev) {
                        try {
                            //En caso de que no exista una devolucion con ese prestamo, entonces se genera la multa
                            System.out.println("Generando multa...!");
                            List<MultaMapaEst> listaMultas = readMultasMapasEstDAO();
                            boolean existeMulta = false;
                            //Se verifica si existe una multa sobre ese prestamo, en caso de que ya exista entonces no se hace otra multa
                            if (listaMultas != null) {
                                for (int k = 0; k < listaMultas.size(); k++) {
                                    if (listaMultas.get(k).getCodPrestamoMapaEst()== codPrestamo) {
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
                                MultaMapaDAOEst multa = new MultaMapaDAOEst();
                                int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()),new Date(listaPres.get(i).getFechaDevolucion().getTime()));//Obtiene los dias atrasados
                                if (precioMulta != null) {
                                    MultaMapaEst multaEst = new MultaMapaEst(codPrestamo, diasAtrasados, 1, precioMulta.getValorpordia(), "no","no aplica",new Date(fechaActual.getTime()));
                                    multa.createDAO(multaEst);
                                    System.out.println("hole");
                                } else {
                                    System.out.println("Error con el precio de la multa..");
                                }
                            }
                        } catch (Exception ex) {
                        }
                    }
                }
                existePrestamoDev = false; //Se renueva el valor               
            }
        }
    }

    private Date getFechaDevolucionPrestamo(int codigoPrestamo) {
        List<PrestamoMapaEst> listaPrestamos = readPrestamosMapasEstDAO();
        if (listaPrestamos != null) {
            for (int i = 0; i < listaPrestamos.size(); i++) {
                if (listaPrestamos.get(i) != null) {
                    if (listaPrestamos.get(i).getCodPrestamoMapaEst() == codigoPrestamo
                            && listaPrestamos.get(i).getDevuelto().equalsIgnoreCase("no")) {
                        return new Date(listaPrestamos.get(i).getFechaDevolucion().getTime());
                    }
                }
            }
        }
        return null;
    }

    private void notificarMulta(PrestamoMapaEst prestamo, int valorMulta) {
        
    }

    private List<DevolucionMapaEst> readDevolucionMapasEstDAO() {
        DevolucionMapaDAOEst dev = new DevolucionMapaDAOEst();
        List<DevolucionMapaEst> listaDev = null;
        try {
            listaDev = dev.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen devoluciones en la BD");
        }
        return listaDev;
    }

    private List<MultaMapaEst> readMultasMapasEstDAO() {
        MultaMapaDAOEst multa = new MultaMapaDAOEst();
        List<MultaMapaEst> listaMulta = null;
        try {
            listaMulta = multa.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen multas en la BD");
        }
        return listaMulta;
    }

    private List<PrestamoMapaEst> readPrestamosMapasEstDAO() {
        PrestamoMapaDAOEst  pres = new PrestamoMapaDAOEst();
        List<PrestamoMapaEst> listaPrestamos = null;
        try {
            listaPrestamos = pres.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen prestamos en la BD");
        }
        return listaPrestamos;
    }

}
