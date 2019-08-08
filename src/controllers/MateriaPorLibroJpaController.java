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
import entitys.Libro;
import entitys.MateriaPorLibro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class MateriaPorLibroJpaController implements Serializable {

    public MateriaPorLibroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MateriaPorLibro materiaPorLibro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro codbarralibro = materiaPorLibro.getCodbarralibro();
            if (codbarralibro != null) {
                codbarralibro = em.getReference(codbarralibro.getClass(), codbarralibro.getCodbarralibro());
                materiaPorLibro.setCodbarralibro(codbarralibro);
            }
            em.persist(materiaPorLibro);
            if (codbarralibro != null) {
                codbarralibro.getMateriaPorLibroList().add(materiaPorLibro);
                codbarralibro = em.merge(codbarralibro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateriaPorLibro(materiaPorLibro.getCodmaterialibro()) != null) {
                throw new PreexistingEntityException("MateriaPorLibro " + materiaPorLibro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MateriaPorLibro materiaPorLibro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MateriaPorLibro persistentMateriaPorLibro = em.find(MateriaPorLibro.class, materiaPorLibro.getCodmaterialibro());
            Libro codbarralibroOld = persistentMateriaPorLibro.getCodbarralibro();
            Libro codbarralibroNew = materiaPorLibro.getCodbarralibro();
            if (codbarralibroNew != null) {
                codbarralibroNew = em.getReference(codbarralibroNew.getClass(), codbarralibroNew.getCodbarralibro());
                materiaPorLibro.setCodbarralibro(codbarralibroNew);
            }
            materiaPorLibro = em.merge(materiaPorLibro);
            if (codbarralibroOld != null && !codbarralibroOld.equals(codbarralibroNew)) {
                codbarralibroOld.getMateriaPorLibroList().remove(materiaPorLibro);
                codbarralibroOld = em.merge(codbarralibroOld);
            }
            if (codbarralibroNew != null && !codbarralibroNew.equals(codbarralibroOld)) {
                codbarralibroNew.getMateriaPorLibroList().add(materiaPorLibro);
                codbarralibroNew = em.merge(codbarralibroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = materiaPorLibro.getCodmaterialibro();
                if (findMateriaPorLibro(id) == null) {
                    throw new NonexistentEntityException("The materiaPorLibro with id " + id + " no longer exists.");
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
            MateriaPorLibro materiaPorLibro;
            try {
                materiaPorLibro = em.getReference(MateriaPorLibro.class, id);
                materiaPorLibro.getCodmaterialibro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materiaPorLibro with id " + id + " no longer exists.", enfe);
            }
            Libro codbarralibro = materiaPorLibro.getCodbarralibro();
            if (codbarralibro != null) {
                codbarralibro.getMateriaPorLibroList().remove(materiaPorLibro);
                codbarralibro = em.merge(codbarralibro);
            }
            em.remove(materiaPorLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MateriaPorLibro> findMateriaPorLibroEntities() {
        return findMateriaPorLibroEntities(true, -1, -1);
    }

    public List<MateriaPorLibro> findMateriaPorLibroEntities(int maxResults, int firstResult) {
        return findMateriaPorLibroEntities(false, maxResults, firstResult);
    }

    private List<MateriaPorLibro> findMateriaPorLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MateriaPorLibro.class));
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
     * Método que retorna una lista de materias de un libro, se busca por medio del id del libro.
     * @param id
     * @return 
     */
    public List<MateriaPorLibro> findMateriaPorLibroCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT m " + " FROM MateriaPorLibro m " + "WHERE m.codbarralibro.codbarralibro = '" + id + "'";
            Query query = em.createQuery(cons);
            List<MateriaPorLibro> lista = (List<MateriaPorLibro>)query.getResultList( );
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find materia por libro");
        }
        return null;
    }

    public MateriaPorLibro findMateriaPorLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MateriaPorLibro.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaPorLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MateriaPorLibro> rt = cq.from(MateriaPorLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
