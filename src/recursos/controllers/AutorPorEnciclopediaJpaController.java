package recursos.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.AutorEnciclopedia;
import recursos.entitys.AutorPorEnciclopedia;
import recursos.entitys.Enciclopedia;
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
public class AutorPorEnciclopediaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * 
     */
    public AutorPorEnciclopediaJpaController() {
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
     * @param autorPorEnciclopedia
     * @throws PreexistingEntityException
     * @throws Exception 
     */
    private void create(AutorPorEnciclopedia autorPorEnciclopedia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorEnciclopedia codautorenciclopedia = autorPorEnciclopedia.getCodautorenciclopedia();
            
            if (codautorenciclopedia != null) {
                codautorenciclopedia = em.getReference(codautorenciclopedia.getClass(), codautorenciclopedia.getCodautorenciclopedia());
                autorPorEnciclopedia.setCodautorenciclopedia(codautorenciclopedia);
            }
            
            Enciclopedia codbarraenciclopedia = autorPorEnciclopedia.getCodbarraenciclopedia();
            
            if (codbarraenciclopedia != null) {
                codbarraenciclopedia = em.getReference(codbarraenciclopedia.getClass(), codbarraenciclopedia.getCodbarraenciclopedia());
                autorPorEnciclopedia.setCodbarraenciclopedia(codbarraenciclopedia);
            }
            
            em.persist(autorPorEnciclopedia);
            
            if (codautorenciclopedia != null) {
                codautorenciclopedia.getAutorPorEnciclopediaList().add(autorPorEnciclopedia);
                codautorenciclopedia = em.merge(codautorenciclopedia);
            }
            
            if (codbarraenciclopedia != null) {
                codbarraenciclopedia.getAutorPorEnciclopediaList().add(autorPorEnciclopedia);
                codbarraenciclopedia = em.merge(codbarraenciclopedia);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorPorEnciclopedia(autorPorEnciclopedia.getCodautenc()) != null) {
                throw new PreexistingEntityException("AutorPorEnciclopedia " + autorPorEnciclopedia + " already exists.", ex);
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
     * @param autorPorEnciclopedia
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    private void edit(AutorPorEnciclopedia autorPorEnciclopedia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorPorEnciclopedia persistentAutorPorEnciclopedia = em.find(AutorPorEnciclopedia.class, autorPorEnciclopedia.getCodautenc());
            AutorEnciclopedia codautorenciclopediaOld = persistentAutorPorEnciclopedia.getCodautorenciclopedia();
            AutorEnciclopedia codautorenciclopediaNew = autorPorEnciclopedia.getCodautorenciclopedia();
            Enciclopedia codbarraenciclopediaOld = persistentAutorPorEnciclopedia.getCodbarraenciclopedia();
            Enciclopedia codbarraenciclopediaNew = autorPorEnciclopedia.getCodbarraenciclopedia();
            
            if (codautorenciclopediaNew != null) {
                codautorenciclopediaNew = em.getReference(codautorenciclopediaNew.getClass(), codautorenciclopediaNew.getCodautorenciclopedia());
                autorPorEnciclopedia.setCodautorenciclopedia(codautorenciclopediaNew);
            }
            
            if (codbarraenciclopediaNew != null) {
                codbarraenciclopediaNew = em.getReference(codbarraenciclopediaNew.getClass(), codbarraenciclopediaNew.getCodbarraenciclopedia());
                autorPorEnciclopedia.setCodbarraenciclopedia(codbarraenciclopediaNew);
            }
            
            autorPorEnciclopedia = em.merge(autorPorEnciclopedia);
            
            if (codautorenciclopediaOld != null && !codautorenciclopediaOld.equals(codautorenciclopediaNew)) {
                codautorenciclopediaOld.getAutorPorEnciclopediaList().remove(autorPorEnciclopedia);
                codautorenciclopediaOld = em.merge(codautorenciclopediaOld);
            }
            
            if (codautorenciclopediaNew != null && !codautorenciclopediaNew.equals(codautorenciclopediaOld)) {
                codautorenciclopediaNew.getAutorPorEnciclopediaList().add(autorPorEnciclopedia);
                codautorenciclopediaNew = em.merge(codautorenciclopediaNew);
            }
            
            if (codbarraenciclopediaOld != null && !codbarraenciclopediaOld.equals(codbarraenciclopediaNew)) {
                codbarraenciclopediaOld.getAutorPorEnciclopediaList().remove(autorPorEnciclopedia);
                codbarraenciclopediaOld = em.merge(codbarraenciclopediaOld);
            }
            
            if (codbarraenciclopediaNew != null && !codbarraenciclopediaNew.equals(codbarraenciclopediaOld)) {
                codbarraenciclopediaNew.getAutorPorEnciclopediaList().add(autorPorEnciclopedia);
                codbarraenciclopediaNew = em.merge(codbarraenciclopediaNew);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            
            if (msg == null || msg.length() == 0) {
                String id = autorPorEnciclopedia.getCodautenc();
                
                if (findAutorPorEnciclopedia(id) == null) {
                    throw new NonexistentEntityException("The autorPorEnciclopedia with id " + id + " no longer exists.");
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
            AutorPorEnciclopedia autorPorEnciclopedia;
            
            try {
                autorPorEnciclopedia = em.getReference(AutorPorEnciclopedia.class, id);
                autorPorEnciclopedia.getCodautenc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorPorEnciclopedia with id " + id + " no longer exists.", enfe);
            }
            
            AutorEnciclopedia codautorenciclopedia = autorPorEnciclopedia.getCodautorenciclopedia();
            
            if (codautorenciclopedia != null) {
                codautorenciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopedia);
                codautorenciclopedia = em.merge(codautorenciclopedia);
            }
            
            Enciclopedia codbarraenciclopedia = autorPorEnciclopedia.getCodbarraenciclopedia();
            
            if (codbarraenciclopedia != null) {
                codbarraenciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopedia);
                codbarraenciclopedia = em.merge(codbarraenciclopedia);
            }
            
            em.remove(autorPorEnciclopedia);
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
    private List<AutorPorEnciclopedia> findAutorPorEnciclopediaEntities() {
        return findAutorPorEnciclopediaEntities(true, -1, -1);
    }
    
    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<AutorPorEnciclopedia> findAutorPorEnciclopediaEntities(int maxResults, int firstResult) {
        return findAutorPorEnciclopediaEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<AutorPorEnciclopedia> findAutorPorEnciclopediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorPorEnciclopedia.class));
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
    private AutorPorEnciclopedia findAutorPorEnciclopedia(String id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(AutorPorEnciclopedia.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna una lista de autores de una enciclopedia, por medio del
     * codBarras.
     *
     * @param codBarras
     * @return listaAutEnc
     */
    public List<AutorEnciclopedia> findAutorPorEnciclopediaCodBarras(String codBarras) {
        EntityManager em = getEntityManager();
        List<AutorEnciclopedia> listaAutEnc = null;

        try {
            Query query = em.createQuery("SELECT a.codautorenciclopedia FROM AutorPorEnciclopedia a "
                    + "WHERE a.codbarraenciclopedia.codbarraenciclopedia = '" + codBarras + "'");

            listaAutEnc = (List<AutorEnciclopedia>) query.getResultList();
        } finally {
            em.close();
        }

        return listaAutEnc;
    }
    
    /**
     * 
     * @return 
     */
    private int getAutorPorEnciclopediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorPorEnciclopedia> rt = cq.from(AutorPorEnciclopedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
