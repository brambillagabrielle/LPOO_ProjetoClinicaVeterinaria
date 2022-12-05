package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

@Entity
@Table(name = "tb_agenda")
public class Agenda implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_agenda", sequenceName = "seq_agenda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_agenda", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFim;
    
    @Column(nullable = false, length = 200)
    private String observacao;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;
    
    @Column(nullable = false)
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_pessoa_cpf", nullable = false)
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "medico_pessoa_cpf", nullable = false)
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "cliente_pessoa_cpf", nullable = false)
    private Cliente cliente;

    public Agenda() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Calendar dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Calendar getDataFim() {
        return dataFim;
    }

    public void setDataFim(Calendar dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}