package com.muyalware.biopractice.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-26T20:12:23")
@StaticMetamodel(Kit.class)
public class Kit_ { 

    public static volatile SingularAttribute<Kit, Integer> alumnoId;
    public static volatile SingularAttribute<Kit, Serializable> listaMateriales;
    public static volatile SingularAttribute<Kit, Date> fechaVencimiento;
    public static volatile SingularAttribute<Kit, Integer> id;
    public static volatile SingularAttribute<Kit, Integer> profesorId;

}