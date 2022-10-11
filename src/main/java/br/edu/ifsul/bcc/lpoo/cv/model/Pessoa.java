package br.edu.ifsul.bcc.lpoo.cv.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/*
    Pessoa é a classe pai de Cliente
*/

@Entity
@Table(name = "tb_pessoa")

// o que conteḿ o pai da herança
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")

// abstract é o que uma classe abstrata tem
public abstract class Pessoa implements Serializable {
    
    @Id
    private String cpf;
    
    @Column(nullable = false, length = 10)
    private String rg;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(nullable = false, length = 50)
    private String senha;
    
    @Column(nullable = false, length = 11)
    private String numeroCelular;
    
    @Column(nullable = false, length = 100)
    private String email;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataNascimento;
    
    @Column(nullable = false, length = 8)
    private String cep;
    
    @Column(nullable = false, length = 100)
    private String endereco;
    
    @Column(nullable = false, length = 100)
    private String complemento;
    
    // o que o pai da herança tem, para ter algo que identifique qual filho é
    @Transient
    private String tipo;

    public Pessoa() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}