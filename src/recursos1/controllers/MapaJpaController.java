package recursos1.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.CategoriaColeccion;
import recursos1.entitys.Mapa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 19/08/2019
 */
public class MapaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * constructos sin parámetros.
     */
    public MapaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * el metódo retorna la conexión con la base de datos.
     *
     * @return em
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();    // -> em
    }

    /**
     * el metódo añade un nuevo mapa en la base de datos.
     *
     * @param mapa
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(Mapa mapa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = mapa.getCodcategoriacoleccion();
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                mapa.setCodcategoriacoleccion(codcategoriacoleccion);
            }
            
            em.persist(mapa);
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getMapaList().add(mapa);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMapa(mapa.getCodbarramapa()) != null) {
                throw new PreexistingEntityException("Mapa " + mapa + " already exists.", ex);
            }
            
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * el metódo actualiza uno o más atributo(s) del mapa en la base de datos.
     *
     * @param mapa
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Mapa mapa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mapa persistentMapa = em.find(Mapa.class, mapa.getCodbarramapa());
            CategoriaColeccion codcategoriacoleccionOld = persistentMapa.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = mapa.getCodcategoriacoleccion();
           
            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                mapa.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }
            
            mapa = em.merge(mapa);
            
            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getMapaList().remove(mapa);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }
            
            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getMapaList().add(mapa);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            
            if (msg == null || msg.length() == 0) {
                String id = mapa.getCodbarramapa();
                
                if (findMapa(id) == null) {
                    throw new NonexistentEntityException("The mapa with id " + id + " no longer exists.");
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
     * el metódo elimina un mapa de la base de datos.
     *
     * @param id
     * @throws NonexistentEntityException
     */
    private void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mapa mapa;
            
            try {
                mapa = em.getReference(Mapa.class, id);
                mapa.getCodbarramapa();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mapa with id " + id + " no longer exists.", enfe);
            }
            
            CategoriaColeccion codcategoriacoleccion = mapa.getCodcategoriacoleccion();
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getMapaList().remove(mapa);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            
            em.remove(mapa);
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
    private List<Mapa> findMapaEntities() {
        return findMapaEntities(true, -1, -1);
    }

    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<Mapa> findMapaEntities(int maxResults, int firstResult) {
        return findMapaEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<Mapa> findMapaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mapa.class));
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
     * el metódo retrona un mapa asociado al codigo de barras ingresado.
     * 
     * @param codBarras
     * @return mapa
     */
    public Mapa findMapa(String codBarras) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mapa.class, codBarras); // -> mapa
        } finally {
            em.close();
        }
    }
    
    /**
     * el metódo retorna un mapa asociado al isbn ingresado.
     *
     * @param isbn
     * @return mapa
     */
    public Mapa findMapaISBN(String isbn) {
        EntityManager em = getEntityManager();
        Mapa mapa = null;

        try {
            Query query = em.createQuery("SELECT m FROM Mapa m WHERE m.isbn = '" + isbn + "'");
            List<Mapa> listM = query.getResultList();

            if (!listM.isEmpty()) {
                mapa = listM.get(0);
            }
        } finally {
            em.close();
        }

        return mapa;
    }
    
    /**
     * 
     * @return 
     */
    private int getMapaCount() {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mapa> rt = cq.from(Mapa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
