/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.MaterialJpaController;
import com.muyalware.biopractice.controller.IllegalOrphanException;
import com.muyalware.biopractice.model.Material;
import com.muyalware.biopractice.model.PersistenceUtil;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alexis
 */
@ManagedBean
@RequestScoped
public class EditarMaterialController {

    private final MaterialJpaController jpa;
    private Material material;
    private Material m;

    /**
     * Creates a new instance of MaterialController
     */
    public EditarMaterialController() {
       
        jpa = new MaterialJpaController(PersistenceUtil.getEntityManagerFactory());
        material = new Material();
       
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
        .getRequest();

        String id1 = request.getParameter("id");

        int id = Integer.parseInt((id1));
        
        m =  jpa.findMaterial(id);
    
}

    public Material getM() {
        return m;
    }

    public void setM(Material m) {
        this.m = m;
    }
    
    public Material getMaterial() {
        return material;
    }  

    public void setMaterial(Material m) {
        material = m;
    }

    public String addMaterial() throws IllegalOrphanException, com.muyalware.biopractice.controller.exceptions.exceptions.IllegalOrphanException {
        jpa.create(material);
        return "lista";
    }

    public List<Material> getRegistrados() {
        return jpa.findMaterialEntities();
    }

}
