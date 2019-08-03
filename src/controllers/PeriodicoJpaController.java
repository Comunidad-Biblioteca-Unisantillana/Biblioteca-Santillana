/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitysRecursos.CategoriaColeccion;
import entitysRecursos.Periodico;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class PeriodicoJpaController implements Serializable {

    public PeriodicoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Periodico periodico) throws PreexistingEntityException, Exception {
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

    public void destroy(String id) throws NonexistentEntityException {
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

    public List<Periodico> findPeriodicoEntities() {
        return findPeriodicoEntities(true, -1, -1);
    }

    public List<Periodico> findPeriodicoEntities(int maxResults, int firstResult) {
        return findPeriodicoEntities(false, maxResults, firstResult);
    }

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

    public Periodico findPeriodico(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodico.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Periodico> findPeriodicoTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Periodico " + "WHERE nombrePeriodico ILIKE '%" + cadena + "%'", Periodico.class);
            List<Periodico> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }

    public int getPeriodicoCount() {
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
