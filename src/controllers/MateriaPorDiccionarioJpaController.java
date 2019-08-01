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
import entitysRecursos.Diccionario;
import entitysRecursos.MateriaPorDiccionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class MateriaPorDiccionarioJpaController implements Serializable {

    public MateriaPorDiccionarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPorDiccionario materiaPorDiccionario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diccionario codbarradiccionario = materiaPorDiccionario.getCodbarradiccionario();
            if (codbarradiccionario != null) {
                codbarradiccionario = em.getReference(codbarradiccionario.getClass(), codbarradiccionario.getCodbarradiccionario());
                materiaPorDiccionario.setCodbarradiccionario(codbarradiccionario);
            }
            em.persist(materiaPorDiccionario);
            if (codbarradiccionario != null) {
                codbarradiccionario.getMateriaPorDiccionarioList().add(materiaPorDiccionario);
                codbarradiccionario = em.merge(codbarradiccionario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaPorDiccionario(materiaPorDiccionario.getCodmateriadiccionario()) != null) {
                throw new PreexistingEntityException("MateriaPorDiccionario " + materiaPorDiccionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaPorDiccionario materiaPorDiccionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPorDiccionario persistentMateriaPorDiccionario = em.find(MateriaPorDiccionario.class, materiaPorDiccionario.getCodmateriadiccionario());
            Diccionario codbarradiccionarioOld = persistentMateriaPorDiccionario.getCodbarradiccionario();
            Diccionario codbarradiccionarioNew = materiaPorDiccionario.getCodbarradiccionario();
            if (codbarradiccionarioNew != null) {
                codbarradiccionarioNew = em.getReference(codbarradiccionarioNew.getClass(), codbarradiccionarioNew.getCodbarradiccionario());
                materiaPorDiccionario.setCodbarradiccionario(codbarradiccionarioNew);
            }
            materiaPorDiccionario = em.merge(materiaPorDiccionario);
            if (codbarradiccionarioOld != null && !codbarradiccionarioOld.equals(codbarradiccionarioNew)) {
                codbarradiccionarioOld.getMateriaPorDiccionarioList().remove(materiaPorDiccionario);
                codbarradiccionarioOld = em.merge(codbarradiccionarioOld);
            }
            if (codbarradiccionarioNew != null && !codbarradiccionarioNew.equals(codbarradiccionarioOld)) {
                codbarradiccionarioNew.getMateriaPorDiccionarioList().add(materiaPorDiccionario);
                codbarradiccionarioNew = em.merge(codbarradiccionarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = materiaPorDiccionario.getCodmateriadiccionario();
                if (findMateriaPorDiccionario(id) == null) {
                    throw new NonexistentEntityException("The materiaPorDiccionario with id " + id + " no longer exists.");
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
            MateriaPorDiccionario materiaPorDiccionario;
            try {
                materiaPorDiccionario = em.getReference(MateriaPorDiccionario.class, id);
                materiaPorDiccionario.getCodmateriadiccionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPorDiccionario with id " + id + " no longer exists.", enfe);
            }
            Diccionario codbarradiccionario = materiaPorDiccionario.getCodbarradiccionario();
            if (codbarradiccionario != null) {
                codbarradiccionario.getMateriaPorDiccionarioList().remove(materiaPorDiccionario);
                codbarradiccionario = em.merge(codbarradiccionario);
            }
            em.remove(materiaPorDiccionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaPorDiccionario> findMateriaPorDiccionarioEntities() {
        return findMateriaPorDiccionarioEntities(true, -1, -1);
    }

    public List<MateriaPorDiccionario> findMateriaPorDiccionarioEntities(int maxResults, int firstResult) {
        return findMateriaPorDiccionarioEntities(false, maxResults, firstResult);
    }

    private List<MateriaPorDiccionario> findMateriaPorDiccionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPorDiccionario.class));
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
     * Método que retorna una lista de materias de un Diccionario, se busca por medio del id de Diccionario.
     * @param id
     * @return 
     */
    public List<MateriaPorDiccionario> findMateriaPorDiccionarioCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT m " + " FROM MateriaPorDiccionario m " + "WHERE m.codbarradiccionario.codbarradiccionario = '" + id + "'";
            Query query = em.createQuery(cons);
            List<MateriaPorDiccionario> lista = (List<MateriaPorDiccionario>)query.getResultList( );
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find materia por diccionario");
        }
        return null;
    }

    public MateriaPorDiccionario findMateriaPorDiccionario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPorDiccionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPorDiccionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPorDiccionario> rt = cq.from(MateriaPorDiccionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
