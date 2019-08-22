package recursos1.controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.CategoriaColeccion;
import recursos1.entitys.AutorPorDiccionario;
import recursos1.entitys.Diccionario;
import java.util.ArrayList;
import java.util.List;
import recursos1.entitys.MateriaPorDiccionario;
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
public class DiccionarioJpaController implements Serializable {

    private EntityManagerFactory emf;

    /**
     * constructor sin parámetros.
     */
    public DiccionarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * el metódo retorna la conexión con la base de datos.
     *
     * @return em
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();     // -> em
    }

    /**
     * el metódo añade un nuevo diccionaro en la base de datos.
     *
     * @param diccionario
     * @throws PreexistingEntityException
     * @throws Exception
     */
    private void create(Diccionario diccionario) throws PreexistingEntityException, Exception {
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

    /**
     * el metódo actualiza uno o más atributo(s) del diccionario en la base de
     * datos.
     *
     * @param diccionario
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception
     */
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

    /**
     * el metódo elimina un diccionario de la base de datos.
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

    /**
     *
     * @return
     */
    private List<Diccionario> findDiccionarioEntities() {
        return findDiccionarioEntities(true, -1, -1);
    }

    /**
     *
     * @param maxResults
     * @param firstResult
     * @return
     */
    private List<Diccionario> findDiccionarioEntities(int maxResults, int firstResult) {
        return findDiccionarioEntities(false, maxResults, firstResult);
    }

    /**
     *
     * @param all
     * @param maxResults
     * @param firstResult
     * @return
     */
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

    /**
     * el metódo retrona un diccionario asociado al codigo de barras ingresado.
     *
     * @param codBarras
     * @return diccionario
     */
    public Diccionario findDiccionario(String codBarras) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Diccionario.class, codBarras); // -> diccionario
        } finally {
            em.close();
        }
    }

    /**
     * el metódo retorna un diccionario asociado al isbn ingresado.
     *
     * @param isbn
     * @return dicionario
     */
    public Diccionario findDiccionarioISBN(String isbn) {
        EntityManager em = getEntityManager();
        Diccionario dicionario = null;

        try {
            Query query = em.createQuery("SELECT d FROM Diccionario d WHERE d.isbn = '" + isbn + "'");
            List<Diccionario> listD = query.getResultList();

            if (!listD.isEmpty()) {
                dicionario = listD.get(0);
            }
        } finally {
            em.close();
        }

        return dicionario;
    }

    /**
     *
     * @return
     */
    private int getDiccionarioCount() {
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
