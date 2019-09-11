package moduloMulta.fabrica;

import general.modelo.ServicioFecha;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import moduloDevolucion.DAO.DevolucionEnciclopediaDAOProf;
import moduloDevolucion.entitys.DevolucionEnciclopediaProf;
import moduloMulta.DAO.MultaEnciclopediaDAOProf;
import moduloMulta.control.ControlPrecioMultaJpaController;
import moduloMulta.entitys.ControlPrecioMulta;
import moduloMulta.entitys.MultaEnciclopediaProf;
import moduloMulta.modelo.IMulta;
import moduloPrestamo.DAO.PrestamoEnciclopediaDAOProf;
import moduloPrestamo.entitys.PrestamoEnciclopediaProf;

/**
 *
 * @author Miguel Fernández
 * @creado: 30/08/2019
 * @author
 * @modificado:
 */
public class MultaEnciclopediaProfFab implements IMulta {

    public MultaEnciclopediaProfFab() {

    }

    @Override
    public void actualizarDiasAtrasadosMulta() {
        try {
            List<MultaEnciclopediaProf> listaMultas = readMultasEnciclopediaProfDAO();
            for (int i = 0; i < listaMultas.size(); i++) {
                if (listaMultas.get(i).getEstadoCancelacion().equalsIgnoreCase("no")) {
                    MultaEnciclopediaDAOProf multaDAO = new MultaEnciclopediaDAOProf();
                    Date fechaDevolucion = getFechaDevolucionPrestamo(listaMultas.get(i).getCodPrestamoEnciclopediaProf());
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
        List<PrestamoEnciclopediaProf> listaPres = readPrestamosEnciclopediasProfDAO();
        List<DevolucionEnciclopediaProf> listaDev = readDevolucionEnciclopediasProfDAO();
        boolean existePrestamoDev = false;

        if (listaPres != null && listaDev != null) {
            for (int i = 0; i < listaPres.size(); i++) {
                int codPrestamo = listaPres.get(i).getCodPrestamoEnciclopediaProf();
                //Se compara si la fecha actual es mayor a la fecha de devoluciÃ³n
                //Si ocurre esto, entonces se deberÃ­a generar una multa, en caso de que no se haya devuelto el recurso.
                if (fechaActual.after(listaPres.get(i).getFechaDevolucion())) {
                    for (int j = 0; j < listaDev.size(); j++) {
                        //Compara si existe alguna devoluciÃ³n con el cÃ³digo de este prÃ©stamo, si existe entonces
                        //Ya fue devuelto el recurso y no se debe generar una multa.
                        if (Objects.equals(listaDev.get(j).getCodPrestamoEnciclopediaProf(), codPrestamo)) {
                            existePrestamoDev = true;
                            break;
                        }
                    }

                    if (!existePrestamoDev) {
                        try {
                            //En caso de que no exista una devolucion con ese prestamo, entonces se genera la multa
                            System.out.println("Generando multa...!");
                            List<MultaEnciclopediaProf> listaMultas = readMultasEnciclopediaProfDAO();
                            boolean existeMulta = false;
                            //Se verifica si existe una multa sobre ese prestamo, en caso de que ya exista entonces no se hace otra multa
                            if (listaMultas != null) {
                                for (int k = 0; k < listaMultas.size(); k++) {
                                    if (listaMultas.get(k).getCodPrestamoEnciclopediaProf() == codPrestamo) {
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
                                MultaEnciclopediaDAOProf multa = new MultaEnciclopediaDAOProf();
                                int diasAtrasados = ServicioFecha.diferenciaEnDias(new Date(fechaActual.getTime()),new Date(listaPres.get(i).getFechaDevolucion().getTime()));//Obtiene los dias atrasados
                                if (precioMulta != null) {
                                    MultaEnciclopediaProf multaEst = new MultaEnciclopediaProf(codPrestamo, diasAtrasados, 1, precioMulta.getValorpordia(), "no","no aplica",new Date(fechaActual.getTime()));
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
        List<PrestamoEnciclopediaProf> listaPrestamos = readPrestamosEnciclopediasProfDAO();
        if (listaPrestamos != null) {
            for (int i = 0; i < listaPrestamos.size(); i++) {
                if (listaPrestamos.get(i) != null) {
                    if (listaPrestamos.get(i).getCodPrestamoEnciclopediaProf()== codigoPrestamo) {
                        return new Date(listaPrestamos.get(i).getFechaDevolucion().getTime());
                    }
                }
            }
        }
        return null;
    }

    private void notificarMulta(PrestamoEnciclopediaProf prestamo, int valorMulta) {

    }

    private List<DevolucionEnciclopediaProf> readDevolucionEnciclopediasProfDAO() {
        DevolucionEnciclopediaDAOProf dev = new DevolucionEnciclopediaDAOProf();
        List<DevolucionEnciclopediaProf> listaDev = null;
        try {
            listaDev = dev.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen devoluciones en la BD");
        }
        return listaDev;
    }

    private List<MultaEnciclopediaProf> readMultasEnciclopediaProfDAO() {
        MultaEnciclopediaDAOProf multa = new MultaEnciclopediaDAOProf();
        List<MultaEnciclopediaProf> listaMulta = null;
        try {
            listaMulta = multa.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen multas en la BD");
        }
        return listaMulta;
    }

    private List<PrestamoEnciclopediaProf> readPrestamosEnciclopediasProfDAO() {
        PrestamoEnciclopediaDAOProf pres = new PrestamoEnciclopediaDAOProf();
        List<PrestamoEnciclopediaProf> listaPrestamos = null;
        try {
            listaPrestamos = pres.readAllDAO();
        } catch (Exception e) {
            System.out.println("No existen prestamos en la BD");
        }
        return listaPrestamos;
    }

}
