package recursos.controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import recursos.entitys.AutorEnciclopedia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.AutorPorEnciclopedia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 17/08/2019
 */
public class AutorEnciclopediaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     *
     */
    public AutorEnciclopediaJpaController() {
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
     * @param autorEnciclopedia
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(AutorEnciclopedia autorEnciclopedia) throws PreexistingEntityException, Exception {
        if (autorEnciclopedia.getAutorPorEnciclopediaList() == null) {
            autorEnciclopedia.setAutorPorEnciclopediaList(new ArrayList<AutorPorEnciclopedia>());
        }

        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AutorPorEnciclopedia> attachedAutorPorEnciclopediaList = new ArrayList<AutorPorEnciclopedia>();

            for (AutorPorEnciclopedia autorPorEnciclopediaListAutorPorEnciclopediaToAttach : autorEnciclopedia.getAutorPorEnciclopediaList()) {
                autorPorEnciclopediaListAutorPorEnciclopediaToAttach = em.getReference(autorPorEnciclopediaListAutorPorEnciclopediaToAttach.getClass(), autorPorEnciclopediaListAutorPorEnciclopediaToAttach.getCodautenc());
                attachedAutorPorEnciclopediaList.add(autorPorEnciclopediaListAutorPorEnciclopediaToAttach);
            }

            autorEnciclopedia.setAutorPorEnciclopediaList(attachedAutorPorEnciclopediaList);
            em.persist(autorEnciclopedia);

            for (AutorPorEnciclopedia autorPorEnciclopediaListAutorPorEnciclopedia : autorEnciclopedia.getAutorPorEnciclopediaList()) {
                AutorEnciclopedia oldCodautorenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia = autorPorEnciclopediaListAutorPorEnciclopedia.getCodautorenciclopedia();
                autorPorEnciclopediaListAutorPorEnciclopedia.setCodautorenciclopedia(autorEnciclopedia);
                autorPorEnciclopediaListAutorPorEnciclopedia = em.merge(autorPorEnciclopediaListAutorPorEnciclopedia);

                if (oldCodautorenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia != null) {
                    oldCodautorenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopediaListAutorPorEnciclopedia);
                    oldCodautorenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia = em.merge(oldCodautorenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia);
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorEnciclopedia(autorEnciclopedia.getCodautorenciclopedia()) != null) {
                throw new PreexistingEntityException("AutorEnciclopedia " + autorEnciclopedia + " already exists.", ex);
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
     * @param autorEnciclopedia
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    private void edit(AutorEnciclopedia autorEnciclopedia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorEnciclopedia persistentAutorEnciclopedia = em.find(AutorEnciclopedia.class, autorEnciclopedia.getCodautorenciclopedia());
            List<AutorPorEnciclopedia> autorPorEnciclopediaListOld = persistentAutorEnciclopedia.getAutorPorEnciclopediaList();
            List<AutorPorEnciclopedia> autorPorEnciclopediaListNew = autorEnciclopedia.getAutorPorEnciclopediaList();
            List<String> illegalOrphanMessages = null;

            for (AutorPorEnciclopedia autorPorEnciclopediaListOldAutorPorEnciclopedia : autorPorEnciclopediaListOld) {
                if (!autorPorEnciclopediaListNew.contains(autorPorEnciclopediaListOldAutorPorEnciclopedia)) {

                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }

                    illegalOrphanMessages.add("You must retain AutorPorEnciclopedia " + autorPorEnciclopediaListOldAutorPorEnciclopedia + " since its codautorenciclopedia field is not nullable.");
                }
            }

            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }

            List<AutorPorEnciclopedia> attachedAutorPorEnciclopediaListNew = new ArrayList<AutorPorEnciclopedia>();

            for (AutorPorEnciclopedia autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach : autorPorEnciclopediaListNew) {
                autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach = em.getReference(autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach.getClass(), autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach.getCodautenc());
                attachedAutorPorEnciclopediaListNew.add(autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach);
            }

            autorPorEnciclopediaListNew = attachedAutorPorEnciclopediaListNew;
            autorEnciclopedia.setAutorPorEnciclopediaList(autorPorEnciclopediaListNew);
            autorEnciclopedia = em.merge(autorEnciclopedia);

            for (AutorPorEnciclopedia autorPorEnciclopediaListNewAutorPorEnciclopedia : autorPorEnciclopediaListNew) {
                if (!autorPorEnciclopediaListOld.contains(autorPorEnciclopediaListNewAutorPorEnciclopedia)) {
                    AutorEnciclopedia oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia = autorPorEnciclopediaListNewAutorPorEnciclopedia.getCodautorenciclopedia();
                    autorPorEnciclopediaListNewAutorPorEnciclopedia.setCodautorenciclopedia(autorEnciclopedia);
                    autorPorEnciclopediaListNewAutorPorEnciclopedia = em.merge(autorPorEnciclopediaListNewAutorPorEnciclopedia);

                    if (oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia != null && !oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia.equals(autorEnciclopedia)) {
                        oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopediaListNewAutorPorEnciclopedia);
                        oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia = em.merge(oldCodautorenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia);
                    }
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();

            if (msg == null || msg.length() == 0) {
                String id = autorEnciclopedia.getCodautorenciclopedia();

                if (findAutorEnciclopedia(id) == null) {
                    throw new NonexistentEntityException("The autorEnciclopedia with id " + id + " no longer exists.");
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
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     */
    private void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorEnciclopedia autorEnciclopedia;

            try {
                autorEnciclopedia = em.getReference(AutorEnciclopedia.class, id);
                autorEnciclopedia.getCodautorenciclopedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorEnciclopedia with id " + id + " no longer exists.", enfe);
            }

            List<String> illegalOrphanMessages = null;
            List<AutorPorEnciclopedia> autorPorEnciclopediaListOrphanCheck = autorEnciclopedia.getAutorPorEnciclopediaList();

            for (AutorPorEnciclopedia autorPorEnciclopediaListOrphanCheckAutorPorEnciclopedia : autorPorEnciclopediaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }

                illegalOrphanMessages.add("This AutorEnciclopedia (" + autorEnciclopedia + ") cannot be destroyed since the AutorPorEnciclopedia " + autorPorEnciclopediaListOrphanCheckAutorPorEnciclopedia + " in its autorPorEnciclopediaList field has a non-nullable codautorenciclopedia field.");
            }

            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }

            em.remove(autorEnciclopedia);
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
    private List<AutorEnciclopedia> findAutorEnciclopediaEntities() {
        return findAutorEnciclopediaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<AutorEnciclopedia> findAutorEnciclopediaEntities(int maxResults, int firstResult) {
        return findAutorEnciclopediaEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<AutorEnciclopedia> findAutorEnciclopediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorEnciclopedia.class));
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
    private AutorEnciclopedia findAutorEnciclopedia(String id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(AutorEnciclopedia.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * 
     * @return 
     */
    private int getAutorEnciclopediaCount() {
        EntityManager em = getEntityManager();

        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorEnciclopedia> rt = cq.from(AutorEnciclopedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);

            return((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
