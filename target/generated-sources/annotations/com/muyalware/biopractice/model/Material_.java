package com.muyalware.biopractice.model;

import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Profesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-18T19:17:19")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> descripcion;
    public static volatile SingularAttribute<Material, Boolean> prestado;
    public static volatile SingularAttribute<Material, String> subcategoria;
    public static volatile SingularAttribute<Material, Integer> unidades;
    public static volatile SingularAttribute<Material, String> categoria;
    public static volatile SingularAttribute<Material, Alumno> alumno;
    public static volatile SingularAttribute<Material, Profesor> profesor;
    public static volatile SingularAttribute<Material, Integer> id;
    public static volatile SingularAttribute<Material, String> nombre;
    public static volatile SingularAttribute<Material, byte[]> fotografia;

}