package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tb_receita")
@NamedQueries(
    {
        @NamedQuery(
            name = "Receita.orderbyid", 
            query = "SELECT r FROM Receita r ORDER BY r.id ASC"
        )
    }
)
public class Receita implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_receita", sequenceName = "seq_receita_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_receita", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String orientacao;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;

    @ManyToMany
    @JoinTable(name = "tb_receita_produto",
            joinColumns = {
                @JoinColumn(name = "receita_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "produto_id")
            })
    private List<Produto> produtos;

    public Receita() {
        produtos = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(String orientacao) {
        this.orientacao = orientacao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
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