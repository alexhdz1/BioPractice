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
import com.muyalware.biopractice.model.Profesor;
import com.muyalware.biopractice.model.Profesor_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author alexis
 */
public class ProfesorJpaController implements Serializable {

    public ProfesorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Profesor profesor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(profesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Profesor profesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Profesor persistentProfesor = em.find(Profesor.class, profesor.getId());
            profesor = em.merge(profesor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = profesor.getId();
                if (findProfesor(id) == null) {
                    throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
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
            Profesor profesor;
            try {
                profesor = em.getReference(Profesor.class, id);
                profesor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The profesor with id " + id + " no longer exists.", enfe);
            }
            em.remove(profesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Profesor> findProfesorEntities() {
        return findProfesorEntities(true, -1, -1);
    }

    public List<Profesor> findProfesorEntities(int maxResults, int firstResult) {
        return findProfesorEntities(false, maxResults, firstResult);
    }

    private List<Profesor> findProfesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Profesor.class));
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Profesor.class).get(Profesor_.id)));
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

    public Profesor findProfesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Profesor.class, id);
        } finally {
            em.close();
        }
    }

        public List<Profesor> findProfesor(Profesor mat){
	EntityManager em = getEntityManager();
	String jpl = "SELECT m FROM Profesor m";
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
            	    if(!"".equals(mat.getNumTrabajador())){
		if(creada){
		    jpl = jpl + " AND m.numTrabajador LIKE '%" + mat.getNumTrabajador()+ "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.numTrabajador LIKE '%" + mat.getNumTrabajador()+ "%'";
		}
	    }
            
	}
	Query query = em.createQuery(jpl);
	return query.getResultList();
    }
    
       
    
    public int getProfesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Profesor> rt = cq.from(Profesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    public void guardar(Profesor profesor){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(profesor);
	em.getTransaction().commit();
        em.close();
    }
   
    public void modificar(Profesor profesor){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(profesor);
	em.getTransaction().commit();
        em.close();
    }
    
    public void eliminar(Profesor profesor){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(profesor));
	em.getTransaction().commit();
        em.close();
    }
    
    
    
    
    
    
    
    
}
