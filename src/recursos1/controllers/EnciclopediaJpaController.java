package recursos1.controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.CategoriaColeccion;
import recursos1.entitys.AutorPorEnciclopedia;
import recursos1.entitys.Enciclopedia;
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
public class EnciclopediaJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * constructor sin parámetros.
     */
    public EnciclopediaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * el metódo retorna la conexión con la base de datos.
     *
     * @return em
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();    // -> em
    }

    /**
     * el metódo añade una nueva enciclopedia en la base de datos.
     *
     * @param enciclopedia
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(Enciclopedia enciclopedia) throws PreexistingEntityException, Exception {
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

    /**
     * el metódo actualiza uno o más atributo(s) de la enciclopedia en la base
     * de datos.
     *
     * @param enciclopedia
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
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

    /**
     * el metódo elimina un enciclopedia de la base de datos.
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

    /**
     *
     * @return
     */
    public List<Enciclopedia> findEnciclopediaEntities() {
        return findEnciclopediaEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<Enciclopedia> findEnciclopediaEntities(int maxResults, int firstResult) {
        return findEnciclopediaEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
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

    /**
     * el metódo retrona un enciclopedia asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return enciclopedia
     */
    public Enciclopedia findEnciclopedia(String codBarras) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Enciclopedia.class, codBarras); // -> enciclopedia
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna un enciclopedia asociado al isbn ingresado.
     *
     * @param isbn
     * @return enciclopedia
     */
    public Enciclopedia findEnciclopediaISBN(String isbn) {
        EntityManager em = getEntityManager();
        Enciclopedia enciclopedia = null;

        try {
            Query query = em.createQuery("SELECT e FROM Enciclopedia e WHERE e.isbn = '" + isbn + "'");
            List<Enciclopedia> listE = query.getResultList();

            if (!listE.isEmpty()) {
                enciclopedia = listE.get(0);
            }
        } finally {
            em.close();
        }

        return enciclopedia;
    }

    /**
     *
     * @return
     */
    private int getEnciclopediaCount() {
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
