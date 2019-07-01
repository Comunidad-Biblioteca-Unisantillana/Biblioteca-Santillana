/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import entitys.MateriaPorEnciclopedia;
import entitys.MateriaPorLibro;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Camilo
 */
public class MateriaPorEnciclopediaJpaController implements Serializable {

    public MateriaPorEnciclopediaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPorEnciclopedia materiaPorEnciclopedia) throws PreexistingEntityException, Exception {
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

    public void edit(MateriaPorEnciclopedia materiaPorEnciclopedia) throws NonexistentEntityException, Exception {
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

    public void destroy(String id) throws NonexistentEntityException {
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

    public List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaEntities() {
        return findMateriaPorEnciclopediaEntities(true, -1, -1);
    }

    public List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaEntities(int maxResults, int firstResult) {
        return findMateriaPorEnciclopediaEntities(false, maxResults, firstResult);
    }

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
     * Método que retorna una lista de materias de una enciclopedia, se busca por medio del id de la enciclopedia.
     * @param id
     * @return 
     */
    public List<MateriaPorEnciclopedia> findMateriaPorEnciclopediaCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT m " + " FROM MateriaPorEnciclopedia m " + "WHERE m.codbarraenciclopedia.codbarraenciclopedia = '" + id + "'";
            Query query = em.createQuery(cons);
            List<MateriaPorEnciclopedia> lista = (List<MateriaPorEnciclopedia>)query.getResultList( );
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find materia por enciclopedia");
        }
        return null;
    }
    
    public MateriaPorEnciclopedia findMateriaPorEnciclopedia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPorEnciclopedia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPorEnciclopediaCount() {
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
