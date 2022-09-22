package br.edu.ifsul.bcc.lpoo.cv.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_medico")
@DiscriminatorValue("M")
public class Medico extends Pessoa {

    @Column(nullable = false, length = 10)
    private String crmv;

    public Medico() {
        this.setTipo("M");
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }
    
}