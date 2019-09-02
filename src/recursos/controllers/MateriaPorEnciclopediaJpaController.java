package recursos.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import recursos.entitys.MateriaPorEnciclopedia;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.Materia;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class MateriaPorEnciclopediaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     *
     */
    public MateriaPorEnciclopediaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     *
     * @return
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     *
     * @param materiaPorEnciclopedia
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(MateriaPorEnciclopedia materiaPorEnciclopedia) throws PreexistingEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(materiaPorEnciclopedia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaPorEnciclopedia(materiaPorEnciclopedia.getCodmateriaenciclopedia()) != null) {
                throw new PreexistingEntityException("MateriaPorEnciclopedia " + materiaPorEnciclopedia + " already exists.", ex);
            }

            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     *
     * @param materiaPorEnciclopedia
     * @throws NonexistentEntityException
     * @throws Exception
     */
    private void edit(MateriaPorEnciclopedia materiaPorEnciclopedia) throws NonexistentEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            materiaPorEnciclopedia = em.merge(materiaPorEnciclopedia);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                String id = materiaPorEnciclopedia.getCodmateriaenciclopedia();

                if (findMateriaPorEnciclopedia(id) == null) {
                    throw new NonexistentEntityException("The materiaPorEnciclopedia with id " + id + " no longer exists.");
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
     *
     * @param id
     * @throws NonexistentEntityException
     */
    private void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPorEnciclopedia materiaPorEnciclopedia;

            try {
                materiaPorEnciclopedia = em.getReference(MateriaPorEnciclopedia.class, id);
                materiaPorEnciclopedia.getCodmateriaenciclopedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPorEnciclopedia with id " + id + " no longer exists.", enfe);
            }

            em.remove(materiaPorEnciclopedia);
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
    private List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaEntities() {
        return findMateriaPorEnciclopediaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaEntities(int maxResults, int firstResult) {
        return findMateriaPorEnciclopediaEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPorEnciclopedia.class));
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
     *
     * @param id
     * @return
     */
    private MateriaPorEnciclopedia findMateriaPorEnciclopedia(String id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(MateriaPorEnciclopedia.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Método que retorna una lista de materias de una enciclopedia, se busca
     * por medio del codBarras.
     *
     * @param codBarras
     * @return listaMat
     */
    public List<Materia> findMateriaPorEnciclopediaCodBarras(String codBarras) {
        EntityManager em = getEntityManager();
        List<Materia> listaMat = null;

        try {
            Query query = em.createQuery("SELECT m.codmateria FROM MateriaPorEnciclopedia m "
                    + "WHERE m.codbarraenciclopedia.codbarraenciclopedia = '" + codBarras + "'");

            listaMat = (List<Materia>) query.getResultList();
        } finally {
            em.close();
        }

        return listaMat;
    }

    /**
     *
     * @return
     */
    private int getMateriaPorEnciclopediaCount() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPorEnciclopedia> rt = cq.from(MateriaPorEnciclopedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);

            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
