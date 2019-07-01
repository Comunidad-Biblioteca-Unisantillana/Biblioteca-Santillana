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
import entitys.AutorLibro;
import entitys.AutorPorLibro;
import entitys.Libro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class AutorPorLibroJpaController implements Serializable {

    public AutorPorLibroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutorPorLibro autorPorLibro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorLibro codautorlibro = autorPorLibro.getCodautorlibro();
            if (codautorlibro != null) {
                codautorlibro = em.getReference(codautorlibro.getClass(), codautorlibro.getCodautorlibro());
                autorPorLibro.setCodautorlibro(codautorlibro);
            }
            Libro codbarralibro = autorPorLibro.getCodbarralibro();
            if (codbarralibro != null) {
                codbarralibro = em.getReference(codbarralibro.getClass(), codbarralibro.getCodbarralibro());
                autorPorLibro.setCodbarralibro(codbarralibro);
            }
            em.persist(autorPorLibro);
            if (codautorlibro != null) {
                codautorlibro.getAutorPorLibroList().add(autorPorLibro);
                codautorlibro = em.merge(codautorlibro);
            }
            if (codbarralibro != null) {
                codbarralibro.getAutorPorLibroList().add(autorPorLibro);
                codbarralibro = em.merge(codbarralibro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorPorLibro(autorPorLibro.getCodautlib()) != null) {
                throw new PreexistingEntityException("AutorPorLibro " + autorPorLibro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutorPorLibro autorPorLibro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorPorLibro persistentAutorPorLibro = em.find(AutorPorLibro.class, autorPorLibro.getCodautlib());
            AutorLibro codautorlibroOld = persistentAutorPorLibro.getCodautorlibro();
            AutorLibro codautorlibroNew = autorPorLibro.getCodautorlibro();
            Libro codbarralibroOld = persistentAutorPorLibro.getCodbarralibro();
            Libro codbarralibroNew = autorPorLibro.getCodbarralibro();
            if (codautorlibroNew != null) {
                codautorlibroNew = em.getReference(codautorlibroNew.getClass(), codautorlibroNew.getCodautorlibro());
                autorPorLibro.setCodautorlibro(codautorlibroNew);
            }
            if (codbarralibroNew != null) {
                codbarralibroNew = em.getReference(codbarralibroNew.getClass(), codbarralibroNew.getCodbarralibro());
                autorPorLibro.setCodbarralibro(codbarralibroNew);
            }
            autorPorLibro = em.merge(autorPorLibro);
            if (codautorlibroOld != null && !codautorlibroOld.equals(codautorlibroNew)) {
                codautorlibroOld.getAutorPorLibroList().remove(autorPorLibro);
                codautorlibroOld = em.merge(codautorlibroOld);
            }
            if (codautorlibroNew != null && !codautorlibroNew.equals(codautorlibroOld)) {
                codautorlibroNew.getAutorPorLibroList().add(autorPorLibro);
                codautorlibroNew = em.merge(codautorlibroNew);
            }
            if (codbarralibroOld != null && !codbarralibroOld.equals(codbarralibroNew)) {
                codbarralibroOld.getAutorPorLibroList().remove(autorPorLibro);
                codbarralibroOld = em.merge(codbarralibroOld);
            }
            if (codbarralibroNew != null && !codbarralibroNew.equals(codbarralibroOld)) {
                codbarralibroNew.getAutorPorLibroList().add(autorPorLibro);
                codbarralibroNew = em.merge(codbarralibroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = autorPorLibro.getCodautlib();
                if (findAutorPorLibro(id) == null) {
                    throw new NonexistentEntityException("The autorPorLibro with id " + id + " no longer exists.");
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
            AutorPorLibro autorPorLibro;
            try {
                autorPorLibro = em.getReference(AutorPorLibro.class, id);
                autorPorLibro.getCodautlib();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorPorLibro with id " + id + " no longer exists.", enfe);
            }
            AutorLibro codautorlibro = autorPorLibro.getCodautorlibro();
            if (codautorlibro != null) {
                codautorlibro.getAutorPorLibroList().remove(autorPorLibro);
                codautorlibro = em.merge(codautorlibro);
            }
            Libro codbarralibro = autorPorLibro.getCodbarralibro();
            if (codbarralibro != null) {
                codbarralibro.getAutorPorLibroList().remove(autorPorLibro);
                codbarralibro = em.merge(codbarralibro);
            }
            em.remove(autorPorLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutorPorLibro> findAutorPorLibroEntities() {
        return findAutorPorLibroEntities(true, -1, -1);
    }

    public List<AutorPorLibro> findAutorPorLibroEntities(int maxResults, int firstResult) {
        return findAutorPorLibroEntities(false, maxResults, firstResult);
    }

    private List<AutorPorLibro> findAutorPorLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorPorLibro.class));
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
     * Método que retorna una lista de autores de un libro, se busca por medio del id del libro.
     * @param id
     * @return 
     */
    public List<AutorLibro> findAutorPorLibroCodBarras(String id) {          
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
            EntityManager em = emf.createEntityManager();
            String cons = "SELECT a.codautorlibro " + " from AutorPorLibro a " + "where a.codbarralibro.codbarralibro = '" + id + "'";
            Query query = em.createQuery(cons);
            List<AutorLibro> lista = (List<AutorLibro>)query.getResultList( );
            
            return lista;           
        } catch(Exception e){
            System.out.println("Error en el find autor por libro");
        }
        return null;
    }

    
    public AutorPorLibro findAutorPorLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutorPorLibro.class, id);
        } finally {
            em.close();
        }
    }
    
    
    public int getAutorPorLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorPorLibro> rt = cq.from(AutorPorLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
