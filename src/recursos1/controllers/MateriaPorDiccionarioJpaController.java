package recursos1.controllers;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.Diccionario;
import recursos1.entitys.MateriaPorDiccionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import recursos1.entitys.Materia;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 18/08/2019
 */
public class MateriaPorDiccionarioJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     *
     */
    public MateriaPorDiccionarioJpaController() {
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
     * @param materiaPorDiccionario
     * @throws PreexistingEntityException
     * @throws Exception 
     */
    private void create(MateriaPorDiccionario materiaPorDiccionario) throws PreexistingEntityException, Exception {
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

    /**
     * 
     * @param materiaPorDiccionario
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    private void edit(MateriaPorDiccionario materiaPorDiccionario) throws NonexistentEntityException, Exception {
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

    /**
     * 
     * @return 
     */
    private List<MateriaPorDiccionario> findMateriaPorDiccionarioEntities() {
        return findMateriaPorDiccionarioEntities(true, -1, -1);
    }

    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<MateriaPorDiccionario> findMateriaPorDiccionarioEntities(int maxResults, int firstResult) {
        return findMateriaPorDiccionarioEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
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
     * 
     * @param id
     * @return 
     */
    private MateriaPorDiccionario findMateriaPorDiccionario(String id) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(MateriaPorDiccionario.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Método que retorna una lista de materias de un Diccionario, por
     * medio del codBarras.
     *
     * @param codBarras
     * @return listaMat
     */
    public List<Materia> findMateriaPorDiccionarioCodBarras(String codBarras) {
        EntityManager em = getEntityManager();
        List<Materia> listaMat = null;

        try {
            Query query = em.createQuery("SELECT m.codmateria FROM MateriaPorDiccionario m "
                    + "WHERE m.codbarradiccionario.codbarradiccionario = '" + codBarras + "'");

            listaMat = (List<Materia>) query.getResultList();
        } finally {
            em.close();
        }

        return listaMat;
    }
    
    /**
     * 
     * @return 
     */
    private int getMateriaPorDiccionarioCount() {
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
