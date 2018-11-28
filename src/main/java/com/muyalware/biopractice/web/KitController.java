/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import com.muyalware.biopractice.controller.AlumnoJpaController;
import com.muyalware.biopractice.controller.KitJpaController;
import com.muyalware.biopractice.controller.MaterialJpaController;
import com.muyalware.biopractice.controller.ProfesorJpaController;
import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Kit;
import com.muyalware.biopractice.model.Material;
import com.muyalware.biopractice.model.PersistenceUtil;
import com.muyalware.biopractice.model.Profesor;
import java.util.ArrayList;
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
    private final MaterialJpaController jpaMat;
    private final ProfesorJpaController jpaProf;
    private final AlumnoJpaController jpaAlumn;
    private Kit kit;
    private Material material;
    private Profesor profesor;
    private Alumno alumno;
    private ArrayList<Integer> listaMateriales;
    public List<Material> listaTmpMateriales;
    private List<Kit> lista;
    
    
    
    /**
     * Creates a new instance of KitController
     */
    public KitController() {
        
        jpa = new KitJpaController(PersistenceUtil.getEntityManagerFactory());
        jpaMat = new MaterialJpaController(PersistenceUtil.getEntityManagerFactory());
        jpaAlumn = new AlumnoJpaController(PersistenceUtil.getEntityManagerFactory());
        jpaProf = new ProfesorJpaController(PersistenceUtil.getEntityManagerFactory());
        kit = new Kit();
        material = new Material();
        profesor = new Profesor();
        alumno   = new Alumno();
	listaMateriales = kit.getListaMateriales();
        lista = jpa.findKitEntities();
        listaTmpMateriales = new ArrayList<Material>();
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
   
   public void guardar(){
	jpa.guardar(kit);
        lista=jpa.findKitEntities();
    }
    public void modificar(){
	jpa.modificar(kit);
        lista=jpa.findKitEntities();
    }
    public void eliminar(){
	jpa.eliminar(kit);
        lista=jpa.findKitEntities();
    }
    public void agregaMaterial(int id){
        listaMateriales.add(new Integer(id));
	kit.setListaMateriales(listaMateriales);
    }
    public void eliminaMaterial(int id){
        listaMateriales.remove(new Integer(id));
	kit.setListaMateriales(listaMateriales);
    }
    public ArrayList<Integer> getlistaMateriales(){
	return listaMateriales;
    }
    public List<Material> getListaTmpMateriales(){
	return listaTmpMateriales;
    }
   
    public void guardaLista(){
        for(Material mat:listaTmpMateriales){
            System.out.println(mat.getId().toString());
            listaMateriales.add(new Integer(mat.getId()));
        }
        kit.setListaMateriales(listaMateriales);
        guardar();
        
    }
   
   
}
