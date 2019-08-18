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
import entitysRecursos.CategoriaColeccion;
import entitysRecursos.MateriaPorLibro;
import java.util.ArrayList;
import java.util.List;
import entitysRecursos.AutorPorLibro;
import entitysRecursos.Libro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class LibroJpaController implements Serializable {

    public LibroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) throws PreexistingEntityException, Exception {
        if (libro.getMateriaPorLibroList() == null) {
            libro.setMateriaPorLibroList(new ArrayList<MateriaPorLibro>());
        }
        if (libro.getAutorPorLibroList() == null) {
            libro.setAutorPorLibroList(new ArrayList<AutorPorLibro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = libro.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                libro.setCodcategoriacoleccion(codcategoriacoleccion);
            }
            List<MateriaPorLibro> attachedMateriaPorLibroList = new ArrayList<MateriaPorLibro>();
            for (MateriaPorLibro materiaPorLibroListMateriaPorLibroToAttach : libro.getMateriaPorLibroList()) {
                materiaPorLibroListMateriaPorLibroToAttach = em.getReference(materiaPorLibroListMateriaPorLibroToAttach.getClass(), materiaPorLibroListMateriaPorLibroToAttach.getCodmaterialibro());
                attachedMateriaPorLibroList.add(materiaPorLibroListMateriaPorLibroToAttach);
            }
            libro.setMateriaPorLibroList(attachedMateriaPorLibroList);
            List<AutorPorLibro> attachedAutorPorLibroList = new ArrayList<AutorPorLibro>();
            for (AutorPorLibro autorPorLibroListAutorPorLibroToAttach : libro.getAutorPorLibroList()) {
                autorPorLibroListAutorPorLibroToAttach = em.getReference(autorPorLibroListAutorPorLibroToAttach.getClass(), autorPorLibroListAutorPorLibroToAttach.getCodautlib());
                attachedAutorPorLibroList.add(autorPorLibroListAutorPorLibroToAttach);
            }
            libro.setAutorPorLibroList(attachedAutorPorLibroList);
            em.persist(libro);
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getLibroList().add(libro);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            for (MateriaPorLibro materiaPorLibroListMateriaPorLibro : libro.getMateriaPorLibroList()) {
                Libro oldCodbarralibroOfMateriaPorLibroListMateriaPorLibro = materiaPorLibroListMateriaPorLibro.getCodbarralibro();
                materiaPorLibroListMateriaPorLibro.setCodbarralibro(libro);
                materiaPorLibroListMateriaPorLibro = em.merge(materiaPorLibroListMateriaPorLibro);
                if (oldCodbarralibroOfMateriaPorLibroListMateriaPorLibro != null) {
                    oldCodbarralibroOfMateriaPorLibroListMateriaPorLibro.getMateriaPorLibroList().remove(materiaPorLibroListMateriaPorLibro);
                    oldCodbarralibroOfMateriaPorLibroListMateriaPorLibro = em.merge(oldCodbarralibroOfMateriaPorLibroListMateriaPorLibro);
                }
            }
            for (AutorPorLibro autorPorLibroListAutorPorLibro : libro.getAutorPorLibroList()) {
                Libro oldCodbarralibroOfAutorPorLibroListAutorPorLibro = autorPorLibroListAutorPorLibro.getCodbarralibro();
                autorPorLibroListAutorPorLibro.setCodbarralibro(libro);
                autorPorLibroListAutorPorLibro = em.merge(autorPorLibroListAutorPorLibro);
                if (oldCodbarralibroOfAutorPorLibroListAutorPorLibro != null) {
                    oldCodbarralibroOfAutorPorLibroListAutorPorLibro.getAutorPorLibroList().remove(autorPorLibroListAutorPorLibro);
                    oldCodbarralibroOfAutorPorLibroListAutorPorLibro = em.merge(oldCodbarralibroOfAutorPorLibroListAutorPorLibro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLibro(libro.getCodbarralibro()) != null) {
                throw new PreexistingEntityException("Libro " + libro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro persistentLibro = em.find(Libro.class, libro.getCodbarralibro());
            CategoriaColeccion codcategoriacoleccionOld = persistentLibro.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = libro.getCodcategoriacoleccion();
            List<MateriaPorLibro> materiaPorLibroListOld = persistentLibro.getMateriaPorLibroList();
            List<MateriaPorLibro> materiaPorLibroListNew = libro.getMateriaPorLibroList();
            List<AutorPorLibro> autorPorLibroListOld = persistentLibro.getAutorPorLibroList();
            List<AutorPorLibro> autorPorLibroListNew = libro.getAutorPorLibroList();
            List<String> illegalOrphanMessages = null;
            for (MateriaPorLibro materiaPorLibroListOldMateriaPorLibro : materiaPorLibroListOld) {
                if (!materiaPorLibroListNew.contains(materiaPorLibroListOldMateriaPorLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MateriaPorLibro " + materiaPorLibroListOldMateriaPorLibro + " since its codbarralibro field is not nullable.");
                }
            }
            for (AutorPorLibro autorPorLibroListOldAutorPorLibro : autorPorLibroListOld) {
                if (!autorPorLibroListNew.contains(autorPorLibroListOldAutorPorLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutorPorLibro " + autorPorLibroListOldAutorPorLibro + " since its codbarralibro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                libro.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }
            List<MateriaPorLibro> attachedMateriaPorLibroListNew = new ArrayList<MateriaPorLibro>();
            for (MateriaPorLibro materiaPorLibroListNewMateriaPorLibroToAttach : materiaPorLibroListNew) {
                materiaPorLibroListNewMateriaPorLibroToAttach = em.getReference(materiaPorLibroListNewMateriaPorLibroToAttach.getClass(), materiaPorLibroListNewMateriaPorLibroToAttach.getCodmaterialibro());
                attachedMateriaPorLibroListNew.add(materiaPorLibroListNewMateriaPorLibroToAttach);
            }
            materiaPorLibroListNew = attachedMateriaPorLibroListNew;
            libro.setMateriaPorLibroList(materiaPorLibroListNew);
            List<AutorPorLibro> attachedAutorPorLibroListNew = new ArrayList<AutorPorLibro>();
            for (AutorPorLibro autorPorLibroListNewAutorPorLibroToAttach : autorPorLibroListNew) {
                autorPorLibroListNewAutorPorLibroToAttach = em.getReference(autorPorLibroListNewAutorPorLibroToAttach.getClass(), autorPorLibroListNewAutorPorLibroToAttach.getCodautlib());
                attachedAutorPorLibroListNew.add(autorPorLibroListNewAutorPorLibroToAttach);
            }
            autorPorLibroListNew = attachedAutorPorLibroListNew;
            libro.setAutorPorLibroList(autorPorLibroListNew);
            libro = em.merge(libro);
            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getLibroList().remove(libro);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }
            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getLibroList().add(libro);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }
            for (MateriaPorLibro materiaPorLibroListNewMateriaPorLibro : materiaPorLibroListNew) {
                if (!materiaPorLibroListOld.contains(materiaPorLibroListNewMateriaPorLibro)) {
                    Libro oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro = materiaPorLibroListNewMateriaPorLibro.getCodbarralibro();
                    materiaPorLibroListNewMateriaPorLibro.setCodbarralibro(libro);
                    materiaPorLibroListNewMateriaPorLibro = em.merge(materiaPorLibroListNewMateriaPorLibro);
                    if (oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro != null && !oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro.equals(libro)) {
                        oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro.getMateriaPorLibroList().remove(materiaPorLibroListNewMateriaPorLibro);
                        oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro = em.merge(oldCodbarralibroOfMateriaPorLibroListNewMateriaPorLibro);
                    }
                }
            }
            for (AutorPorLibro autorPorLibroListNewAutorPorLibro : autorPorLibroListNew) {
                if (!autorPorLibroListOld.contains(autorPorLibroListNewAutorPorLibro)) {
                    Libro oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro = autorPorLibroListNewAutorPorLibro.getCodbarralibro();
                    autorPorLibroListNewAutorPorLibro.setCodbarralibro(libro);
                    autorPorLibroListNewAutorPorLibro = em.merge(autorPorLibroListNewAutorPorLibro);
                    if (oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro != null && !oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro.equals(libro)) {
                        oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro.getAutorPorLibroList().remove(autorPorLibroListNewAutorPorLibro);
                        oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro = em.merge(oldCodbarralibroOfAutorPorLibroListNewAutorPorLibro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = libro.getCodbarralibro();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
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
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getCodbarralibro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<MateriaPorLibro> materiaPorLibroListOrphanCheck = libro.getMateriaPorLibroList();
            for (MateriaPorLibro materiaPorLibroListOrphanCheckMateriaPorLibro : materiaPorLibroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Libro (" + libro + ") cannot be destroyed since the MateriaPorLibro " + materiaPorLibroListOrphanCheckMateriaPorLibro + " in its materiaPorLibroList field has a non-nullable codbarralibro field.");
            }
            List<AutorPorLibro> autorPorLibroListOrphanCheck = libro.getAutorPorLibroList();
            for (AutorPorLibro autorPorLibroListOrphanCheckAutorPorLibro : autorPorLibroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Libro (" + libro + ") cannot be destroyed since the AutorPorLibro " + autorPorLibroListOrphanCheckAutorPorLibro + " in its autorPorLibroList field has a non-nullable codbarralibro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CategoriaColeccion codcategoriacoleccion = libro.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getLibroList().remove(libro);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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

    public Libro findLibro(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public List<Libro> findLibroTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Libro " + "WHERE titulo ILIKE '%" + cadena + "%'", Libro.class);
            List<Libro> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }
    
    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
