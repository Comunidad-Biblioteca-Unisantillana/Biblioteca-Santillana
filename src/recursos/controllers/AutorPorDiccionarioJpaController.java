package recursos.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.AutorDiccionario;
import recursos.entitys.AutorPorDiccionario;
import recursos.entitys.Diccionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class AutorPorDiccionarioJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     *
     */
    public AutorPorDiccionarioJpaController() {
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
     * @param autorPorDiccionario
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(AutorPorDiccionario autorPorDiccionario) throws PreexistingEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorDiccionario codautordiccionario = autorPorDiccionario.getCodautordiccionario();

            if (codautordiccionario != null) {
                codautordiccionario = em.getReference(codautordiccionario.getClass(), codautordiccionario.getCodautordiccionario());
                autorPorDiccionario.setCodautordiccionario(codautordiccionario);
            }

            Diccionario codbarradiccionario = autorPorDiccionario.getCodbarradiccionario();

            if (codbarradiccionario != null) {
                codbarradiccionario = em.getReference(codbarradiccionario.getClass(), codbarradiccionario.getCodbarradiccionario());
                autorPorDiccionario.setCodbarradiccionario(codbarradiccionario);
            }

            em.persist(autorPorDiccionario);

            if (codautordiccionario != null) {
                codautordiccionario.getAutorPorDiccionarioList().add(autorPorDiccionario);
                codautordiccionario = em.merge(codautordiccionario);
            }

            if (codbarradiccionario != null) {
                codbarradiccionario.getAutorPorDiccionarioList().add(autorPorDiccionario);
                codbarradiccionario = em.merge(codbarradiccionario);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorPorDiccionario(autorPorDiccionario.getCodautdic()) != null) {
                throw new PreexistingEntityException("AutorPorDiccionario " + autorPorDiccionario + " already exists.", ex);
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
     * @param autorPorDiccionario
     * @throws NonexistentEntityException
     * @throws Exception
     */
    private void edit(AutorPorDiccionario autorPorDiccionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorPorDiccionario persistentAutorPorDiccionario = em.find(AutorPorDiccionario.class, autorPorDiccionario.getCodautdic());
            AutorDiccionario codautordiccionarioOld = persistentAutorPorDiccionario.getCodautordiccionario();
            AutorDiccionario codautordiccionarioNew = autorPorDiccionario.getCodautordiccionario();
            Diccionario codbarradiccionarioOld = persistentAutorPorDiccionario.getCodbarradiccionario();
            Diccionario codbarradiccionarioNew = autorPorDiccionario.getCodbarradiccionario();

            if (codautordiccionarioNew != null) {
                codautordiccionarioNew = em.getReference(codautordiccionarioNew.getClass(), codautordiccionarioNew.getCodautordiccionario());
                autorPorDiccionario.setCodautordiccionario(codautordiccionarioNew);
            }

            if (codbarradiccionarioNew != null) {
                codbarradiccionarioNew = em.getReference(codbarradiccionarioNew.getClass(), codbarradiccionarioNew.getCodbarradiccionario());
                autorPorDiccionario.setCodbarradiccionario(codbarradiccionarioNew);
            }

            autorPorDiccionario = em.merge(autorPorDiccionario);

            if (codautordiccionarioOld != null && !codautordiccionarioOld.equals(codautordiccionarioNew)) {
                codautordiccionarioOld.getAutorPorDiccionarioList().remove(autorPorDiccionario);
                codautordiccionarioOld = em.merge(codautordiccionarioOld);
            }
            if (codautordiccionarioNew != null && !codautordiccionarioNew.equals(codautordiccionarioOld)) {
                codautordiccionarioNew.getAutorPorDiccionarioList().add(autorPorDiccionario);
                codautordiccionarioNew = em.merge(codautordiccionarioNew);
            }

            if (codbarradiccionarioOld != null && !codbarradiccionarioOld.equals(codbarradiccionarioNew)) {
                codbarradiccionarioOld.getAutorPorDiccionarioList().remove(autorPorDiccionario);
                codbarradiccionarioOld = em.merge(codbarradiccionarioOld);
            }

            if (codbarradiccionarioNew != null && !codbarradiccionarioNew.equals(codbarradiccionarioOld)) {
                codbarradiccionarioNew.getAutorPorDiccionarioList().add(autorPorDiccionario);
                codbarradiccionarioNew = em.merge(codbarradiccionarioNew);
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                String id = autorPorDiccionario.getCodautdic();

                if (findAutorPorDiccionario(id) == null) {
                    throw new NonexistentEntityException("The autorPorDiccionario with id " + id + " no longer exists.");
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
            AutorPorDiccionario autorPorDiccionario;

            try {
                autorPorDiccionario = em.getReference(AutorPorDiccionario.class, id);
                autorPorDiccionario.getCodautdic();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorPorDiccionario with id " + id + " no longer exists.", enfe);
            }

            AutorDiccionario codautordiccionario = autorPorDiccionario.getCodautordiccionario();

            if (codautordiccionario != null) {
                codautordiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionario);
                codautordiccionario = em.merge(codautordiccionario);
            }

            Diccionario codbarradiccionario = autorPorDiccionario.getCodbarradiccionario();

            if (codbarradiccionario != null) {
                codbarradiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionario);
                codbarradiccionario = em.merge(codbarradiccionario);
            }

            em.remove(autorPorDiccionario);
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
    private List<AutorPorDiccionario> findAutorPorDiccionarioEntities() {
        return findAutorPorDiccionarioEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<AutorPorDiccionario> findAutorPorDiccionarioEntities(int maxResults, int firstResult) {
        return findAutorPorDiccionarioEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<AutorPorDiccionario> findAutorPorDiccionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorPorDiccionario.class));
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
     */
    private AutorPorDiccionario findAutorPorDiccionario(String id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(AutorPorDiccionario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna una lista de autores de un diccionario, por medio del
     * codBarras.
     *
     * @param codBarras
     * @return listaAutDic
     */
    public List<AutorDiccionario> findAutorPorDiccionarioCodBarras(String codBarras) {
        EntityManager em = getEntityManager();
        List<AutorDiccionario> listaAutDic = null;

        try {
            Query query = em.createQuery("SELECT a.codautordiccionario FROM AutorPorDiccionario a "
                    + "WHERE a.codbarradiccionario.codbarradiccionario = '" + codBarras + "'");

            listaAutDic = (List<AutorDiccionario>) query.getResultList();
        } finally {
            em.close();
        }

        return listaAutDic;
    }

    /**
     *
     * @return
     */
    private int getAutorPorDiccionarioCount() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorPorDiccionario> rt = cq.from(AutorPorDiccionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);

            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
