package recursos1.controllers;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import recursos1.entitys.CategoriaColeccion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import recursos1.entitys.Libro;
import java.util.ArrayList;
import java.util.List;
import recursos1.entitys.Revista;
import recursos1.entitys.Diccionario;
import recursos1.entitys.Periodico;
import recursos1.entitys.Mapa;
import recursos1.entitys.Enciclopedia;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Camilo
 * @creado
 * @author Miguel Fernández
 * @modificado 17/08/2019
 */
public class CategoriaColeccionJpaController implements Serializable {

    private EntityManagerFactory emf;
    
    /**
     * 
     * @param emf 
     */
    public CategoriaColeccionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("BibliotecaSantiPU");
    }

    /**
     * 
     * @return 
     */
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * 
     * @param categoriaColeccion
     * @throws PreexistingEntityException
     * @throws Exception 
     */
    private void create(CategoriaColeccion categoriaColeccion) throws PreexistingEntityException, Exception {
        if (categoriaColeccion.getLibroList() == null) {
            categoriaColeccion.setLibroList(new ArrayList<Libro>());
        }
        
        if (categoriaColeccion.getRevistaList() == null) {
            categoriaColeccion.setRevistaList(new ArrayList<Revista>());
        }
        
        if (categoriaColeccion.getDiccionarioList() == null) {
            categoriaColeccion.setDiccionarioList(new ArrayList<Diccionario>());
        }
        
        if (categoriaColeccion.getPeriodicoList() == null) {
            categoriaColeccion.setPeriodicoList(new ArrayList<Periodico>());
        }
        
        if (categoriaColeccion.getMapaList() == null) {
            categoriaColeccion.setMapaList(new ArrayList<Mapa>());
        }
        
        if (categoriaColeccion.getEnciclopediaList() == null) {
            categoriaColeccion.setEnciclopediaList(new ArrayList<Enciclopedia>());
        }
        
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Libro> attachedLibroList = new ArrayList<Libro>();
            
            for (Libro libroListLibroToAttach : categoriaColeccion.getLibroList()) {
                libroListLibroToAttach = em.getReference(libroListLibroToAttach.getClass(), libroListLibroToAttach.getCodbarralibro());
                attachedLibroList.add(libroListLibroToAttach);
            }
            
            categoriaColeccion.setLibroList(attachedLibroList);
            List<Revista> attachedRevistaList = new ArrayList<Revista>();
            
            for (Revista revistaListRevistaToAttach : categoriaColeccion.getRevistaList()) {
                revistaListRevistaToAttach = em.getReference(revistaListRevistaToAttach.getClass(), revistaListRevistaToAttach.getCodbarrarevista());
                attachedRevistaList.add(revistaListRevistaToAttach);
            }
            
            categoriaColeccion.setRevistaList(attachedRevistaList);
            List<Diccionario> attachedDiccionarioList = new ArrayList<Diccionario>();
            
            for (Diccionario diccionarioListDiccionarioToAttach : categoriaColeccion.getDiccionarioList()) {
                diccionarioListDiccionarioToAttach = em.getReference(diccionarioListDiccionarioToAttach.getClass(), diccionarioListDiccionarioToAttach.getCodbarradiccionario());
                attachedDiccionarioList.add(diccionarioListDiccionarioToAttach);
            }
            
            categoriaColeccion.setDiccionarioList(attachedDiccionarioList);
            List<Periodico> attachedPeriodicoList = new ArrayList<Periodico>();
            
            for (Periodico periodicoListPeriodicoToAttach : categoriaColeccion.getPeriodicoList()) {
                periodicoListPeriodicoToAttach = em.getReference(periodicoListPeriodicoToAttach.getClass(), periodicoListPeriodicoToAttach.getCodbarraperiodico());
                attachedPeriodicoList.add(periodicoListPeriodicoToAttach);
            }
            
            categoriaColeccion.setPeriodicoList(attachedPeriodicoList);
            List<Mapa> attachedMapaList = new ArrayList<Mapa>();
            
            for (Mapa mapaListMapaToAttach : categoriaColeccion.getMapaList()) {
                mapaListMapaToAttach = em.getReference(mapaListMapaToAttach.getClass(), mapaListMapaToAttach.getCodbarramapa());
                attachedMapaList.add(mapaListMapaToAttach);
            }
            
            categoriaColeccion.setMapaList(attachedMapaList);
            List<Enciclopedia> attachedEnciclopediaList = new ArrayList<Enciclopedia>();
            
            for (Enciclopedia enciclopediaListEnciclopediaToAttach : categoriaColeccion.getEnciclopediaList()) {
                enciclopediaListEnciclopediaToAttach = em.getReference(enciclopediaListEnciclopediaToAttach.getClass(), enciclopediaListEnciclopediaToAttach.getCodbarraenciclopedia());
                attachedEnciclopediaList.add(enciclopediaListEnciclopediaToAttach);
            }
            
            categoriaColeccion.setEnciclopediaList(attachedEnciclopediaList);
            em.persist(categoriaColeccion);
            
            for (Libro libroListLibro : categoriaColeccion.getLibroList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfLibroListLibro = libroListLibro.getCodcategoriacoleccion();
                libroListLibro.setCodcategoriacoleccion(categoriaColeccion);
                libroListLibro = em.merge(libroListLibro);
                
                if (oldCodcategoriacoleccionOfLibroListLibro != null) {
                    oldCodcategoriacoleccionOfLibroListLibro.getLibroList().remove(libroListLibro);
                    oldCodcategoriacoleccionOfLibroListLibro = em.merge(oldCodcategoriacoleccionOfLibroListLibro);
                }
            }
            
            for (Revista revistaListRevista : categoriaColeccion.getRevistaList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfRevistaListRevista = revistaListRevista.getCodcategoriacoleccion();
                revistaListRevista.setCodcategoriacoleccion(categoriaColeccion);
                revistaListRevista = em.merge(revistaListRevista);
                
                if (oldCodcategoriacoleccionOfRevistaListRevista != null) {
                    oldCodcategoriacoleccionOfRevistaListRevista.getRevistaList().remove(revistaListRevista);
                    oldCodcategoriacoleccionOfRevistaListRevista = em.merge(oldCodcategoriacoleccionOfRevistaListRevista);
                }
            }
            
            for (Diccionario diccionarioListDiccionario : categoriaColeccion.getDiccionarioList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfDiccionarioListDiccionario = diccionarioListDiccionario.getCodcategoriacoleccion();
                diccionarioListDiccionario.setCodcategoriacoleccion(categoriaColeccion);
                diccionarioListDiccionario = em.merge(diccionarioListDiccionario);
                
                if (oldCodcategoriacoleccionOfDiccionarioListDiccionario != null) {
                    oldCodcategoriacoleccionOfDiccionarioListDiccionario.getDiccionarioList().remove(diccionarioListDiccionario);
                    oldCodcategoriacoleccionOfDiccionarioListDiccionario = em.merge(oldCodcategoriacoleccionOfDiccionarioListDiccionario);
                }
            }
            
            for (Periodico periodicoListPeriodico : categoriaColeccion.getPeriodicoList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfPeriodicoListPeriodico = periodicoListPeriodico.getCodcategoriacoleccion();
                periodicoListPeriodico.setCodcategoriacoleccion(categoriaColeccion);
                periodicoListPeriodico = em.merge(periodicoListPeriodico);
                
                if (oldCodcategoriacoleccionOfPeriodicoListPeriodico != null) {
                    oldCodcategoriacoleccionOfPeriodicoListPeriodico.getPeriodicoList().remove(periodicoListPeriodico);
                    oldCodcategoriacoleccionOfPeriodicoListPeriodico = em.merge(oldCodcategoriacoleccionOfPeriodicoListPeriodico);
                }
            }
            
            for (Mapa mapaListMapa : categoriaColeccion.getMapaList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfMapaListMapa = mapaListMapa.getCodcategoriacoleccion();
                mapaListMapa.setCodcategoriacoleccion(categoriaColeccion);
                mapaListMapa = em.merge(mapaListMapa);
                
                if (oldCodcategoriacoleccionOfMapaListMapa != null) {
                    oldCodcategoriacoleccionOfMapaListMapa.getMapaList().remove(mapaListMapa);
                    oldCodcategoriacoleccionOfMapaListMapa = em.merge(oldCodcategoriacoleccionOfMapaListMapa);
                }
            }
            
            for (Enciclopedia enciclopediaListEnciclopedia : categoriaColeccion.getEnciclopediaList()) {
                CategoriaColeccion oldCodcategoriacoleccionOfEnciclopediaListEnciclopedia = enciclopediaListEnciclopedia.getCodcategoriacoleccion();
                enciclopediaListEnciclopedia.setCodcategoriacoleccion(categoriaColeccion);
                enciclopediaListEnciclopedia = em.merge(enciclopediaListEnciclopedia);
                
                if (oldCodcategoriacoleccionOfEnciclopediaListEnciclopedia != null) {
                    oldCodcategoriacoleccionOfEnciclopediaListEnciclopedia.getEnciclopediaList().remove(enciclopediaListEnciclopedia);
                    oldCodcategoriacoleccionOfEnciclopediaListEnciclopedia = em.merge(oldCodcategoriacoleccionOfEnciclopediaListEnciclopedia);
                }
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategoriaColeccion(categoriaColeccion.getCodcategoriacoleccion()) != null) {
                throw new PreexistingEntityException("CategoriaColeccion " + categoriaColeccion + " already exists.", ex);
            }
            
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * 
     * @param categoriaColeccion
     * @throws IllegalOrphanException
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    private void edit(CategoriaColeccion categoriaColeccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        
        EntityManager em = null;
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CategoriaColeccion persistentCategoriaColeccion = em.find(CategoriaColeccion.class, categoriaColeccion.getCodcategoriacoleccion());
            List<Libro> libroListOld = persistentCategoriaColeccion.getLibroList();
            List<Libro> libroListNew = categoriaColeccion.getLibroList();
            List<Revista> revistaListOld = persistentCategoriaColeccion.getRevistaList();
            List<Revista> revistaListNew = categoriaColeccion.getRevistaList();
            List<Diccionario> diccionarioListOld = persistentCategoriaColeccion.getDiccionarioList();
            List<Diccionario> diccionarioListNew = categoriaColeccion.getDiccionarioList();
            List<Periodico> periodicoListOld = persistentCategoriaColeccion.getPeriodicoList();
            List<Periodico> periodicoListNew = categoriaColeccion.getPeriodicoList();
            List<Mapa> mapaListOld = persistentCategoriaColeccion.getMapaList();
            List<Mapa> mapaListNew = categoriaColeccion.getMapaList();
            List<Enciclopedia> enciclopediaListOld = persistentCategoriaColeccion.getEnciclopediaList();
            List<Enciclopedia> enciclopediaListNew = categoriaColeccion.getEnciclopediaList();
            List<String> illegalOrphanMessages = null;
            
            for (Libro libroListOldLibro : libroListOld) {
                if (!libroListNew.contains(libroListOldLibro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Libro " + libroListOldLibro + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            for (Revista revistaListOldRevista : revistaListOld) {
                if (!revistaListNew.contains(revistaListOldRevista)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Revista " + revistaListOldRevista + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            for (Diccionario diccionarioListOldDiccionario : diccionarioListOld) {
                if (!diccionarioListNew.contains(diccionarioListOldDiccionario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Diccionario " + diccionarioListOldDiccionario + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            for (Periodico periodicoListOldPeriodico : periodicoListOld) {
                if (!periodicoListNew.contains(periodicoListOldPeriodico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Periodico " + periodicoListOldPeriodico + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            for (Mapa mapaListOldMapa : mapaListOld) {
                if (!mapaListNew.contains(mapaListOldMapa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Mapa " + mapaListOldMapa + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            for (Enciclopedia enciclopediaListOldEnciclopedia : enciclopediaListOld) {
                if (!enciclopediaListNew.contains(enciclopediaListOldEnciclopedia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    
                    illegalOrphanMessages.add("You must retain Enciclopedia " + enciclopediaListOldEnciclopedia + " since its codcategoriacoleccion field is not nullable.");
                }
            }
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            
            List<Libro> attachedLibroListNew = new ArrayList<Libro>();
            
            for (Libro libroListNewLibroToAttach : libroListNew) {
                libroListNewLibroToAttach = em.getReference(libroListNewLibroToAttach.getClass(), libroListNewLibroToAttach.getCodbarralibro());
                attachedLibroListNew.add(libroListNewLibroToAttach);
            }
            
            libroListNew = attachedLibroListNew;
            categoriaColeccion.setLibroList(libroListNew);
            List<Revista> attachedRevistaListNew = new ArrayList<Revista>();
            
            for (Revista revistaListNewRevistaToAttach : revistaListNew) {
                revistaListNewRevistaToAttach = em.getReference(revistaListNewRevistaToAttach.getClass(), revistaListNewRevistaToAttach.getCodbarrarevista());
                attachedRevistaListNew.add(revistaListNewRevistaToAttach);
            }
            
            revistaListNew = attachedRevistaListNew;
            categoriaColeccion.setRevistaList(revistaListNew);
            List<Diccionario> attachedDiccionarioListNew = new ArrayList<Diccionario>();
            
            for (Diccionario diccionarioListNewDiccionarioToAttach : diccionarioListNew) {
                diccionarioListNewDiccionarioToAttach = em.getReference(diccionarioListNewDiccionarioToAttach.getClass(), diccionarioListNewDiccionarioToAttach.getCodbarradiccionario());
                attachedDiccionarioListNew.add(diccionarioListNewDiccionarioToAttach);
            }
            
            diccionarioListNew = attachedDiccionarioListNew;
            categoriaColeccion.setDiccionarioList(diccionarioListNew);
            List<Periodico> attachedPeriodicoListNew = new ArrayList<Periodico>();
            
            for (Periodico periodicoListNewPeriodicoToAttach : periodicoListNew) {
                periodicoListNewPeriodicoToAttach = em.getReference(periodicoListNewPeriodicoToAttach.getClass(), periodicoListNewPeriodicoToAttach.getCodbarraperiodico());
                attachedPeriodicoListNew.add(periodicoListNewPeriodicoToAttach);
            }
            periodicoListNew = attachedPeriodicoListNew;
            categoriaColeccion.setPeriodicoList(periodicoListNew);
            List<Mapa> attachedMapaListNew = new ArrayList<Mapa>();
            
            for (Mapa mapaListNewMapaToAttach : mapaListNew) {
                mapaListNewMapaToAttach = em.getReference(mapaListNewMapaToAttach.getClass(), mapaListNewMapaToAttach.getCodbarramapa());
                attachedMapaListNew.add(mapaListNewMapaToAttach);
            }
            
            mapaListNew = attachedMapaListNew;
            categoriaColeccion.setMapaList(mapaListNew);
            List<Enciclopedia> attachedEnciclopediaListNew = new ArrayList<Enciclopedia>();
            
            for (Enciclopedia enciclopediaListNewEnciclopediaToAttach : enciclopediaListNew) {
                enciclopediaListNewEnciclopediaToAttach = em.getReference(enciclopediaListNewEnciclopediaToAttach.getClass(), enciclopediaListNewEnciclopediaToAttach.getCodbarraenciclopedia());
                attachedEnciclopediaListNew.add(enciclopediaListNewEnciclopediaToAttach);
            }
            
            enciclopediaListNew = attachedEnciclopediaListNew;
            categoriaColeccion.setEnciclopediaList(enciclopediaListNew);
            categoriaColeccion = em.merge(categoriaColeccion);
            
            for (Libro libroListNewLibro : libroListNew) {
                if (!libroListOld.contains(libroListNewLibro)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfLibroListNewLibro = libroListNewLibro.getCodcategoriacoleccion();
                    libroListNewLibro.setCodcategoriacoleccion(categoriaColeccion);
                    libroListNewLibro = em.merge(libroListNewLibro);
                    
                    if (oldCodcategoriacoleccionOfLibroListNewLibro != null && !oldCodcategoriacoleccionOfLibroListNewLibro.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfLibroListNewLibro.getLibroList().remove(libroListNewLibro);
                        oldCodcategoriacoleccionOfLibroListNewLibro = em.merge(oldCodcategoriacoleccionOfLibroListNewLibro);
                    }
                }
            }
            
            for (Revista revistaListNewRevista : revistaListNew) {
                if (!revistaListOld.contains(revistaListNewRevista)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfRevistaListNewRevista = revistaListNewRevista.getCodcategoriacoleccion();
                    revistaListNewRevista.setCodcategoriacoleccion(categoriaColeccion);
                    revistaListNewRevista = em.merge(revistaListNewRevista);
                    
                    if (oldCodcategoriacoleccionOfRevistaListNewRevista != null && !oldCodcategoriacoleccionOfRevistaListNewRevista.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfRevistaListNewRevista.getRevistaList().remove(revistaListNewRevista);
                        oldCodcategoriacoleccionOfRevistaListNewRevista = em.merge(oldCodcategoriacoleccionOfRevistaListNewRevista);
                    }
                }
            }
            
            for (Diccionario diccionarioListNewDiccionario : diccionarioListNew) {
                if (!diccionarioListOld.contains(diccionarioListNewDiccionario)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfDiccionarioListNewDiccionario = diccionarioListNewDiccionario.getCodcategoriacoleccion();
                    diccionarioListNewDiccionario.setCodcategoriacoleccion(categoriaColeccion);
                    diccionarioListNewDiccionario = em.merge(diccionarioListNewDiccionario);
                    
                    if (oldCodcategoriacoleccionOfDiccionarioListNewDiccionario != null && !oldCodcategoriacoleccionOfDiccionarioListNewDiccionario.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfDiccionarioListNewDiccionario.getDiccionarioList().remove(diccionarioListNewDiccionario);
                        oldCodcategoriacoleccionOfDiccionarioListNewDiccionario = em.merge(oldCodcategoriacoleccionOfDiccionarioListNewDiccionario);
                    }
                }
            }
            
            for (Periodico periodicoListNewPeriodico : periodicoListNew) {
                if (!periodicoListOld.contains(periodicoListNewPeriodico)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfPeriodicoListNewPeriodico = periodicoListNewPeriodico.getCodcategoriacoleccion();
                    periodicoListNewPeriodico.setCodcategoriacoleccion(categoriaColeccion);
                    periodicoListNewPeriodico = em.merge(periodicoListNewPeriodico);
                    
                    if (oldCodcategoriacoleccionOfPeriodicoListNewPeriodico != null && !oldCodcategoriacoleccionOfPeriodicoListNewPeriodico.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfPeriodicoListNewPeriodico.getPeriodicoList().remove(periodicoListNewPeriodico);
                        oldCodcategoriacoleccionOfPeriodicoListNewPeriodico = em.merge(oldCodcategoriacoleccionOfPeriodicoListNewPeriodico);
                    }
                }
            }
            
            for (Mapa mapaListNewMapa : mapaListNew) {
                if (!mapaListOld.contains(mapaListNewMapa)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfMapaListNewMapa = mapaListNewMapa.getCodcategoriacoleccion();
                    mapaListNewMapa.setCodcategoriacoleccion(categoriaColeccion);
                    mapaListNewMapa = em.merge(mapaListNewMapa);
                    
                    if (oldCodcategoriacoleccionOfMapaListNewMapa != null && !oldCodcategoriacoleccionOfMapaListNewMapa.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfMapaListNewMapa.getMapaList().remove(mapaListNewMapa);
                        oldCodcategoriacoleccionOfMapaListNewMapa = em.merge(oldCodcategoriacoleccionOfMapaListNewMapa);
                    }
                }
            }
            
            for (Enciclopedia enciclopediaListNewEnciclopedia : enciclopediaListNew) {
                if (!enciclopediaListOld.contains(enciclopediaListNewEnciclopedia)) {
                    CategoriaColeccion oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia = enciclopediaListNewEnciclopedia.getCodcategoriacoleccion();
                    enciclopediaListNewEnciclopedia.setCodcategoriacoleccion(categoriaColeccion);
                    enciclopediaListNewEnciclopedia = em.merge(enciclopediaListNewEnciclopedia);
                    
                    if (oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia != null && !oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia.equals(categoriaColeccion)) {
                        oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia.getEnciclopediaList().remove(enciclopediaListNewEnciclopedia);
                        oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia = em.merge(oldCodcategoriacoleccionOfEnciclopediaListNewEnciclopedia);
                    }
                }
            }
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            
            if (msg == null || msg.length() == 0) {
                String id = categoriaColeccion.getCodcategoriacoleccion();
                
                if (findCategoriaColeccion(id) == null) {
                    throw new NonexistentEntityException("The categoriaColeccion with id " + id + " no longer exists.");
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
            CategoriaColeccion categoriaColeccion;
            
            try {
                categoriaColeccion = em.getReference(CategoriaColeccion.class, id);
                categoriaColeccion.getCodcategoriacoleccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoriaColeccion with id " + id + " no longer exists.", enfe);
            }
            
            List<String> illegalOrphanMessages = null;
            List<Libro> libroListOrphanCheck = categoriaColeccion.getLibroList();
            
            for (Libro libroListOrphanCheckLibro : libroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Libro " + libroListOrphanCheckLibro + " in its libroList field has a non-nullable codcategoriacoleccion field.");
            }
            
            List<Revista> revistaListOrphanCheck = categoriaColeccion.getRevistaList();
            for (Revista revistaListOrphanCheckRevista : revistaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Revista " + revistaListOrphanCheckRevista + " in its revistaList field has a non-nullable codcategoriacoleccion field.");
            }
            
            List<Diccionario> diccionarioListOrphanCheck = categoriaColeccion.getDiccionarioList();
            
            for (Diccionario diccionarioListOrphanCheckDiccionario : diccionarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Diccionario " + diccionarioListOrphanCheckDiccionario + " in its diccionarioList field has a non-nullable codcategoriacoleccion field.");
            }
            
            List<Periodico> periodicoListOrphanCheck = categoriaColeccion.getPeriodicoList();
            
            for (Periodico periodicoListOrphanCheckPeriodico : periodicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Periodico " + periodicoListOrphanCheckPeriodico + " in its periodicoList field has a non-nullable codcategoriacoleccion field.");
            }
            
            List<Mapa> mapaListOrphanCheck = categoriaColeccion.getMapaList();
            for (Mapa mapaListOrphanCheckMapa : mapaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Mapa " + mapaListOrphanCheckMapa + " in its mapaList field has a non-nullable codcategoriacoleccion field.");
            }
            
            List<Enciclopedia> enciclopediaListOrphanCheck = categoriaColeccion.getEnciclopediaList();
            for (Enciclopedia enciclopediaListOrphanCheckEnciclopedia : enciclopediaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                
                illegalOrphanMessages.add("This CategoriaColeccion (" + categoriaColeccion + ") cannot be destroyed since the Enciclopedia " + enciclopediaListOrphanCheckEnciclopedia + " in its enciclopediaList field has a non-nullable codcategoriacoleccion field.");
            }
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            
            em.remove(categoriaColeccion);
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
    private List<CategoriaColeccion> findCategoriaColeccionEntities() {
        return findCategoriaColeccionEntities(true, -1, -1);
    }

    /**
     * 
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<CategoriaColeccion> findCategoriaColeccionEntities(int maxResults, int firstResult) {
        return findCategoriaColeccionEntities(false, maxResults, firstResult);
    }

    /**
     * 
     * @param all
     * @param maxResults
     * @param firstResult
     * @return 
     */
    private List<CategoriaColeccion> findCategoriaColeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CategoriaColeccion.class));
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
     * 
     * @param id
     * @return 
     */
    public CategoriaColeccion findCategoriaColeccion(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CategoriaColeccion.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * 
     * @return 
     */
    private int getCategoriaColeccionCount() {
        EntityManager em = getEntityManager();
        
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CategoriaColeccion> rt = cq.from(CategoriaColeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
