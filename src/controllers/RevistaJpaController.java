/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitys.CategoriaColeccion;
import entitys.MateriaPorRevista;
import entitys.Revista;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class RevistaJpaController implements Serializable {

    public RevistaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Revista revista) throws PreexistingEntityException, Exception {
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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

    public List<Revista> findRevistaEntities() {
        return findRevistaEntities(true, -1, -1);
    }

    public List<Revista> findRevistaEntities(int maxResults, int firstResult) {
        return findRevistaEntities(false, maxResults, firstResult);
    }

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
    
     public List<Revista> findRevistaTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Revista " + "WHERE titulo ILIKE '%" + cadena + "%'", Revista.class);
            List<Revista> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }

    public Revista findRevista(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Revista.class, id);
        } finally {
            em.close();
        }
    }

    public int getRevistaCount() {
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
