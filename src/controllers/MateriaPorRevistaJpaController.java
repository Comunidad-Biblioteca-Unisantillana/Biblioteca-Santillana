/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entitysRecursos.MateriaPorDiccionario;
import entitysRecursos.MateriaPorRevista;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitysRecursos.Revista;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class MateriaPorRevistaJpaController implements Serializable {

    public MateriaPorRevistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPorRevista materiaPorRevista) throws PreexistingEntityException, Exception {
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

    public void edit(MateriaPorRevista materiaPorRevista) throws NonexistentEntityException, Exception {
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

    public void destroy(String id) throws NonexistentEntityException {
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

    public List<MateriaPorRevista> findMateriaPorRevistaEntities() {
        return findMateriaPorRevistaEntities(true, -1, -1);
    }

    public List<MateriaPorRevista> findMateriaPorRevistaEntities(int maxResults, int firstResult) {
        return findMateriaPorRevistaEntities(false, maxResults, firstResult);
    }

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
     * Método que retorna una lista de materias de una revisa, se busca por medio del id de la revista.
     * @param id
     * @return 
     */
    public List<MateriaPorRevista> findMateriaPorRevistaCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT m " + " FROM MateriaPorRevista m " + "WHERE m.codbarrarevista.codbarrarevista = '" + id + "'";
            Query query = em.createQuery(cons);
            List<MateriaPorRevista> lista = (List<MateriaPorRevista>)query.getResultList( );
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find materia por revista");
        }
        return null;
    }

    public MateriaPorRevista findMateriaPorRevista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPorRevista.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPorRevistaCount() {
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
