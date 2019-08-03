/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitysRecursos.CategoriaColeccion;
import entitysRecursos.AutorPorEnciclopedia;
import entitysRecursos.Enciclopedia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class EnciclopediaJpaController implements Serializable {

    public EnciclopediaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enciclopedia enciclopedia) throws PreexistingEntityException, Exception {
        if (enciclopedia.getAutorPorEnciclopediaList() == null) {
            enciclopedia.setAutorPorEnciclopediaList(new ArrayList<AutorPorEnciclopedia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = enciclopedia.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                enciclopedia.setCodcategoriacoleccion(codcategoriacoleccion);
            }
            List<AutorPorEnciclopedia> attachedAutorPorEnciclopediaList = new ArrayList<AutorPorEnciclopedia>();
            for (AutorPorEnciclopedia autorPorEnciclopediaListAutorPorEnciclopediaToAttach : enciclopedia.getAutorPorEnciclopediaList()) {
                autorPorEnciclopediaListAutorPorEnciclopediaToAttach = em.getReference(autorPorEnciclopediaListAutorPorEnciclopediaToAttach.getClass(), autorPorEnciclopediaListAutorPorEnciclopediaToAttach.getCodautenc());
                attachedAutorPorEnciclopediaList.add(autorPorEnciclopediaListAutorPorEnciclopediaToAttach);
            }
            enciclopedia.setAutorPorEnciclopediaList(attachedAutorPorEnciclopediaList);
            em.persist(enciclopedia);
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getEnciclopediaList().add(enciclopedia);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            for (AutorPorEnciclopedia autorPorEnciclopediaListAutorPorEnciclopedia : enciclopedia.getAutorPorEnciclopediaList()) {
                Enciclopedia oldCodbarraenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia = autorPorEnciclopediaListAutorPorEnciclopedia.getCodbarraenciclopedia();
                autorPorEnciclopediaListAutorPorEnciclopedia.setCodbarraenciclopedia(enciclopedia);
                autorPorEnciclopediaListAutorPorEnciclopedia = em.merge(autorPorEnciclopediaListAutorPorEnciclopedia);
                if (oldCodbarraenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia != null) {
                    oldCodbarraenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopediaListAutorPorEnciclopedia);
                    oldCodbarraenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia = em.merge(oldCodbarraenciclopediaOfAutorPorEnciclopediaListAutorPorEnciclopedia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEnciclopedia(enciclopedia.getCodbarraenciclopedia()) != null) {
                throw new PreexistingEntityException("Enciclopedia " + enciclopedia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enciclopedia enciclopedia) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enciclopedia persistentEnciclopedia = em.find(Enciclopedia.class, enciclopedia.getCodbarraenciclopedia());
            CategoriaColeccion codcategoriacoleccionOld = persistentEnciclopedia.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = enciclopedia.getCodcategoriacoleccion();
            List<AutorPorEnciclopedia> autorPorEnciclopediaListOld = persistentEnciclopedia.getAutorPorEnciclopediaList();
            List<AutorPorEnciclopedia> autorPorEnciclopediaListNew = enciclopedia.getAutorPorEnciclopediaList();
            List<String> illegalOrphanMessages = null;
            for (AutorPorEnciclopedia autorPorEnciclopediaListOldAutorPorEnciclopedia : autorPorEnciclopediaListOld) {
                if (!autorPorEnciclopediaListNew.contains(autorPorEnciclopediaListOldAutorPorEnciclopedia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutorPorEnciclopedia " + autorPorEnciclopediaListOldAutorPorEnciclopedia + " since its codbarraenciclopedia field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                enciclopedia.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }
            List<AutorPorEnciclopedia> attachedAutorPorEnciclopediaListNew = new ArrayList<AutorPorEnciclopedia>();
            for (AutorPorEnciclopedia autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach : autorPorEnciclopediaListNew) {
                autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach = em.getReference(autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach.getClass(), autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach.getCodautenc());
                attachedAutorPorEnciclopediaListNew.add(autorPorEnciclopediaListNewAutorPorEnciclopediaToAttach);
            }
            autorPorEnciclopediaListNew = attachedAutorPorEnciclopediaListNew;
            enciclopedia.setAutorPorEnciclopediaList(autorPorEnciclopediaListNew);
            enciclopedia = em.merge(enciclopedia);
            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getEnciclopediaList().remove(enciclopedia);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }
            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getEnciclopediaList().add(enciclopedia);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }
            for (AutorPorEnciclopedia autorPorEnciclopediaListNewAutorPorEnciclopedia : autorPorEnciclopediaListNew) {
                if (!autorPorEnciclopediaListOld.contains(autorPorEnciclopediaListNewAutorPorEnciclopedia)) {
                    Enciclopedia oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia = autorPorEnciclopediaListNewAutorPorEnciclopedia.getCodbarraenciclopedia();
                    autorPorEnciclopediaListNewAutorPorEnciclopedia.setCodbarraenciclopedia(enciclopedia);
                    autorPorEnciclopediaListNewAutorPorEnciclopedia = em.merge(autorPorEnciclopediaListNewAutorPorEnciclopedia);
                    if (oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia != null && !oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia.equals(enciclopedia)) {
                        oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia.getAutorPorEnciclopediaList().remove(autorPorEnciclopediaListNewAutorPorEnciclopedia);
                        oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia = em.merge(oldCodbarraenciclopediaOfAutorPorEnciclopediaListNewAutorPorEnciclopedia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = enciclopedia.getCodbarraenciclopedia();
                if (findEnciclopedia(id) == null) {
                    throw new NonexistentEntityException("The enciclopedia with id " + id + " no longer exists.");
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
            Enciclopedia enciclopedia;
            try {
                enciclopedia = em.getReference(Enciclopedia.class, id);
                enciclopedia.getCodbarraenciclopedia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enciclopedia with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AutorPorEnciclopedia> autorPorEnciclopediaListOrphanCheck = enciclopedia.getAutorPorEnciclopediaList();
            for (AutorPorEnciclopedia autorPorEnciclopediaListOrphanCheckAutorPorEnciclopedia : autorPorEnciclopediaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Enciclopedia (" + enciclopedia + ") cannot be destroyed since the AutorPorEnciclopedia " + autorPorEnciclopediaListOrphanCheckAutorPorEnciclopedia + " in its autorPorEnciclopediaList field has a non-nullable codbarraenciclopedia field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CategoriaColeccion codcategoriacoleccion = enciclopedia.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getEnciclopediaList().remove(enciclopedia);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            em.remove(enciclopedia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enciclopedia> findEnciclopediaEntities() {
        return findEnciclopediaEntities(true, -1, -1);
    }

    public List<Enciclopedia> findEnciclopediaEntities(int maxResults, int firstResult) {
        return findEnciclopediaEntities(false, maxResults, firstResult);
    }

    private List<Enciclopedia> findEnciclopediaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enciclopedia.class));
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

    public Enciclopedia findEnciclopedia(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enciclopedia.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Enciclopedia> findEnciclopediaTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Enciclopedia " + "WHERE titulo ILIKE '%" + cadena + "%'", Enciclopedia.class);
            List<Enciclopedia> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }

    public int getEnciclopediaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enciclopedia> rt = cq.from(Enciclopedia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
