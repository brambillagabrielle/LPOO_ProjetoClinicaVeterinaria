package br.edu.ifsul.bcc.lpoo.cv.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_funcionario")
@DiscriminatorValue("U")
public class Funcionario extends Pessoa {

    @Column(nullable = false, length = 14)
    private String ctps;
    
    @Column(nullable = false, length = 10)
    private String pis;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    public Funcionario() {
        this.setTipo("U");
    }

    public String getCtps() {
        return ctps;
    }
    
    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String numeroPis) {
        this.pis = pis;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}