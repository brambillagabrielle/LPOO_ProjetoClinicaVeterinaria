package br.edu.ifsul.bcc.lpoo.cv.model;

import br.edu.ifsul.bcc.lpoo.cv.model.Pet;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-10-10T21:15:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Pessoa_ {

    public static volatile ListAttribute<Cliente, Pet> pets;
    public static volatile SingularAttribute<Cliente, Calendar> dataUltimaVisita;

}