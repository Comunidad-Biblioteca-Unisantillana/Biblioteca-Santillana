/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.control;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import usuarios.entitys.Bibliotecario;

/**
 *
 * @author Storkolm
 */
public class BibliotecarioJpaController implements Serializable {

    public BibliotecarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bibliotecario bibliotecario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bibliotecario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBibliotecario(bibliotecario.getIdbibliotecario()) != null) {
                throw new PreexistingEntityException("Bibliotecario " + bibliotecario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bibliotecario bibliotecario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bibliotecario = em.merge(bibliotecario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = bibliotecario.getIdbibliotecario();
                if (findBibliotecario(id) == null) {
                    throw new NonexistentEntityException("The bibliotecario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bibliotecario bibliotecario;
            try {
                bibliotecario = em.getReference(Bibliotecario.class, id);
                bibliotecario.getIdbibliotecario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bibliotecario with id " + id + " no longer exists.", enfe);
            }
            em.remove(bibliotecario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bibliotecario> findBibliotecarioEntities() {
        return findBibliotecarioEntities(true, -1, -1);
    }

    public List<Bibliotecario> findBibliotecarioEntities(int maxResults, int firstResult) {
        return findBibliotecarioEntities(false, maxResults, firstResult);
    }

    private List<Bibliotecario> findBibliotecarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bibliotecario.class));
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

    public Bibliotecario findBibliotecario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bibliotecario.class, id);
        } finally {
            em.close();
        }
    }

    public int getBibliotecarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bibliotecario> rt = cq.from(Bibliotecario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
