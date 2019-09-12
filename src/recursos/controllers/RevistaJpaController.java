package recursos.controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos.entitys.CategoriaColeccion;
import recursos.entitys.MateriaPorRevista;
import recursos.entitys.Revista;
import java.util.ArrayList;
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
public class RevistaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * constructor sin parámetros.
     */
    public RevistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * el metódo retorna la conexión con la base de datos.
     *
     * @return em
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();   // -> em
    }

    /**
     * el metódo añade una nueva revista en la base de datos.
     *
     * @param revista
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(Revista revista) throws PreexistingEntityException, Exception {
        if (revista.getMateriaPorRevistaList() == null) {
            revista.setMateriaPorRevistaList(new ArrayList<MateriaPorRevista>());
        }
        
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = revista.getCodcategoriacoleccion();
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                revista.setCodcategoriacoleccion(codcategoriacoleccion);
            }
            
            List<MateriaPorRevista> attachedMateriaPorRevistaList = new ArrayList<MateriaPorRevista>();
            
            for (MateriaPorRevista materiaPorRevistaListMateriaPorRevistaToAttach : revista.getMateriaPorRevistaList()) {
                materiaPorRevistaListMateriaPorRevistaToAttach = em.getReference(materiaPorRevistaListMateriaPorRevistaToAttach.getClass(), materiaPorRevistaListMateriaPorRevistaToAttach.getCodmateriarevista());
                attachedMateriaPorRevistaList.add(materiaPorRevistaListMateriaPorRevistaToAttach);
            }
            
            revista.setMateriaPorRevistaList(attachedMateriaPorRevistaList);
            em.persist(revista);
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getRevistaList().add(revista);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            
            for (MateriaPorRevista materiaPorRevistaListMateriaPorRevista : revista.getMateriaPorRevistaList()) {
                Revista oldCodbarrarevistaOfMateriaPorRevistaListMateriaPorRevista = materiaPorRevistaListMateriaPorRevista.getCodbarrarevista();
                materiaPorRevistaListMateriaPorRevista.setCodbarrarevista(revista);
                materiaPorRevistaListMateriaPorRevista = em.merge(materiaPorRevistaListMateriaPorRevista);
                
                if (oldCodbarrarevistaOfMateriaPorRevistaListMateriaPorRevista != null) {
                    oldCodbarrarevistaOfMateriaPorRevistaListMateriaPorRevista.getMateriaPorRevistaList().remove(materiaPorRevistaListMateriaPorRevista);
                    oldCodbarrarevistaOfMateriaPorRevistaListMateriaPorRevista = em.merge(oldCodbarrarevistaOfMateriaPorRevistaListMateriaPorRevista);
                }
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRevista(revista.getCodbarrarevista()) != null) {
                throw new PreexistingEntityException("Revista " + revista + " already exists.", ex);
            }
            
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * el metódo actualiza uno o más atributo(s) de la revista en la base de
     * datos.
     *
     * @param revista
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
    public void edit(Revista revista) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Revista persistentRevista = em.find(Revista.class, revista.getCodbarrarevista());
            CategoriaColeccion codcategoriacoleccionOld = persistentRevista.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = revista.getCodcategoriacoleccion();
            List<MateriaPorRevista> materiaPorRevistaListOld = persistentRevista.getMateriaPorRevistaList();
            List<MateriaPorRevista> materiaPorRevistaListNew = revista.getMateriaPorRevistaList();
            List<String> illegalOrphanMessages = null;
            
            for (MateriaPorRevista materiaPorRevistaListOldMateriaPorRevista : materiaPorRevistaListOld) {
                if (!materiaPorRevistaListNew.contains(materiaPorRevistaListOldMateriaPorRevista)) {
                    
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain MateriaPorRevista " + materiaPorRevistaListOldMateriaPorRevista + " since its codbarrarevista field is not nullable.");
                }
            }
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            
            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                revista.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }
            
            List<MateriaPorRevista> attachedMateriaPorRevistaListNew = new ArrayList<MateriaPorRevista>();
            
            for (MateriaPorRevista materiaPorRevistaListNewMateriaPorRevistaToAttach : materiaPorRevistaListNew) {
                materiaPorRevistaListNewMateriaPorRevistaToAttach = em.getReference(materiaPorRevistaListNewMateriaPorRevistaToAttach.getClass(), materiaPorRevistaListNewMateriaPorRevistaToAttach.getCodmateriarevista());
                attachedMateriaPorRevistaListNew.add(materiaPorRevistaListNewMateriaPorRevistaToAttach);
            }
            
            materiaPorRevistaListNew = attachedMateriaPorRevistaListNew;
            revista.setMateriaPorRevistaList(materiaPorRevistaListNew);
            revista = em.merge(revista);
            
            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getRevistaList().remove(revista);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }
            
            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getRevistaList().add(revista);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }
            
            for (MateriaPorRevista materiaPorRevistaListNewMateriaPorRevista : materiaPorRevistaListNew) {
                if (!materiaPorRevistaListOld.contains(materiaPorRevistaListNewMateriaPorRevista)) {
                    Revista oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista = materiaPorRevistaListNewMateriaPorRevista.getCodbarrarevista();
                    materiaPorRevistaListNewMateriaPorRevista.setCodbarrarevista(revista);
                    materiaPorRevistaListNewMateriaPorRevista = em.merge(materiaPorRevistaListNewMateriaPorRevista);
                    
                    if (oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista != null && !oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista.equals(revista)) {
                        oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista.getMateriaPorRevistaList().remove(materiaPorRevistaListNewMateriaPorRevista);
                        oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista = em.merge(oldCodbarrarevistaOfMateriaPorRevistaListNewMateriaPorRevista);
                    }
                }
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            
            if (msg == null || msg.length() == 0) {
                String id = revista.getCodbarrarevista();
                
                if (findRevista(id) == null) {
                    throw new NonexistentEntityException("The revista with id " + id + " no longer exists.");
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
     * el metódo elimina una revista de la base de datos.
     *
     * @param id
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     */
    private void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Revista revista;
            
            try {
                revista = em.getReference(Revista.class, id);
                revista.getCodbarrarevista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The revista with id " + id + " no longer exists.", enfe);
            }
            
            List<String> illegalOrphanMessages = null;
            List<MateriaPorRevista> materiaPorRevistaListOrphanCheck = revista.getMateriaPorRevistaList();
            
            for (MateriaPorRevista materiaPorRevistaListOrphanCheckMateriaPorRevista : materiaPorRevistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This Revista (" + revista + ") cannot be destroyed since the MateriaPorRevista " + materiaPorRevistaListOrphanCheckMateriaPorRevista + " in its materiaPorRevistaList field has a non-nullable codbarrarevista field.");
            }
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            
            CategoriaColeccion codcategoriacoleccion = revista.getCodcategoriacoleccion();
            
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getRevistaList().remove(revista);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            
            em.remove(revista);
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
    public List<Revista> findRevistaEntities() {
        return findRevistaEntities(true, -1, -1);
    }

    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<Revista> findRevistaEntities(int maxResults, int firstResult) {
        return findRevistaEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<Revista> findRevistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Revista.class));
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
     * el metódo retrona una revista asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return revista
     */
    public Revista findRevista(String codBarras) {
        EntityManager em = getEntityManager();
        
        try {
            return em.find(Revista.class, codBarras); // -> revista
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna una revista asociado al issn ingresado.
     *
     * @param isbn
     * @return revista
     */
    public Revista findRevistaISSN(String issn) {
        EntityManager em = getEntityManager();
        Revista revista = null;

        try {
            Query query = em.createQuery("SELECT r FROM Revista r WHERE r.issn = '" + issn + "'");
            List<Revista> listR = query.getResultList();

            if (!listR.isEmpty()) {
                revista = listR.get(0);
            }
        } finally {
            em.close();
        }

        return revista;
    }

    /**
     *
     * @return
     */
    private int getRevistaCount() {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Revista> rt = cq.from(Revista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
