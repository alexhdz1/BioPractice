/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.KitJpaController;
import com.muyalware.biopractice.model.Kit;
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
public class KitController {
    
    private final KitJpaController jpa;
    private Kit kit;
    /**
     * Creates a new instance of KitController
     */
    public KitController() {
        jpa = new KitJpaController(PersistenceUtil.getEntityManagerFactory());
        kit = new Kit();
    }
    
    public Kit getKit(){
        return kit;
    }
    
    public void setKit(Kit k){
        kit = k;
    }
    
   public String addKit(){
       jpa.create(kit);
       return "lista";
   }
   
   public List<Kit> getRegistrados(){
       return jpa.findKitEntities();
   }
}
