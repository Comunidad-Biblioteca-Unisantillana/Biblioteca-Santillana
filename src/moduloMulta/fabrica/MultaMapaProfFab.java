package moduloMulta.fabrica;

import general.modelo.ServicioFecha;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import moduloDevolucion.DAO.DevolucionMapaDAOProf;
import moduloDevolucion.entitys.DevolucionMapaProf;
import moduloMulta.DAO.MultaMapaDAOProf;
import moduloMulta.control.ControlPrecioMultaJpaController;
import moduloMulta.entitys.ControlPrecioMulta;
import moduloMulta.entitys.MultaMapaProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.DAO.PrestamoMapaDAOProf;
import moduloPrestamo.entitys.PrestamoMapaProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaMapaProfFab implements IMulta {

    public MultaMapaProfFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {
        try {
            List<MultaMapaProf> listaMultas = readMultasMapasProfDAO();
            for (int i = 0; i < listaMultas.size(); i++) {
                if (listaMultas.get(i).getEstadoCancelacion().equalsIgnoreCase("no")) {
                    MultaMapaDAOProf multaDAO = new MultaMapaDAOProf();
                    Date fechaDevolucion = getFechaDevolucionPrestamo(listaMultas.get(i).getCodPrestamoMapaProf());
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
        List<PrestamoMapaProf> listaPres = readPrestamosMapasProfDAO();
        List<DevolucionMapaProf> listaDev = readDevolucionMapasProfDAO();
        boolean existePrestamoDev = false;

        if (listaPres != null && listaDev != null) {
            for (int i = 0; i < listaPres.size(); i++) {
                int codPrestamo = listaPres.get(i).getCodPrestamoMapaProf();
                //Se compara si la fecha actual es mayor a la fecha de devoluciÃ³n
                //Si ocurre esto, entonces se deberÃ­a generar una multa, en caso de que no se haya devuelto el recurso.
                if (fechaActual.after(listaPres.get(i).getFechaDevolucion())) {
                    for (int j = 0; j < listaDev.size(); j++) {
                        //Compara si existe alguna devoluciÃ³n con el cÃ³digo de este prÃ©stamo, si existe entonces
                        //Ya fue devuelto el recurso y no se debe generar una multa.
                        if (Objects.equals(listaDev.get(j).getCodPrestamoMapaProf(), codPrestamo)) {
                            existePrestamoDev = true;
                            break;
                        }
                    }

                    if (!existePrestamoDev) {
                        try {
                            //En caso de que no exista una devolucion con ese prestamo, entonces se genera la multa
                            System.out.println("Generando multa...!");
                            List<MultaMapaProf> listaMultas = readMultasMapasProfDAO();
                            boolean existeMulta = false;
                            //Se verifica si existe una multa sobre ese prestamo, en caso de que ya exista entonces no se hace otra multa
                            if (listaMultas != null) {
                                for (int k = 0; k < listaMultas.size(); k++) {
                                    if (listaMultas.get(k).getCodPrestamoMapaProf()== codPrestamo) {
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
                                MultaMapaDAOProf multa = new MultaMapaDAOProf();
                                int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()),new Date(listaPres.get(i).getFechaDevolucion().getTime()));//Obtiene los dias atrasados
                                if (precioMulta != null) {
                                    MultaMapaProf multaEst = new MultaMapaProf(codPrestamo, diasAtrasados, 1, precioMulta.getValorpordia(), "no","no aplica",new Date(fechaActual.getTime()));
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
        List<PrestamoMapaProf> listaPrestamos = readPrestamosMapasProfDAO();
        if (listaPrestamos != null) {
            for (int i = 0; i < listaPrestamos.size(); i++) {
                if (listaPrestamos.get(i) != null) {
                    if (listaPrestamos.get(i).getCodPrestamoMapaProf() == codigoPrestamo) {
                        return new Date(listaPrestamos.get(i).getFechaDevolucion().getTime());
                    }
                }
            }
        }
        return null;
    }

    public void notificarMulta(PrestamoMapaProf prestamo, int valorMulta) {

    }

    private List<DevolucionMapaProf> readDevolucionMapasProfDAO() {
        DevolucionMapaDAOProf dev = new DevolucionMapaDAOProf();
        List<DevolucionMapaProf> listaDev = null;
        try {
            listaDev = dev.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen devoluciones en la BD");
        }
        return listaDev;
    }

    private List<MultaMapaProf> readMultasMapasProfDAO() {
        MultaMapaDAOProf multa = new MultaMapaDAOProf();
        List<MultaMapaProf> listaMulta = null;
        try {
            listaMulta = multa.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen multas en la BD");
        }
        return listaMulta;
    }

    private List<PrestamoMapaProf> readPrestamosMapasProfDAO() {
        PrestamoMapaDAOProf  pres = new PrestamoMapaDAOProf();
        List<PrestamoMapaProf> listaPrestamos = null;
        try {
            listaPrestamos = pres.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen prestamos en la BD");
        }
        return listaPrestamos;
    }

}
