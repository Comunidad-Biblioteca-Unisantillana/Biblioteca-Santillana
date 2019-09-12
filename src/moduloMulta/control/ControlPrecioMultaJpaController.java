/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloMulta.control;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import moduloMulta.entitys.ControlPrecioMulta;

/**
 *
 * @author Storkolm
 */
public class ControlPrecioMultaJpaController implements Serializable {

    public ControlPrecioMultaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ControlPrecioMulta controlPrecioMulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(controlPrecioMulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ControlPrecioMulta controlPrecioMulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            controlPrecioMulta = em.merge(controlPrecioMulta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = controlPrecioMulta.getCodpreciomulta();
                if (findControlPrecioMulta(id) == null) {
                    throw new NonexistentEntityException("The controlPrecioMulta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ControlPrecioMulta controlPrecioMulta;
            try {
                controlPrecioMulta = em.getReference(ControlPrecioMulta.class, id);
                controlPrecioMulta.getCodpreciomulta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The controlPrecioMulta with id " + id + " no longer exists.", enfe);
            }
            em.remove(controlPrecioMulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ControlPrecioMulta> findControlPrecioMultaEntities() {
        return findControlPrecioMultaEntities(true, -1, -1);
    }

    public List<ControlPrecioMulta> findControlPrecioMultaEntities(int maxResults, int firstResult) {
        return findControlPrecioMultaEntities(false, maxResults, firstResult);
    }

    private List<ControlPrecioMulta> findControlPrecioMultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ControlPrecioMulta.class));
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

    public ControlPrecioMulta findControlPrecioMulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ControlPrecioMulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getControlPrecioMultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ControlPrecioMulta> rt = cq.from(ControlPrecioMulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
