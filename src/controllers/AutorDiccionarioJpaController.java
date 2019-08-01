/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entitysRecursos.AutorDiccionario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitysRecursos.AutorPorDiccionario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class AutorDiccionarioJpaController implements Serializable {

    public AutorDiccionarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutorDiccionario autorDiccionario) throws PreexistingEntityException, Exception {
        if (autorDiccionario.getAutorPorDiccionarioList() == null) {
            autorDiccionario.setAutorPorDiccionarioList(new ArrayList<AutorPorDiccionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<AutorPorDiccionario> attachedAutorPorDiccionarioList = new ArrayList<AutorPorDiccionario>();
            for (AutorPorDiccionario autorPorDiccionarioListAutorPorDiccionarioToAttach : autorDiccionario.getAutorPorDiccionarioList()) {
                autorPorDiccionarioListAutorPorDiccionarioToAttach = em.getReference(autorPorDiccionarioListAutorPorDiccionarioToAttach.getClass(), autorPorDiccionarioListAutorPorDiccionarioToAttach.getCodautdic());
                attachedAutorPorDiccionarioList.add(autorPorDiccionarioListAutorPorDiccionarioToAttach);
            }
            autorDiccionario.setAutorPorDiccionarioList(attachedAutorPorDiccionarioList);
            em.persist(autorDiccionario);
            for (AutorPorDiccionario autorPorDiccionarioListAutorPorDiccionario : autorDiccionario.getAutorPorDiccionarioList()) {
                AutorDiccionario oldCodautordiccionarioOfAutorPorDiccionarioListAutorPorDiccionario = autorPorDiccionarioListAutorPorDiccionario.getCodautordiccionario();
                autorPorDiccionarioListAutorPorDiccionario.setCodautordiccionario(autorDiccionario);
                autorPorDiccionarioListAutorPorDiccionario = em.merge(autorPorDiccionarioListAutorPorDiccionario);
                if (oldCodautordiccionarioOfAutorPorDiccionarioListAutorPorDiccionario != null) {
                    oldCodautordiccionarioOfAutorPorDiccionarioListAutorPorDiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionarioListAutorPorDiccionario);
                    oldCodautordiccionarioOfAutorPorDiccionarioListAutorPorDiccionario = em.merge(oldCodautordiccionarioOfAutorPorDiccionarioListAutorPorDiccionario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAutorDiccionario(autorDiccionario.getCodautordiccionario()) != null) {
                throw new PreexistingEntityException("AutorDiccionario " + autorDiccionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutorDiccionario autorDiccionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutorDiccionario persistentAutorDiccionario = em.find(AutorDiccionario.class, autorDiccionario.getCodautordiccionario());
            List<AutorPorDiccionario> autorPorDiccionarioListOld = persistentAutorDiccionario.getAutorPorDiccionarioList();
            List<AutorPorDiccionario> autorPorDiccionarioListNew = autorDiccionario.getAutorPorDiccionarioList();
            List<String> illegalOrphanMessages = null;
            for (AutorPorDiccionario autorPorDiccionarioListOldAutorPorDiccionario : autorPorDiccionarioListOld) {
                if (!autorPorDiccionarioListNew.contains(autorPorDiccionarioListOldAutorPorDiccionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutorPorDiccionario " + autorPorDiccionarioListOldAutorPorDiccionario + " since its codautordiccionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<AutorPorDiccionario> attachedAutorPorDiccionarioListNew = new ArrayList<AutorPorDiccionario>();
            for (AutorPorDiccionario autorPorDiccionarioListNewAutorPorDiccionarioToAttach : autorPorDiccionarioListNew) {
                autorPorDiccionarioListNewAutorPorDiccionarioToAttach = em.getReference(autorPorDiccionarioListNewAutorPorDiccionarioToAttach.getClass(), autorPorDiccionarioListNewAutorPorDiccionarioToAttach.getCodautdic());
                attachedAutorPorDiccionarioListNew.add(autorPorDiccionarioListNewAutorPorDiccionarioToAttach);
            }
            autorPorDiccionarioListNew = attachedAutorPorDiccionarioListNew;
            autorDiccionario.setAutorPorDiccionarioList(autorPorDiccionarioListNew);
            autorDiccionario = em.merge(autorDiccionario);
            for (AutorPorDiccionario autorPorDiccionarioListNewAutorPorDiccionario : autorPorDiccionarioListNew) {
                if (!autorPorDiccionarioListOld.contains(autorPorDiccionarioListNewAutorPorDiccionario)) {
                    AutorDiccionario oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario = autorPorDiccionarioListNewAutorPorDiccionario.getCodautordiccionario();
                    autorPorDiccionarioListNewAutorPorDiccionario.setCodautordiccionario(autorDiccionario);
                    autorPorDiccionarioListNewAutorPorDiccionario = em.merge(autorPorDiccionarioListNewAutorPorDiccionario);
                    if (oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario != null && !oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario.equals(autorDiccionario)) {
                        oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionarioListNewAutorPorDiccionario);
                        oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario = em.merge(oldCodautordiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = autorDiccionario.getCodautordiccionario();
                if (findAutorDiccionario(id) == null) {
                    throw new NonexistentEntityException("The autorDiccionario with id " + id + " no longer exists.");
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
            AutorDiccionario autorDiccionario;
            try {
                autorDiccionario = em.getReference(AutorDiccionario.class, id);
                autorDiccionario.getCodautordiccionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autorDiccionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AutorPorDiccionario> autorPorDiccionarioListOrphanCheck = autorDiccionario.getAutorPorDiccionarioList();
            for (AutorPorDiccionario autorPorDiccionarioListOrphanCheckAutorPorDiccionario : autorPorDiccionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AutorDiccionario (" + autorDiccionario + ") cannot be destroyed since the AutorPorDiccionario " + autorPorDiccionarioListOrphanCheckAutorPorDiccionario + " in its autorPorDiccionarioList field has a non-nullable codautordiccionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(autorDiccionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutorDiccionario> findAutorDiccionarioEntities() {
        return findAutorDiccionarioEntities(true, -1, -1);
    }

    public List<AutorDiccionario> findAutorDiccionarioEntities(int maxResults, int firstResult) {
        return findAutorDiccionarioEntities(false, maxResults, firstResult);
    }

    private List<AutorDiccionario> findAutorDiccionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutorDiccionario.class));
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

    public AutorDiccionario findAutorDiccionario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutorDiccionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorDiccionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutorDiccionario> rt = cq.from(AutorDiccionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
