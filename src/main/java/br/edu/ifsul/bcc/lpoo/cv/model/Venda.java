package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String observacao;
    
    @Column(nullable = false)
    private Float valorTotal;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_pessoa_cpf", nullable = false)
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "cliente_pessoa_cpf", nullable = false)
    private Cliente cliente;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Pagamento formaPagamento;
    
    @ManyToMany
    @JoinTable(name = "tb_venda_consulta",
            joinColumns = {
                @JoinColumn(name = "venda_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "consulta_id")
            })
    private List<Consulta> consultas;
    
    @ManyToMany
    @JoinTable(name = "tb_venda_produto",
            joinColumns = {
                @JoinColumn(name = "venda_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "produto_id")
            })
    private List<Produto> produtos;

    public Venda() {
        consultas = new ArrayList();
        produtos = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Calendar getData() {
        return data;
    }
    
    public void setData(Calendar data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(Pagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    public void setProduto(Consulta consulta) {
        this.consultas.add(consulta);
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setProduto(Produto produto) {
        this.produtos.add(produto);
    }
    
}