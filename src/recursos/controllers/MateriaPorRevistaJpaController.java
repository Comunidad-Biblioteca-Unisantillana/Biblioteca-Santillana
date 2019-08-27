package recursos.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import recursos.entitys.MateriaPorRevista;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.Revista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import recursos.entitys.Materia;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class MateriaPorRevistaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     *
     */
    public MateriaPorRevistaJpaController() {
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
     * @param materiaPorRevista
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(MateriaPorRevista materiaPorRevista) throws PreexistingEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Revista codbarrarevista = materiaPorRevista.getCodbarrarevista();

            if (codbarrarevista != null) {
                codbarrarevista = em.getReference(codbarrarevista.getClass(), codbarrarevista.getCodbarrarevista());
                materiaPorRevista.setCodbarrarevista(codbarrarevista);
            }

            em.persist(materiaPorRevista);

            if (codbarrarevista != null) {
                codbarrarevista.getMateriaPorRevistaList().add(materiaPorRevista);
                codbarrarevista = em.merge(codbarrarevista);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaPorRevista(materiaPorRevista.getCodmateriarevista()) != null) {
                throw new PreexistingEntityException("MateriaPorRevista " + materiaPorRevista + " already exists.", ex);
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
     * @param materiaPorRevista
     * @throws NonexistentEntityException
     * @throws Exception
     */
    private void edit(MateriaPorRevista materiaPorRevista) throws NonexistentEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPorRevista persistentMateriaPorRevista = em.find(MateriaPorRevista.class, materiaPorRevista.getCodmateriarevista());
            Revista codbarrarevistaOld = persistentMateriaPorRevista.getCodbarrarevista();
            Revista codbarrarevistaNew = materiaPorRevista.getCodbarrarevista();

            if (codbarrarevistaNew != null) {
                codbarrarevistaNew = em.getReference(codbarrarevistaNew.getClass(), codbarrarevistaNew.getCodbarrarevista());
                materiaPorRevista.setCodbarrarevista(codbarrarevistaNew);
            }

            materiaPorRevista = em.merge(materiaPorRevista);

            if (codbarrarevistaOld != null && !codbarrarevistaOld.equals(codbarrarevistaNew)) {
                codbarrarevistaOld.getMateriaPorRevistaList().remove(materiaPorRevista);
                codbarrarevistaOld = em.merge(codbarrarevistaOld);
            }

            if (codbarrarevistaNew != null && !codbarrarevistaNew.equals(codbarrarevistaOld)) {
                codbarrarevistaNew.getMateriaPorRevistaList().add(materiaPorRevista);
                codbarrarevistaNew = em.merge(codbarrarevistaNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                String id = materiaPorRevista.getCodmateriarevista();

                if (findMateriaPorRevista(id) == null) {
                    throw new NonexistentEntityException("The materiaPorRevista with id " + id + " no longer exists.");
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
            MateriaPorRevista materiaPorRevista;

            try {
                materiaPorRevista = em.getReference(MateriaPorRevista.class, id);
                materiaPorRevista.getCodmateriarevista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPorRevista with id " + id + " no longer exists.", enfe);
            }

            Revista codbarrarevista = materiaPorRevista.getCodbarrarevista();

            if (codbarrarevista != null) {
                codbarrarevista.getMateriaPorRevistaList().remove(materiaPorRevista);
                codbarrarevista = em.merge(codbarrarevista);
            }

            em.remove(materiaPorRevista);
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
    private List<MateriaPorRevista> findMateriaPorRevistaEntities() {
        return findMateriaPorRevistaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<MateriaPorRevista> findMateriaPorRevistaEntities(int maxResults, int firstResult) {
        return findMateriaPorRevistaEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<MateriaPorRevista> findMateriaPorRevistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPorRevista.class));
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
    private MateriaPorRevista findMateriaPorRevista(String id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(MateriaPorRevista.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Método que retorna una lista de materias de una revista, se busca por
     * medio del codBarras.
     *
     * @param codBarras
     * @return listaMat
     */
    public List<Materia> findMateriaPorRevistaCodBarras(String codBarras) {
        EntityManager em = getEntityManager();
        List<Materia> listaMat = null;

        try {
            Query query = em.createQuery("SELECT m.codmateria FROM MateriaPorRevista m "
                    + "WHERE m.codbarrarevista.codbarrarevista = '" + codBarras + "'");

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
    private int getMateriaPorRevistaCount() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPorRevista> rt = cq.from(MateriaPorRevista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);

            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
