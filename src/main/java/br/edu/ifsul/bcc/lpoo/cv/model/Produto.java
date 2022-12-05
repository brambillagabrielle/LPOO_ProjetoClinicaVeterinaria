package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tb_produto")

@NamedQueries(
    {
        @NamedQuery(
            name = "Produto.orderbyid", 
            query = "SELECT p FROM Produto p ORDER BY p.id ASC"
        )
    }
)
public class Produto implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false)
    private Float valor;
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor_pessoa_cpf", nullable = false)
    private Fornecedor fornecedor;

    public Produto() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}