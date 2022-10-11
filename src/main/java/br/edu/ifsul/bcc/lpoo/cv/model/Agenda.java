package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/*
    Agenda tem Médico como uma classe associada
        - Contém um atributo  de Médico
*/

// vai em todas as Classes que viram tabelas
@Entity

// recebe o nome que a tabela vai ter
@Table(name = "tb_agenda")

// Serializable é sempre recomendável para usar cache
public class Agenda implements Serializable {
    
    // id marca o atributo que será a chave primária
    @Id
    
    // usar para gerar valor em sequência para o id no banco
    @SequenceGenerator(name = "seq_agenda", sequenceName = "seq_agenda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_agenda", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    // define uma coluna para a tabela que a classe forma
    // nullable diz se pode ou não ter um valor null
    @Column(nullable = false)
    
    // atributo temporal
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFim;
    
    // define o tamanho de uma string para a coluna (recomendável sempre botar)
    @Column(nullable = false, length = 200)
    private String observacao;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;
    
    @Column(nullable = false)
    
    // colunas com valores de enum
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_pessoa_cpf", nullable = false)
    private Funcionario funcionario;
    
    // o que a classe que tem a associação contém
    @ManyToOne
    
    // junta o campo na tabela (chave estrangeira)
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