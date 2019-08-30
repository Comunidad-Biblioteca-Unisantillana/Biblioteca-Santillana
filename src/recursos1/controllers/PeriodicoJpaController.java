package recursos1.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.CategoriaColeccion;
import recursos1.entitys.Periodico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class PeriodicoJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * constructor sin parámetros.
     */
    public PeriodicoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * el metódo retorna la conexión con la base de datos.
     *
     * @return em
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();    // -> em
    }

    /**
     * el metódo añade un nuevo periodico en la base de datos.
     *
     * @param periodico
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(Periodico periodico) throws PreexistingEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = periodico.getCodcategoriacoleccion();

            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                periodico.setCodcategoriacoleccion(codcategoriacoleccion);
            }

            em.persist(periodico);

            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getPeriodicoList().add(periodico);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {

            if (findPeriodico(periodico.getCodbarraperiodico()) != null) {
                throw new PreexistingEntityException("Periodico " + periodico + " already exists.", ex);
            }

            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * el metódo actualiza uno o más atributo(s) del periodico en la base de
     * datos.
     *
     * @param periodico
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Periodico periodico) throws NonexistentEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodico persistentPeriodico = em.find(Periodico.class, periodico.getCodbarraperiodico());
            CategoriaColeccion codcategoriacoleccionOld = persistentPeriodico.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = periodico.getCodcategoriacoleccion();

            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                periodico.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }

            periodico = em.merge(periodico);

            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getPeriodicoList().remove(periodico);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }

            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getPeriodicoList().add(periodico);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                String id = periodico.getCodbarraperiodico();

                if (findPeriodico(id) == null) {
                    throw new NonexistentEntityException("The periodico with id " + id + " no longer exists.");
                }
            }

            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * el metódo elimina un periodico de la base de datos.
     *
     * @param id
     * @throws NonexistentEntityException
     */
    private void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodico periodico;

            try {
                periodico = em.getReference(Periodico.class, id);
                periodico.getCodbarraperiodico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodico with id " + id + " no longer exists.", enfe);
            }

            CategoriaColeccion codcategoriacoleccion = periodico.getCodcategoriacoleccion();

            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getPeriodicoList().remove(periodico);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }

            em.remove(periodico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @return
     */
    public List<Periodico> findPeriodicoEntities() {
        return findPeriodicoEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<Periodico> findPeriodicoEntities(int maxResults, int firstResult) {
        return findPeriodicoEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<Periodico> findPeriodicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodico.class));
            Query q = em.createQuery(cq);

            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retrona un periodico asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return periodico
     */
    public Periodico findPeriodico(String codBarras) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodico.class, codBarras); // -> periodico
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna un periodico asociado al issn ingresado.
     *
     * @param issn
     * @return periodico
     */
    public Periodico findPeriodicoISSN(String issn) {
        EntityManager em = getEntityManager();
        Periodico periodico = null;

        try {
            Query query = em.createQuery("SELECT p FROM Periodico p WHERE p.issn = '" + issn + "'");
            List<Periodico> listP = query.getResultList();

            if (!listP.isEmpty()) {
                periodico = listP.get(0);
            }
        } finally {
            em.close();
        }

        return periodico;
    }

    /**
     *
     * @return
     */
    private int getPeriodicoCount() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodico> rt = cq.from(Periodico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);

            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
