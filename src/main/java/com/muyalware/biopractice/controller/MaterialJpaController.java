/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.controller;

import com.muyalware.biopractice.controller.exceptions.exceptions.IllegalOrphanException;
import com.muyalware.biopractice.controller.exceptions.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Material;
import com.muyalware.biopractice.model.Profesor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alexis
 */
public class MaterialJpaController implements Serializable {

    public MaterialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Material material) throws IllegalOrphanException {
        List<String> illegalOrphanMessages = null;
        Alumno alumnoOrphanCheck = material.getAlumno();
        if (alumnoOrphanCheck != null) {
            Material oldMaterialOfAlumno = alumnoOrphanCheck.getMaterial();
            if (oldMaterialOfAlumno != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Alumno " + alumnoOrphanCheck + " already has an item of type Material whose alumno column cannot be null. Please make another selection for the alumno field.");
            }
        }
        Profesor profesorOrphanCheck = material.getProfesor();
        if (profesorOrphanCheck != null) {
            Material oldMaterialOfProfesor = profesorOrphanCheck.getMaterial();
            if (oldMaterialOfProfesor != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Profesor " + profesorOrphanCheck + " already has an item of type Material whose profesor column cannot be null. Please make another selection for the profesor field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno = material.getAlumno();
            if (alumno != null) {
                alumno = em.getReference(alumno.getClass(), alumno.getId());
                material.setAlumno(alumno);
            }
            Profesor profesor = material.getProfesor();
            if (profesor != null) {
                profesor = em.getReference(profesor.getClass(), profesor.getId());
                material.setProfesor(profesor);
            }
            em.persist(material);
            if (alumno != null) {
                alumno.setMaterial(material);
                alumno = em.merge(alumno);
            }
            if (profesor != null) {
                profesor.setMaterial(material);
                profesor = em.merge(profesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Material material) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material persistentMaterial = em.find(Material.class, material.getId());
            Alumno alumnoOld = persistentMaterial.getAlumno();
            Alumno alumnoNew = material.getAlumno();
            Profesor profesorOld = persistentMaterial.getProfesor();
            Profesor profesorNew = material.getProfesor();
            List<String> illegalOrphanMessages = null;
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                Material oldMaterialOfAlumno = alumnoNew.getMaterial();
                if (oldMaterialOfAlumno != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Alumno " + alumnoNew + " already has an item of type Material whose alumno column cannot be null. Please make another selection for the alumno field.");
                }
            }
            if (profesorNew != null && !profesorNew.equals(profesorOld)) {
                Material oldMaterialOfProfesor = profesorNew.getMaterial();
                if (oldMaterialOfProfesor != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Profesor " + profesorNew + " already has an item of type Material whose profesor column cannot be null. Please make another selection for the profesor field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (alumnoNew != null) {
                alumnoNew = em.getReference(alumnoNew.getClass(), alumnoNew.getId());
                material.setAlumno(alumnoNew);
            }
            if (profesorNew != null) {
                profesorNew = em.getReference(profesorNew.getClass(), profesorNew.getId());
                material.setProfesor(profesorNew);
            }
            material = em.merge(material);
            if (alumnoOld != null && !alumnoOld.equals(alumnoNew)) {
                alumnoOld.setMaterial(null);
                alumnoOld = em.merge(alumnoOld);
            }
            if (alumnoNew != null && !alumnoNew.equals(alumnoOld)) {
                alumnoNew.setMaterial(material);
                alumnoNew = em.merge(alumnoNew);
            }
            if (profesorOld != null && !profesorOld.equals(profesorNew)) {
                profesorOld.setMaterial(null);
                profesorOld = em.merge(profesorOld);
            }
            if (profesorNew != null && !profesorNew.equals(profesorOld)) {
                profesorNew.setMaterial(material);
                profesorNew = em.merge(profesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = material.getId();
                if (findMaterial(id) == null) {
                    throw new NonexistentEntityException("The material with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Material material;
            try {
                material = em.getReference(Material.class, id);
                material.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The material with id " + id + " no longer exists.", enfe);
            }
            Alumno alumno = material.getAlumno();
            if (alumno != null) {
                alumno.setMaterial(null);
                alumno = em.merge(alumno);
            }
            Profesor profesor = material.getProfesor();
            if (profesor != null) {
                profesor.setMaterial(null);
                profesor = em.merge(profesor);
            }
            em.remove(material);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Material> findMaterialEntities() {
        return findMaterialEntities(true, -1, -1);
    }

    public List<Material> findMaterialEntities(int maxResults, int firstResult) {
        return findMaterialEntities(false, maxResults, firstResult);
    }

    private List<Material> findMaterialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Material.class));
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

    public Material findMaterial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Material.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaterialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Material> rt = cq.from(Material.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
