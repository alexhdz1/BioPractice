/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.ProfesorJpaController;
import com.muyalware.biopractice.model.PersistenceUtil;
import com.muyalware.biopractice.model.Profesor;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author alexis
 */
@ManagedBean
@RequestScoped
public class ProfesorController {
    
    private ProfesorJpaController jpa;
    private Profesor profesor;
    private List<Profesor> lista;
    /**
     * Creates a new instance of ProfesorController
     */
    public ProfesorController() {
        jpa = new ProfesorJpaController(PersistenceUtil.getEntityManagerFactory());
        profesor = new Profesor();
        lista = jpa.findProfesorEntities();
    }
        
    public Profesor getProfesor(){
        return profesor;
    }
    
    public void setProfesor(Profesor p){
        profesor = p;
    }
    
    public List<Profesor> getLista(){
            return lista;
    }
    
   public String addProfesor(){
       jpa.create(profesor);
       return "lista";
   }
       public void guardar(){
	jpa.guardar(profesor);
        lista=jpa.findProfesorEntities();
    }
    public void modificar(){
	jpa.modificar(profesor);
        lista=jpa.findProfesorEntities();
    }
    public void eliminar(){
	jpa.eliminar(profesor);
        lista=jpa.findProfesorEntities();
    }
    public Profesor buscar(){
	lista.clear();
	lista = jpa.findProfesor(profesor);
	return jpa.findProfesor(profesor.getId());
    }
   
   public List<Profesor> getRegistrados(){
       return jpa.findProfesorEntities();
   }
}
