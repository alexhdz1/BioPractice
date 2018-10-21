package com.muyalware.biopractice.model;

import com.muyalware.biopractice.model.Alumno;
import com.muyalware.biopractice.model.Profesor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

<<<<<<< HEAD
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-19T10:25:42")
=======
@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-19T09:32:45")
>>>>>>> refs/remotes/origin/master
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