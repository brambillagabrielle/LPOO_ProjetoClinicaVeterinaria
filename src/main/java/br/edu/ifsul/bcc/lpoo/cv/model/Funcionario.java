package br.edu.ifsul.bcc.lpoo.cv.model;

public class Funcionario extends Pessoa {

    private String ctps;
    private String pis;
    private Cargo cargo;

    public Funcionario() {
        
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