package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tb_procedimento")
public class Procedimento implements Serializable {
    
    @Id
    @SequenceGenerator(name = "seq_procedimento", sequenceName = "seq_procedimento_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_procedimento", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String observacao;
    
    @Column(nullable = false)
    private Float valor;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @ManyToOne
    @JoinColumn(name = "agenda_id", nullable = false)
    private Agenda agenda;
    
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;
    
    @ManyToMany
    @JoinTable(name = "tb_procedimento_produto",
            joinColumns = {
                @JoinColumn(name = "procedimento_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "produto_id")
            })
    private List<Produto> produtos;

    public Procedimento() {
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

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
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