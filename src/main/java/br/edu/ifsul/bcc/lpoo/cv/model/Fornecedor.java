package br.edu.ifsul.bcc.lpoo.cv.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_fornecedor")
@DiscriminatorValue("F")
public class Fornecedor extends Pessoa {
    
    @Column(nullable = false, length = 14)
    private String cnpj;
    
    @Column(nullable = false, length = 12)
    private String ie;

    public Fornecedor() {
        this.setTipo("F");
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }
    
    public void setIe(String ie) {
        this.ie = ie;
    }
    
}