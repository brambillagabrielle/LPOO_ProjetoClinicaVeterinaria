package br.edu.ifsul.bcc.lpoo.cv.model;

import br.edu.ifsul.bcc.lpoo.cv.model.Consulta;
import br.edu.ifsul.bcc.lpoo.cv.model.Produto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-10-10T21:15:27", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Receita.class)
public class Receita_ { 

    public static volatile SingularAttribute<Receita, String> orientacao;
    public static volatile ListAttribute<Receita, Produto> produtos;
    public static volatile SingularAttribute<Receita, Integer> id;
    public static volatile SingularAttribute<Receita, Consulta> consulta;

}