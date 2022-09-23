package br.edu.ifsul.bcc.lpoo.cv.model;

import br.edu.ifsul.bcc.lpoo.cv.model.Cliente;
import br.edu.ifsul.bcc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.bcc.lpoo.cv.model.Medico;
import br.edu.ifsul.bcc.lpoo.cv.model.Status;
import br.edu.ifsul.bcc.lpoo.cv.model.TipoProduto;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-09-22T22:11:11", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Agenda.class)
public class Agenda_ { 

    public static volatile SingularAttribute<Agenda, Cliente> cliente;
    public static volatile SingularAttribute<Agenda, String> observacao;
    public static volatile SingularAttribute<Agenda, Calendar> dataFim;
    public static volatile SingularAttribute<Agenda, Medico> medico;
    public static volatile SingularAttribute<Agenda, Integer> id;
    public static volatile SingularAttribute<Agenda, Calendar> dataInicio;
    public static volatile SingularAttribute<Agenda, Funcionario> funcionario;
    public static volatile SingularAttribute<Agenda, TipoProduto> tipoProduto;
    public static volatile SingularAttribute<Agenda, Status> status;

}