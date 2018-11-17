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
import com.muyalware.biopractice.model.Material_;
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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(material);
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
            List<String> illegalOrphanMessages = null;
            material = em.merge(material);
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
            cq.orderBy(em.getCriteriaBuilder().asc(cq.from(Material.class).get(Material_.id)));
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

    public List<Material> findMaterials(Material mat){
	EntityManager em = getEntityManager();
	String jpl = "SELECT m FROM Material m";
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
	    if(!"".equals(mat.getDescripcion())){
		if(creada){
		    jpl = jpl + " AND m.descripcion LIKE '%" + mat.getDescripcion() + "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.descripcion LIKE '%" + mat.getDescripcion() + "%'";
		}
	    }
	    if(!"".equals(mat.getCategoria())){
		if(creada){
		    jpl = jpl + " AND m.unidades = " + Integer.toString(mat.getUnidades());
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.unidades = " + Integer.toString(mat.getUnidades());
		}
	    }
	    if(mat.getCategoria() != ""){
		if(creada){
		    jpl = jpl + " AND m.categoria LIKE '%" + mat.getCategoria() + "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.categoria LIKE '%" + mat.getCategoria() + "%'";
		}
	    }
	    if(!"".equals(mat.getSubcategoria())){
		if(creada){
		    jpl = jpl + " AND m.subcategoria LIKE '%" + mat.getSubcategoria() + "%'";
		} else {
		    creada = true;
		    jpl = jpl + " WHERE m.subcategoria LIKE '%" + mat.getSubcategoria() + "%'";
		}
	    }
	}
	Query query = em.createQuery(jpl);
	return query.getResultList();
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
    
    public void guardar(Material material){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(material);
	em.getTransaction().commit();
        em.close();
    }
   
    public void modificar(Material material){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(material);
	em.getTransaction().commit();
        em.close();
    }
    
    public void eliminar(Material material){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(material));
	em.getTransaction().commit();
        em.close();
    }
    
}
