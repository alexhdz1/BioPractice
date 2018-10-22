/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.MaterialJpaController;
import com.muyalware.biopractice.controller.IllegalOrphanException;
import com.muyalware.biopractice.controller.exceptions.exceptions.NonexistentEntityException;
import com.muyalware.biopractice.model.Material;
import com.muyalware.biopractice.model.PersistenceUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alexis
 */
@ManagedBean
@RequestScoped
public class MaterialController {

    private final MaterialJpaController jpa;
    private Material material;
    private List<Material> lista;

    /**
     * Creates a new instance of MaterialController
     */
    public MaterialController() {
        jpa = new MaterialJpaController(PersistenceUtil.getEntityManagerFactory());
        material = new Material();
	lista = jpa.findMaterialEntities();
    }
    
    public Material getMaterial() {
        return material;
    }  

    public void setMaterial(Material m) {
        material = m;
    }

    public List<Material> getLista(){
	return lista;
    }
    
    public void imprime(){
        System.out.println("Hola");
    }
    
    public Material buscar(){
	lista.clear();
	lista.add(jpa.findMaterial(material.getId()));
        return jpa.findMaterial(material.getId());   
    }

    public void busca(){
	lista = jpa.findMaterials(material);
    }
   
    public List<Material> getRegistrados(){
       return jpa.findMaterialEntities();
   }

    /*public void guardar() throws com.muyalware.biopractice.controller.exceptions.exceptions.IllegalOrphanException{
       jpa.create(material);
       }*/

    public void guardar(){
	jpa.guardar(material);
        lista=jpa.findMaterialEntities();
    }
    public void modificar(){
	jpa.modificar(material);
        lista=jpa.findMaterialEntities();
    }
    public void eliminar(){
	jpa.eliminar(material);
        lista=jpa.findMaterialEntities();
    }
    /*public Material buscar(){
	return jpa.findMaterial(material.getId());
	}*/
    
    private void muestraMensaje(String mensaje){
         FacesMessage mensajeFace = new FacesMessage(mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(mensajeFace);
    }
}
