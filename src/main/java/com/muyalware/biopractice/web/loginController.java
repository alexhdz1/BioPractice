package com.muyalware.biopractice.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.muyalware.biopractice.model.Administrador;
import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Profesor;
import com.muyalware.biopractice.model.PersistenceUtil;
import com.muyalware.biopractice.controller.AdministradorJpaController;
import com.muyalware.biopractice.controller.AlumnoJpaController;
import com.muyalware.biopractice.controller.ProfesorJpaController;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author muyalware
 */
@ManagedBean(name ="loginController")
@RequestScoped
public class loginController {
    
    private final AdministradorJpaController jpa;
    
    private final AlumnoJpaController jpaAlumno;
    
    private final ProfesorJpaController jpaProfesor;
    
    private String correo;
    private String contra;
    private String estado;

    /**
     * Creates a new instance of AlumnoController
     */
    public loginController() {
        jpa = new AdministradorJpaController(PersistenceUtil.getEntityManagerFactory());
        jpaAlumno = new AlumnoJpaController(PersistenceUtil.getEntityManagerFactory());
        jpaProfesor = new ProfesorJpaController(PersistenceUtil.getEntityManagerFactory());
        this.estado = "no presionado";
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String co) {
        this.correo = co;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    /*
    Revisa si el usuario y la contraseña coinciden con un administrador, si lo 
    hacen realiza login, de otro modo llama al metodo loginProfesor
    */

    public void login(){
        
        Administrador miAdmin = busca();
        if (miAdmin==null){
            //System.out.println("no existe");
            loginProfesor();
            
        } else if ( !contra.equals(miAdmin.getContrasena())){
            
            muestraMensaje("Contraseña incorrecta");
            //System.out.println("contraseña incorrecta");
        } else if ( contra.equals(miAdmin.getContrasena())){
            
            
            //System.out.println("inicio sesion");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("nombre", miAdmin.getCorreo());
            redirecciona("/faces/index.xhtml");
          
        }
    }
    
    /*
    Revisa si el usuario y la contraseña coinciden con un alumno, si lo 
    hacen realiza login, de otro modo imprime un mensaje
    */
    public void loginAlumno(){
        
        Alumno miAlumno = buscaAlumno();
        if (miAlumno==null){
            //System.out.println("no existe");
            muestraMensaje("No existe usuario");
            
        } else if ( !contra.equals(miAlumno.getContrasena())){
            
            muestraMensaje("Contraseña incorrecta");
            //System.out.println("contraseña incorrecta");
        } else if ( contra.equals(miAlumno.getContrasena())){
            
            
            //System.out.println("inicio sesion");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("alumno", miAlumno.getCorreo());
            redirecciona("/faces/indexAlumno.xhtml");
          
        }
    }
    /*
    Revisa si el usuario y la contraseña coinciden con un profesor, si lo 
    hacen realiza login, de otro modo llama al metodo loginUsuario
    */
    public void loginProfesor(){
        
        Profesor miProfesor = buscaProfesor();
        if (miProfesor==null){
            //System.out.println("no existe");
           loginAlumno();
            
        } else if ( !contra.equals(miProfesor.getContrasena())){
            
            muestraMensaje("Contraseña incorrecta");
            //System.out.println("contraseña incorrecta");
        } else if ( contra.equals(miProfesor.getContrasena())){
            
            
            //System.out.println("inicio sesion");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("profesor", miProfesor.getCorreo());
            redirecciona("/faces/indexProfesor.xhtml");
          
        }
    }
    /**
     * Busca si el correo esta en la base de los administradores
     */    
    public Administrador busca( ){
        Administrador miAdmin = null;
        List<Administrador> listaAdmins = getRegistrados();
        
        for (int i = 0; i < listaAdmins.size(); i++){
            if (listaAdmins.get(i).getCorreo().equals(correo)){
                miAdmin = listaAdmins.get(i);
                //miAdmin.setActivo(true);

                //System.out.println("estoy probando" + name + " con " + )
            }
        }
        return miAdmin;
    }
    /**
     * Busca si el correo esta en la base de los alumnos
     */
    public Alumno buscaAlumno( ){
        Alumno miAlumno = null;
        List<Alumno> listaAlumnos = getListaAlumnos();
        
        for (int i = 0; i < listaAlumnos.size(); i++){
            if (listaAlumnos.get(i).getCorreo().equals(correo)){
                miAlumno = listaAlumnos.get(i);
                //miAdmin.setActivo(true);

                //System.out.println("estoy probando" + name + " con " + )
            }
        }
        return miAlumno;
    }
    
    /**
     * Busca si el correo esta en la base de los profesores
     */
    public Profesor buscaProfesor( ){
        Profesor miProfesor = null;
        List<Profesor> listaProfesores = getListaProfesores();
        
        for (int i = 0; i < listaProfesores.size(); i++){
            if (listaProfesores.get(i).getCorreo().equals(correo)){
                miProfesor = listaProfesores.get(i);
                //miAdmin.setActivo(true);

                //System.out.println("estoy probando" + name + " con " + )
            }
        }
        return miProfesor;
    }
    
    
    
    public List<Administrador> getRegistrados(){
       return jpa.findAdministradorEntities();
   }
    
     public List<Alumno> getListaAlumnos(){
       return jpaAlumno.findAlumnoEntities();
   }
     public List<Profesor> getListaProfesores(){
       return jpaProfesor.findProfesorEntities();
   }

     /*
      Redirecciona al usuario a una url: direccion
     */
    private void redirecciona(String direccion) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
    try {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect(contextPath  + direccion);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    
    private void muestraMensaje(String mensaje){
         FacesMessage mensajeFace = new FacesMessage(mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(mensajeFace);
    }
    
    /*
     * Hace logout borrando la sesion 
     */
    public void logout() {
	FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().invalidateSession();
        try {
		context.getExternalContext().redirect("login.xhtml");
	} catch (IOException e) {
	e.printStackTrace();
	}
    }
}
