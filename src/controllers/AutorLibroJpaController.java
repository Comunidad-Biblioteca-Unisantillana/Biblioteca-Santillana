/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entitysRecursos.AutorLibro;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitysRecursos.AutorPorLibro;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class AutorLibroJpaController implements Serializable {

    public AutorLibroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutorLibro autorLibro) throws PreexistingEntityException, Exception {
        if (autorLibro.getAutorPorLibroList() == null) {
            autorLibro.setAutorPorLibroList(new ArrayList<AutorPorLibro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AutorPorLibro> attachedAutorPorLibroList = new ArrayList<AutorPorLibro>();
            for (AutorPorLibro autorPorLibroListAutorPorLibroToAttach : autorLibro.getAutorPorLibroList()) {
                autorPorLibroListAutorPorLibroToAttach = em.getReference(autorPorLibroListAutorPorLibroToAttach.getClass(), autorPorLibroListAutorPorLibroToAttach.getCodautlib());
                attachedAutorPorLibroList.add(autorPorLibroListAutorPorLibroToAttach);
            }
            autorLibro.setAutorPorLibroList(attachedAutorPorLibroList);
            em.persist(autorLibro);
            for (AutorPorLibro autorPorLibroListAutorPorLibro : autorLibro.getAutorPorLibroList()) {
                AutorLibro oldCodautorlibroOfAutorPorLibroListAutorPorLibro = autorPorLibroListAutorPorLibro.getCodautorlibro();
                autorPorLibroListAutorPorLibro.setCodautorlibro(autorLibro);
                autorPorLibroListAutorPorLibro = em.merge(autorPorLibroListAutorPorLibro);
                if (oldCodautorlibroOfAutorPorLibroListAutorPorLibro != null) {
                    oldCodautorlibroOfAutorPorLibroListAutorPorLibro.getAutorPorLibroList().remove(autorPorLibroListAutorPorLibro);
                    oldCodautorlibroOfAutorPorLibroListAutorPorLibro = em.merge(oldCodautorlibroOfAutorPorLibroListAutorPorLibro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorLibro(autorLibro.getCodautorlibro()) != null) {
                throw new PreexistingEntityException("AutorLibro " + autorLibro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutorLibro autorLibro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorLibro persistentAutorLibro = em.find(AutorLibro.class, autorLibro.getCodautorlibro());
            List<AutorPorLibro> autorPorLibroListOld = persistentAutorLibro.getAutorPorLibroList();
            List<AutorPorLibro> autorPorLibroListNew = autorLibro.getAutorPorLibroList();
            List<String> illegalOrphanMessages = null;
            for (AutorPorLibro autorPorLibroListOldAutorPorLibro : autorPorLibroListOld) {
                if (!autorPorLibroListNew.contains(autorPorLibroListOldAutorPorLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutorPorLibro " + autorPorLibroListOldAutorPorLibro + " since its codautorlibro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AutorPorLibro> attachedAutorPorLibroListNew = new ArrayList<AutorPorLibro>();
            for (AutorPorLibro autorPorLibroListNewAutorPorLibroToAttach : autorPorLibroListNew) {
                autorPorLibroListNewAutorPorLibroToAttach = em.getReference(autorPorLibroListNewAutorPorLibroToAttach.getClass(), autorPorLibroListNewAutorPorLibroToAttach.getCodautlib());
                attachedAutorPorLibroListNew.add(autorPorLibroListNewAutorPorLibroToAttach);
            }
            autorPorLibroListNew = attachedAutorPorLibroListNew;
            autorLibro.setAutorPorLibroList(autorPorLibroListNew);
            autorLibro = em.merge(autorLibro);
            for (AutorPorLibro autorPorLibroListNewAutorPorLibro : autorPorLibroListNew) {
                if (!autorPorLibroListOld.contains(autorPorLibroListNewAutorPorLibro)) {
                    AutorLibro oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro = autorPorLibroListNewAutorPorLibro.getCodautorlibro();
                    autorPorLibroListNewAutorPorLibro.setCodautorlibro(autorLibro);
                    autorPorLibroListNewAutorPorLibro = em.merge(autorPorLibroListNewAutorPorLibro);
                    if (oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro != null && !oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro.equals(autorLibro)) {
                        oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro.getAutorPorLibroList().remove(autorPorLibroListNewAutorPorLibro);
                        oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro = em.merge(oldCodautorlibroOfAutorPorLibroListNewAutorPorLibro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = autorLibro.getCodautorlibro();
                if (findAutorLibro(id) == null) {
                    throw new NonexistentEntityException("The autorLibro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorLibro autorLibro;
            try {
                autorLibro = em.getReference(AutorLibro.class, id);
                autorLibro.getCodautorlibro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorLibro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AutorPorLibro> autorPorLibroListOrphanCheck = autorLibro.getAutorPorLibroList();
            for (AutorPorLibro autorPorLibroListOrphanCheckAutorPorLibro : autorPorLibroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AutorLibro (" + autorLibro + ") cannot be destroyed since the AutorPorLibro " + autorPorLibroListOrphanCheckAutorPorLibro + " in its autorPorLibroList field has a non-nullable codautorlibro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(autorLibro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutorLibro> findAutorLibroEntities() {
        return findAutorLibroEntities(true, -1, -1);
    }

    public List<AutorLibro> findAutorLibroEntities(int maxResults, int firstResult) {
        return findAutorLibroEntities(false, maxResults, firstResult);
    }

    private List<AutorLibro> findAutorLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorLibro.class));
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

    public AutorLibro findAutorLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutorLibro.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorLibro> rt = cq.from(AutorLibro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
