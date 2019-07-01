/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitys.AutorEnciclopedia;
import entitys.AutorPorEnciclopedia;
import entitys.Enciclopedia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class AutorPorEnciclopediaJpaController implements Serializable {

    public AutorPorEnciclopediaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutorPorEnciclopedia autorPorEnciclopedia) throws PreexistingEntityException, Exception {
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

    public void edit(AutorPorEnciclopedia autorPorEnciclopedia) throws NonexistentEntityException, Exception {
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

    public void destroy(String id) throws NonexistentEntityException {
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

    public List<AutorPorEnciclopedia> findAutorPorEnciclopediaEntities() {
        return findAutorPorEnciclopediaEntities(true, -1, -1);
    }

    public List<AutorPorEnciclopedia> findAutorPorEnciclopediaEntities(int maxResults, int firstResult) {
        return findAutorPorEnciclopediaEntities(false, maxResults, firstResult);
    }

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
     * Método que retorna una lista de autores de una Enciclopedia, se busca por medio del id de la Enciclopedia.
     * @param id
     * @return 
     */
    public List<AutorEnciclopedia> findAutorPorEnciclopediaCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT a.codautorenciclopedia " + " from AutorPorEnciclopedia a " + "where a.codbarraenciclopedia.codbarraenciclopedia = '" + id + "'";
            Query query = em.createQuery(cons);
            List<AutorEnciclopedia> lista = (List<AutorEnciclopedia>)query.getResultList( );
            
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find autor por enciclopedia");
        }
        return null;
    }

    public AutorPorEnciclopedia findAutorPorEnciclopedia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutorPorEnciclopedia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorPorEnciclopediaCount() {
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
