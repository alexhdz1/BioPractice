/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.controller;

import com.muyalware.biopractice.controller.exceptions.exceptions.IllegalOrphanException;
import com.muyalware.biopractice.controller.exceptions.exceptions.NonexistentEntityException;
import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Alumno_;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alexis
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getId());
            alumno = em.merge(alumno);
            em.getTransaction().commit();
        } catch (Exception ex) {
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Alumno.class).get(Alumno_.id)));
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   
        public List<Alumno> findAlumno(Alumno mat){
	EntityManager em = getEntityManager();
	String jpl = "SELECT m FROM Alumno m";
	boolean creada = false;
	if(mat != null){
	    if(mat.getId() != 0){
		creada = true;
		jpl = jpl + " WHERE m.id = " + Integer.toString(mat.getId());
	    }
	    if(!"".equals(mat.getNombre())){
		if(creada){
		    jpl = jpl + " AND m.nombre LIKE '%" + mat.getNombre() + "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.nombre LIKE '%" + mat.getNombre() + "%'";
		}
	    }
	    if(!"".equals(mat.getCorreo())){
		if(creada){
		    jpl = jpl + " AND m.correo LIKE '%" + mat.getCorreo() + "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.correo LIKE '%" + mat.getCorreo()+ "%'";
		}
	    }
            if(!"".equals(mat.getNumCuenta())){
		if(creada){
		    jpl = jpl + " AND m.numCuenta LIKE '%" + mat.getNumCuenta()+ "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.numCuenta LIKE '%" + mat.getNumCuenta()+ "%'";
		}
	    }
            
                if(!"".equals(mat.getEstado())){
		if(creada){
		    jpl = jpl + " AND m.estado = " + mat.getEstado();
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.estado = " + mat.getEstado();
		}
	    }
                    
                    
                    
	}
	Query query = em.createQuery(jpl);
	return query.getResultList();
    }
    
       
    public void guardar(Alumno alumno){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(alumno);
	em.getTransaction().commit();
        em.close();
    }
   
    public void modificar(Alumno alumno){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(alumno);
	em.getTransaction().commit();
        em.close();
    }
    
    public void eliminar(Alumno alumno){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(alumno));
	em.getTransaction().commit();
        em.close();
    }
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
}
