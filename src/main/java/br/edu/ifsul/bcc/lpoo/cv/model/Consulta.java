package br.edu.ifsul.bcc.lpoo.cv.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Consulta {

    private Integer id;
    private Calendar data;
    private String observacao;
    private Calendar dataRetorno;
    private Float valor;
    private Medico medico;
    private Pet pet;
    private List<Receita> receitas;

    public Consulta() {
        receitas = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Calendar getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(Calendar dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<Receita> receitas) {
        this.receitas = receitas;
    }

    public void setReceita(Receita receita) {
        this.receitas.add(receita);
    }
    
}