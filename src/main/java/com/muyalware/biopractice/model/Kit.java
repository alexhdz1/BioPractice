/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muyalware.biopractice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import java.nio.*;

/**
 *
 * @author alexis
 */
@Entity
@Table(name = "kit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kit.findAll", query = "SELECT k FROM Kit k")
    , @NamedQuery(name = "Kit.findById", query = "SELECT k FROM Kit k WHERE k.id = :id")
    , @NamedQuery(name = "Kit.findByFechaVencimiento", query = "SELECT k FROM Kit k WHERE k.fechaVencimiento = :fechaVencimiento")
    , @NamedQuery(name = "Kit.findByListaMateriales", query = "SELECT k FROM Kit k WHERE k.listaMateriales = :listaMateriales")
    , @NamedQuery(name = "Kit.findByAlumnoId", query = "SELECT k FROM Kit k WHERE k.alumnoId = :alumnoId")
    , @NamedQuery(name = "Kit.findByProfesorId", query = "SELECT k FROM Kit k WHERE k.profesorId = :profesorId")})
public class Kit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha_vencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "lista_materiales")
    private String listaMateriales;
    @Column(name = "alumno_id")
    private Integer alumnoId;
    @Column(name = "profesor_id")
    private Integer profesorId;

    public Kit() {
    }

    public Kit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public ArrayList<Integer> getListaMateriales() {
	ArrayList<Integer> tmp = new ArrayList<Integer>();
	if(listaMateriales==null){
	    
	}
	else {
	    String lista1 = listaMateriales.replace("[","");
	    lista1 = lista1.replace("]","");
	    String [] listaMateriales1 = lista1.split(",");
	    for(int x = 0; x < listaMateriales1.length; x++){
		tmp.add(new Integer(listaMateriales1[x]));
	    }
	}
	return tmp;
    }

    public void setListaMateriales(ArrayList<Integer> listaMateriales) {
        this.listaMateriales = listaMateriales.toString();
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kit)) {
            return false;
        }
        Kit other = (Kit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.muyalware.biopractice.model.Kit[ id=" + id + " ]";
    }


    
}
