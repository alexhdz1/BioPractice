/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.AdministradorJpaController;
import com.muyalware.biopractice.model.Administrador;
import com.muyalware.biopractice.model.PersistenceUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author alexis
 */
@ManagedBean
@RequestScoped
public class AdministradorController {

    private AdministradorJpaController jpa;
    private Administrador administrador;
    
    /**
     * Creates a new instance of AministradorController
     */
    public AdministradorController() {
        
        jpa = new AdministradorJpaController(PersistenceUtil.getEntityManagerFactory());
        administrador = new Administrador(); 
    }
    
    public Administrador getAdministrador(){
        return administrador;
    }
    
    public void setAdministrador(Administrador a){
        administrador = a;
    }
    
   public String addAdministrador(){
       jpa.create(administrador);
       return "lista";
   }
   
   public List<Administrador> getRegistrados(){
       return jpa.findAdministradorEntities();
   }
   
}
