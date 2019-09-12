package recursos.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.AutorLibro;
import recursos.entitys.AutorPorLibro;
import recursos.entitys.Libro;
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
public class AutorPorLibroJpaController implements Serializable {

    private EntityManagerFactory emf;
    
    /**
     * 
     */
    public AutorPorLibroJpaController() {
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
     * @param autorPorLibro
     * @throws PreexistingEntityException
     * @throws Exception 
     */
    private void create(AutorPorLibro autorPorLibro) throws PreexistingEntityException, Exception {
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

    /**
     * 
     * @param autorPorLibro
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    private void edit(AutorPorLibro autorPorLibro) throws NonexistentEntityException, Exception {
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

    /**
     * 
     * @return 
     */
    private List<AutorPorLibro> findAutorPorLibroEntities() {
        return findAutorPorLibroEntities(true, -1, -1);
    }

    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<AutorPorLibro> findAutorPorLibroEntities(int maxResults, int firstResult) {
        return findAutorPorLibroEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
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
     * 
     * @param id
     * @return 
     */
    private AutorPorLibro findAutorPorLibro(String id) {
        EntityManager em = getEntityManager();
       
        try {
            return em.find(AutorPorLibro.class, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * el metódo retorna una lista de autores de un libro, por medio del
     * codBarras.
     *
     * @param codBarras
     * @return listaAutlib
     */
    public List<AutorLibro> findAutorPorLibroCodBarras(String codBarras) {          
        EntityManager em = getEntityManager();
        List<AutorLibro> listaAutlib = null;

        try {
            Query query = em.createQuery("SELECT a.codautorlibro FROM AutorPorLibro a "
                    + "WHERE a.codbarralibro.codbarralibro = '" + codBarras + "'");

            listaAutlib = (List<AutorLibro>) query.getResultList();
        } finally {
            em.close();
        }

        return listaAutlib;
    }
    
    /**
     * 
     * @return 
     */
    private int getAutorPorLibroCount() {
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
