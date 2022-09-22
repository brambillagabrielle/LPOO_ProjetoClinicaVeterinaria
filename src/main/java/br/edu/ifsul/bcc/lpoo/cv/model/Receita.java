package br.edu.ifsul.bcc.lpoo.cv.model;

import java.util.ArrayList;
import java.util.List;

public class Receita {

    private Integer id;
    private String orientacao;
    private Consulta consulta;
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