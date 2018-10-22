package com.muyalware.biopractice.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.muyalware.biopractice.model.Administrador;
import com.muyalware.biopractice.model.PersistenceUtil;
import com.muyalware.biopractice.controller.AdministradorJpaController;
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
    
    private String correo;
    private String contra;
    private String estado;

    /**
     * Creates a new instance of AlumnoController
     */
    public loginController() {
        jpa = new AdministradorJpaController(PersistenceUtil.getEntityManagerFactory());
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
    

    public void login(){
        
        Administrador miAdmin = busca();
        if (miAdmin==null){
            //System.out.println("no existe");
            muestraMensaje("No existe usuario");
            
        } else if ( !contra.equals(miAdmin.getContrasena())){
            
            muestraMensaje("Contraseña incorrecta");
            //System.out.println("contraseña incorrecta");
        } else if ( contra.equals(miAdmin.getContrasena())){
            
            
            //System.out.println("inicio sesion");
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("nombre", miAdmin.getCorreo());
            redirecciona();
          
        }
    }
    
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
    
    public List<Administrador> getRegistrados(){
       return jpa.findAdministradorEntities();
   }

    private void redirecciona() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest)context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
    try {
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect(contextPath  + "/faces/index.xhtml");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    private void muestraMensaje(String mensaje){
         FacesMessage mensajeFace = new FacesMessage(mensaje);
        RequestContext.getCurrentInstance().showMessageInDialog(mensajeFace);
    }
    
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
