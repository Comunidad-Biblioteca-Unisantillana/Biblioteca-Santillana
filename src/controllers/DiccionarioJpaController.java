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
import entitys.AutorPorDiccionario;
import entitys.Diccionario;
import java.util.ArrayList;
import java.util.List;
import entitys.MateriaPorDiccionario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 */
public class DiccionarioJpaController implements Serializable {

    public DiccionarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diccionario diccionario) throws PreexistingEntityException, Exception {
        if (diccionario.getAutorPorDiccionarioList() == null) {
            diccionario.setAutorPorDiccionarioList(new ArrayList<AutorPorDiccionario>());
        }
        if (diccionario.getMateriaPorDiccionarioList() == null) {
            diccionario.setMateriaPorDiccionarioList(new ArrayList<MateriaPorDiccionario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion codcategoriacoleccion = diccionario.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion = em.getReference(codcategoriacoleccion.getClass(), codcategoriacoleccion.getCodcategoriacoleccion());
                diccionario.setCodcategoriacoleccion(codcategoriacoleccion);
            }
            List<AutorPorDiccionario> attachedAutorPorDiccionarioList = new ArrayList<AutorPorDiccionario>();
            for (AutorPorDiccionario autorPorDiccionarioListAutorPorDiccionarioToAttach : diccionario.getAutorPorDiccionarioList()) {
                autorPorDiccionarioListAutorPorDiccionarioToAttach = em.getReference(autorPorDiccionarioListAutorPorDiccionarioToAttach.getClass(), autorPorDiccionarioListAutorPorDiccionarioToAttach.getCodautdic());
                attachedAutorPorDiccionarioList.add(autorPorDiccionarioListAutorPorDiccionarioToAttach);
            }
            diccionario.setAutorPorDiccionarioList(attachedAutorPorDiccionarioList);
            List<MateriaPorDiccionario> attachedMateriaPorDiccionarioList = new ArrayList<MateriaPorDiccionario>();
            for (MateriaPorDiccionario materiaPorDiccionarioListMateriaPorDiccionarioToAttach : diccionario.getMateriaPorDiccionarioList()) {
                materiaPorDiccionarioListMateriaPorDiccionarioToAttach = em.getReference(materiaPorDiccionarioListMateriaPorDiccionarioToAttach.getClass(), materiaPorDiccionarioListMateriaPorDiccionarioToAttach.getCodmateriadiccionario());
                attachedMateriaPorDiccionarioList.add(materiaPorDiccionarioListMateriaPorDiccionarioToAttach);
            }
            diccionario.setMateriaPorDiccionarioList(attachedMateriaPorDiccionarioList);
            em.persist(diccionario);
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getDiccionarioList().add(diccionario);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            for (AutorPorDiccionario autorPorDiccionarioListAutorPorDiccionario : diccionario.getAutorPorDiccionarioList()) {
                Diccionario oldCodbarradiccionarioOfAutorPorDiccionarioListAutorPorDiccionario = autorPorDiccionarioListAutorPorDiccionario.getCodbarradiccionario();
                autorPorDiccionarioListAutorPorDiccionario.setCodbarradiccionario(diccionario);
                autorPorDiccionarioListAutorPorDiccionario = em.merge(autorPorDiccionarioListAutorPorDiccionario);
                if (oldCodbarradiccionarioOfAutorPorDiccionarioListAutorPorDiccionario != null) {
                    oldCodbarradiccionarioOfAutorPorDiccionarioListAutorPorDiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionarioListAutorPorDiccionario);
                    oldCodbarradiccionarioOfAutorPorDiccionarioListAutorPorDiccionario = em.merge(oldCodbarradiccionarioOfAutorPorDiccionarioListAutorPorDiccionario);
                }
            }
            for (MateriaPorDiccionario materiaPorDiccionarioListMateriaPorDiccionario : diccionario.getMateriaPorDiccionarioList()) {
                Diccionario oldCodbarradiccionarioOfMateriaPorDiccionarioListMateriaPorDiccionario = materiaPorDiccionarioListMateriaPorDiccionario.getCodbarradiccionario();
                materiaPorDiccionarioListMateriaPorDiccionario.setCodbarradiccionario(diccionario);
                materiaPorDiccionarioListMateriaPorDiccionario = em.merge(materiaPorDiccionarioListMateriaPorDiccionario);
                if (oldCodbarradiccionarioOfMateriaPorDiccionarioListMateriaPorDiccionario != null) {
                    oldCodbarradiccionarioOfMateriaPorDiccionarioListMateriaPorDiccionario.getMateriaPorDiccionarioList().remove(materiaPorDiccionarioListMateriaPorDiccionario);
                    oldCodbarradiccionarioOfMateriaPorDiccionarioListMateriaPorDiccionario = em.merge(oldCodbarradiccionarioOfMateriaPorDiccionarioListMateriaPorDiccionario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiccionario(diccionario.getCodbarradiccionario()) != null) {
                throw new PreexistingEntityException("Diccionario " + diccionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diccionario diccionario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diccionario persistentDiccionario = em.find(Diccionario.class, diccionario.getCodbarradiccionario());
            CategoriaColeccion codcategoriacoleccionOld = persistentDiccionario.getCodcategoriacoleccion();
            CategoriaColeccion codcategoriacoleccionNew = diccionario.getCodcategoriacoleccion();
            List<AutorPorDiccionario> autorPorDiccionarioListOld = persistentDiccionario.getAutorPorDiccionarioList();
            List<AutorPorDiccionario> autorPorDiccionarioListNew = diccionario.getAutorPorDiccionarioList();
            List<MateriaPorDiccionario> materiaPorDiccionarioListOld = persistentDiccionario.getMateriaPorDiccionarioList();
            List<MateriaPorDiccionario> materiaPorDiccionarioListNew = diccionario.getMateriaPorDiccionarioList();
            List<String> illegalOrphanMessages = null;
            for (AutorPorDiccionario autorPorDiccionarioListOldAutorPorDiccionario : autorPorDiccionarioListOld) {
                if (!autorPorDiccionarioListNew.contains(autorPorDiccionarioListOldAutorPorDiccionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AutorPorDiccionario " + autorPorDiccionarioListOldAutorPorDiccionario + " since its codbarradiccionario field is not nullable.");
                }
            }
            for (MateriaPorDiccionario materiaPorDiccionarioListOldMateriaPorDiccionario : materiaPorDiccionarioListOld) {
                if (!materiaPorDiccionarioListNew.contains(materiaPorDiccionarioListOldMateriaPorDiccionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MateriaPorDiccionario " + materiaPorDiccionarioListOldMateriaPorDiccionario + " since its codbarradiccionario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codcategoriacoleccionNew != null) {
                codcategoriacoleccionNew = em.getReference(codcategoriacoleccionNew.getClass(), codcategoriacoleccionNew.getCodcategoriacoleccion());
                diccionario.setCodcategoriacoleccion(codcategoriacoleccionNew);
            }
            List<AutorPorDiccionario> attachedAutorPorDiccionarioListNew = new ArrayList<AutorPorDiccionario>();
            for (AutorPorDiccionario autorPorDiccionarioListNewAutorPorDiccionarioToAttach : autorPorDiccionarioListNew) {
                autorPorDiccionarioListNewAutorPorDiccionarioToAttach = em.getReference(autorPorDiccionarioListNewAutorPorDiccionarioToAttach.getClass(), autorPorDiccionarioListNewAutorPorDiccionarioToAttach.getCodautdic());
                attachedAutorPorDiccionarioListNew.add(autorPorDiccionarioListNewAutorPorDiccionarioToAttach);
            }
            autorPorDiccionarioListNew = attachedAutorPorDiccionarioListNew;
            diccionario.setAutorPorDiccionarioList(autorPorDiccionarioListNew);
            List<MateriaPorDiccionario> attachedMateriaPorDiccionarioListNew = new ArrayList<MateriaPorDiccionario>();
            for (MateriaPorDiccionario materiaPorDiccionarioListNewMateriaPorDiccionarioToAttach : materiaPorDiccionarioListNew) {
                materiaPorDiccionarioListNewMateriaPorDiccionarioToAttach = em.getReference(materiaPorDiccionarioListNewMateriaPorDiccionarioToAttach.getClass(), materiaPorDiccionarioListNewMateriaPorDiccionarioToAttach.getCodmateriadiccionario());
                attachedMateriaPorDiccionarioListNew.add(materiaPorDiccionarioListNewMateriaPorDiccionarioToAttach);
            }
            materiaPorDiccionarioListNew = attachedMateriaPorDiccionarioListNew;
            diccionario.setMateriaPorDiccionarioList(materiaPorDiccionarioListNew);
            diccionario = em.merge(diccionario);
            if (codcategoriacoleccionOld != null && !codcategoriacoleccionOld.equals(codcategoriacoleccionNew)) {
                codcategoriacoleccionOld.getDiccionarioList().remove(diccionario);
                codcategoriacoleccionOld = em.merge(codcategoriacoleccionOld);
            }
            if (codcategoriacoleccionNew != null && !codcategoriacoleccionNew.equals(codcategoriacoleccionOld)) {
                codcategoriacoleccionNew.getDiccionarioList().add(diccionario);
                codcategoriacoleccionNew = em.merge(codcategoriacoleccionNew);
            }
            for (AutorPorDiccionario autorPorDiccionarioListNewAutorPorDiccionario : autorPorDiccionarioListNew) {
                if (!autorPorDiccionarioListOld.contains(autorPorDiccionarioListNewAutorPorDiccionario)) {
                    Diccionario oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario = autorPorDiccionarioListNewAutorPorDiccionario.getCodbarradiccionario();
                    autorPorDiccionarioListNewAutorPorDiccionario.setCodbarradiccionario(diccionario);
                    autorPorDiccionarioListNewAutorPorDiccionario = em.merge(autorPorDiccionarioListNewAutorPorDiccionario);
                    if (oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario != null && !oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario.equals(diccionario)) {
                        oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario.getAutorPorDiccionarioList().remove(autorPorDiccionarioListNewAutorPorDiccionario);
                        oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario = em.merge(oldCodbarradiccionarioOfAutorPorDiccionarioListNewAutorPorDiccionario);
                    }
                }
            }
            for (MateriaPorDiccionario materiaPorDiccionarioListNewMateriaPorDiccionario : materiaPorDiccionarioListNew) {
                if (!materiaPorDiccionarioListOld.contains(materiaPorDiccionarioListNewMateriaPorDiccionario)) {
                    Diccionario oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario = materiaPorDiccionarioListNewMateriaPorDiccionario.getCodbarradiccionario();
                    materiaPorDiccionarioListNewMateriaPorDiccionario.setCodbarradiccionario(diccionario);
                    materiaPorDiccionarioListNewMateriaPorDiccionario = em.merge(materiaPorDiccionarioListNewMateriaPorDiccionario);
                    if (oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario != null && !oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario.equals(diccionario)) {
                        oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario.getMateriaPorDiccionarioList().remove(materiaPorDiccionarioListNewMateriaPorDiccionario);
                        oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario = em.merge(oldCodbarradiccionarioOfMateriaPorDiccionarioListNewMateriaPorDiccionario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = diccionario.getCodbarradiccionario();
                if (findDiccionario(id) == null) {
                    throw new NonexistentEntityException("The diccionario with id " + id + " no longer exists.");
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
            Diccionario diccionario;
            try {
                diccionario = em.getReference(Diccionario.class, id);
                diccionario.getCodbarradiccionario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diccionario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AutorPorDiccionario> autorPorDiccionarioListOrphanCheck = diccionario.getAutorPorDiccionarioList();
            for (AutorPorDiccionario autorPorDiccionarioListOrphanCheckAutorPorDiccionario : autorPorDiccionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Diccionario (" + diccionario + ") cannot be destroyed since the AutorPorDiccionario " + autorPorDiccionarioListOrphanCheckAutorPorDiccionario + " in its autorPorDiccionarioList field has a non-nullable codbarradiccionario field.");
            }
            List<MateriaPorDiccionario> materiaPorDiccionarioListOrphanCheck = diccionario.getMateriaPorDiccionarioList();
            for (MateriaPorDiccionario materiaPorDiccionarioListOrphanCheckMateriaPorDiccionario : materiaPorDiccionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Diccionario (" + diccionario + ") cannot be destroyed since the MateriaPorDiccionario " + materiaPorDiccionarioListOrphanCheckMateriaPorDiccionario + " in its materiaPorDiccionarioList field has a non-nullable codbarradiccionario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CategoriaColeccion codcategoriacoleccion = diccionario.getCodcategoriacoleccion();
            if (codcategoriacoleccion != null) {
                codcategoriacoleccion.getDiccionarioList().remove(diccionario);
                codcategoriacoleccion = em.merge(codcategoriacoleccion);
            }
            em.remove(diccionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diccionario> findDiccionarioEntities() {
        return findDiccionarioEntities(true, -1, -1);
    }

    public List<Diccionario> findDiccionarioEntities(int maxResults, int firstResult) {
        return findDiccionarioEntities(false, maxResults, firstResult);
    }

    private List<Diccionario> findDiccionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diccionario.class));
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

    public Diccionario findDiccionario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diccionario.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Diccionario> findDiccionarioTitulo(String cadena) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNativeQuery("SELECT * FROM Diccionario " + "WHERE titulo ILIKE '%" + cadena + "%'", Diccionario.class);
            List<Diccionario> lista = query.getResultList();
            return lista;
        } finally {
            em.close();
        }
    }

    public int getDiccionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diccionario> rt = cq.from(Diccionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
