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
import entitys.CategoriaColeccion;
import entitys.Mapa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class MapaJpaController implements Serializable {

    public MapaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mapa mapa) throws PreexistingEntityException, Exception {
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

    public void destroy(String id) throws NonexistentEntityException {
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

    public List<Mapa> findMapaEntities() {
        return findMapaEntities(true, -1, -1);
    }

    public List<Mapa> findMapaEntities(int maxResults, int firstResult) {
        return findMapaEntities(false, maxResults, firstResult);
    }

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

    public Mapa findMapa(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mapa.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Mapa> findMapaTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Mapa " + "WHERE titulo ILIKE '%" + cadena + "%'", Mapa.class);
            List<Mapa> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }

    public int getMapaCount() {
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
