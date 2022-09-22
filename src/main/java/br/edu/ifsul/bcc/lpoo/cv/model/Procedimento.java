package br.edu.ifsul.bcc.lpoo.cv.model;

import java.util.ArrayList;
import java.util.List;

public class Procedimento {

    private Integer id;
    private String observacao;
    private Float valor;
    private Status status;
    private Agenda agenda;
    private Pet pet;
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