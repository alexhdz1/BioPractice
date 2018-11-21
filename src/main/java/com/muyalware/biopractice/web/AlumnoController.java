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
import com.muyalware.biopractice.lib.Mailer;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;
import java.util.regex.*;

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
    
    public void guardarAlumno(){
        jpa.guardar(alumno);
        lista=jpa.findAlumnoEntities();
    }
    
    public boolean registrarAlumno(){
        String ePattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@ciencias.unam.mx";
        Pattern pattern = Pattern.compile(ePattern);
        String correo = alumno.getCorreo();
	if(alumno.getCorreo() != null) {
            Matcher matcher = pattern.matcher(correo);
            if(matcher.matches()) {
                jpa.guardar(alumno);
                lista=jpa.findAlumnoEntities();
		String [] params = {"biopractice20191@gmail.com","Biopractice1234",alumno.getCorreo(),"smtp.gmail.com","587","Confirma tu correo","<a href='localhost:8080/biopractice'></a>"};
		new Mailer().envia(params);
                return true;
            }
        }
        return false;
    }
    
    public void registra(){
        if(!registrarAlumno()){
            muestraMensaje("La cuenta de correo no es valida");
        } 
    }

    private void muestraMensaje(String mensaje){
         FacesMessage mensajeFace = new FacesMessage(mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(mensajeFace);
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
