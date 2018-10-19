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
    
    /**
     * Creates a new instance of ProfesorController
     */
    public ProfesorController() {
        jpa = new ProfesorJpaController(PersistenceUtil.getEntityManagerFactory());
        profesor = new Profesor();
    }
        
    public Profesor getProfesor(){
        return profesor;
    }
    
    public void setProfesor(Profesor p){
        profesor = p;
    }
    
   public String addProfesor(){
       jpa.create(profesor);
       return "lista";
   }
   
   public List<Profesor> getRegistrados(){
       return jpa.findProfesorEntities();
   }
}
