/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.PersistenceUtil;
import com.muyalware.biopractice.controller.AlumnoJpaController;

/**
 *
 * @author muyalware
 */
@ManagedBean
@RequestScoped
public class AlumnoController {

    private Alumno alumno;
    private final AlumnoJpaController jpa;
    private List<Alumno> lista;
    /**
     * Creates a new instance of AlumnoController
     */
    public AlumnoController() {
        jpa = new AlumnoJpaController(PersistenceUtil.getEntityManagerFactory());
        alumno = new Alumno();
        lista = jpa.findAlumnoEntities();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno a) {
        alumno = a;
    }

    public List<Alumno> getLista(){
            return lista;
    }
    
    public String addAlumno() {
        jpa.create(alumno);
        return "lista";
    }
    

    public List<Alumno> getRegistrados() {
        return jpa.findAlumnoEntities();
    }
    
       public void guardar(){
	jpa.guardar(alumno);
        lista=jpa.findAlumnoEntities();
    }
    public void modificar(){
	jpa.modificar(alumno);
        lista=jpa.findAlumnoEntities();
    }
    public void eliminar(){
	jpa.eliminar(alumno);
        lista=jpa.findAlumnoEntities();
    }
    public Alumno buscar(){
	lista.clear();
	lista = jpa.findAlumno(alumno);
	return jpa.findAlumno(alumno.getId());
    }
    
}
